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
import java.text.ParseException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class TestJSON {
	private static URL url;
	private static String sitio = "http://localhost:8088/";
	public static ArrayList<Empresarios> getJSON() throws IOException, ParseException{
		url = new URL(sitio+"Empresarios/listar");
		HttpURLConnection htpp = (HttpURLConnection)url.openConnection();
		htpp.setRequestMethod("GET");
		htpp.setRequestProperty("Accept", "application/json");
		InputStream respuesta = htpp.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for(int i =0;i<inp.length;i++) {
			json += (char)inp[i];
		}
		ArrayList<Empresarios> lista = new ArrayList<Empresarios>();
		lista = parsingUsuarios(json);
		htpp.disconnect();
		return lista;
	}
	
	private static ArrayList<Empresarios> parsingUsuarios(String json) {
		//JSONParser jsonParser = new JSONParser();
		return null;
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
		    }
		    postJSON(empresario.get(0));
		} catch (IOException e) {
		    e.printStackTrace();
		}

	}
	

	public static int postJSON(Empresarios empresarios) throws IOException{
		url = new URL(sitio+"Empresarios/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		try {
			http.setRequestMethod("POST");
		}catch(ProtocolException e) {
			e.printStackTrace();
		}
		http.setDoOutput(true);
		http.setRequestProperty("Accept","application/json");
		http.setRequestProperty("Content-Type", "application/json");
		String data = "{"
				+ "\"codigo\":\""+empresarios.getCodigo()
				+ "\",\"arl\":\""+empresarios.getArl()
				+ "\",\"cargo\":\""+empresarios.getCargo()
				+ "\",\"dependencia\":\""+empresarios.getDependencia()
				+ "\",\"eps\":\""+empresarios.getEps()
				+ "\",\"fecha\":\""+empresarios.getFecha()
				+ "\",\"nombre\":\""+empresarios.getNombre()
				+ "\",\"pensiones\":\""+empresarios.getPensiones()
				+ "\",\"sueldo\":\""+empresarios.getSueldo()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		System.out.println(respuesta);
		http.disconnect();
		return respuesta;
	}
}
