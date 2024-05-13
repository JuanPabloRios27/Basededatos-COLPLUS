package edu.unbosque.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import javax.faces.bean.ManagedBean;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@ManagedBean
public class EmpresariosJSON {
	
	private static URL url;
	private static String sitio = "http://localhost:8088/";
	
	public static ArrayList<Empresarios> getJSON() throws IOException, ParseException{
		url = new URL(sitio+"Empresarios/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		ArrayList<Empresarios> lista = new ArrayList<Empresarios>();
		lista = parsingEmpresarios(json);
		http.disconnect();
		return lista;
	}
	
	public static ArrayList<Empresarios> parsingEmpresarios(String json) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Empresarios> lista = new ArrayList<Empresarios>();
        JSONArray usuarios = (JSONArray) jsonParser.parse(json);
        Iterator i = usuarios.iterator();
        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            Empresarios empresario = new Empresarios();
            empresario.setCodigo(Integer.parseInt(innerObj.get("codigo").toString())); 
            empresario.setArl(innerObj.get("arl").toString());
            empresario.setCargo(innerObj.get("cargo").toString());
            empresario.setDependencia(innerObj.get("dependencia").toString());
            empresario.setEps(innerObj.get("eps").toString());
            empresario.setFecha(innerObj.get("fecha").toString());
            empresario.setNombre(innerObj.get("nombre").toString());
            empresario.setPensiones(innerObj.get("pensiones").toString());
            empresario.setSueldo(Float.parseFloat(innerObj.get("sueldo").toString()));
            lista.add(empresario);
        }
        return lista;
	}
	public static int postJSON(Empresarios ab) throws IOException {
		url = new URL(sitio+"Empresarios/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		String data = "{"
				+ "\"codigo\":\""+ab.getCodigo()
				+ "\",\"arl\":\""+ab.getArl()
				+ "\",\"cargo\": \""+ab.getCargo()
				+ "\",\"dependencia\": \""+ab.getDependencia()
				+ "\",\"eps\": \""+ab.getEps()
				+ "\",\"fecha\": \""+ab.getFecha()
				+ "\",\"nombre\": \""+ab.getNombre()
				+ "\",\"pensiones\": \""+ab.getPensiones()
				+ "\",\"sueldo\":\""+ab.getSueldo()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		System.out.println(respuesta);
		http.disconnect();
		return respuesta;
	}
	public static void eliminar(int cedula) throws IOException, ParseException{
		url = new URL(sitio+"Empresarios/eliminar/"+cedula);
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("DELETE");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		http.disconnect();
	}
	public static void editar() throws IOException, ParseException{
		url = new URL(sitio+"Empresarios/actualizar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("PUT");
		http.setRequestProperty("Content-Type", "application/json");
		http.setDoOutput(true);
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		http.disconnect();
	}
	public void agregarcsv() {
		String archivo = "C:\\Users\\USER\\git\\Basededatos-COLPLUS\\Frontend\\memoria\\Empleados.csv";
		ArrayList<Empresarios> empresario = new ArrayList<>();
		String linea = "";
		String separacion = ";";
		String[] datos;
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
		    while ((linea = br.readLine()) != null) {
		        datos = linea.split(separacion);
		        Empresarios empresario_encontrado = new Empresarios(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], datos[7], Float.parseFloat(datos[8]));
		        empresario.add(empresario_encontrado);
		        postJSON(empresario_encontrado);
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}

	}
}
