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
public class NominaJSON {
	private static URL url;
	private static String sitio = "http://localhost:8088/";
	
	public static ArrayList<Nomina> getJSON() throws IOException, ParseException{
		url = new URL(sitio+"Nomina/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		ArrayList<Nomina> lista = new ArrayList<Nomina>();
		lista = parsingNomina(json);
		http.disconnect();
		return lista;
	}
	
	public static ArrayList<Nomina> parsingNomina(String json) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Nomina> lista = new ArrayList<Nomina>();
        JSONArray nominas = (JSONArray) jsonParser.parse(json);
        Iterator i = nominas.iterator();
        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            Nomina nomina = new Nomina();
            nomina.setCodigo(Integer.parseInt(innerObj.get("codigo").toString())); 
            nomina.setBonificacion(Double.parseDouble(innerObj.get("bonificacion").toString()));
            nomina.setFecha_Final(innerObj.get("fecha_final").toString());
            nomina.setFecha_Inicio(innerObj.get("fecha_inicio").toString());
            nomina.setTransporte(Double.parseDouble(innerObj.get("transporte").toString()));
            nomina.setDias_incapacidades(Integer.parseInt(innerObj.get("dias_incapacidades").toString()));
            nomina.setDias_trabajados(Integer.parseInt(innerObj.get("dias_trabajados").toString()));
            nomina.setNovedad_antes(Boolean.parseBoolean(innerObj.get("novedad_antes").toString()));
            nomina.setNovedad_despues(Boolean.parseBoolean(innerObj.get("novedad_despues").toString()));
            lista.add(nomina);
        }
        return lista;
	}
	public static int postJSON(Nomina ab) throws IOException {
		url = new URL(sitio+"Nomina/guardar");
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
				+ "\",\"bonificacion\":\""+ab.getBonificacion()
				+ "\",\"fecha_final\": \""+ab.getFecha_Final()
				+ "\",\"fecha_inicio\": \""+ab.getFecha_Inicio()
				+ "\",\"inicio_vacaciones\": \""+ab.getInicio_vacaciones()
				+ "\",\"final_vacaciones\": \""+ab.getFinal_vacaciones()
				+ "\",\"transporte\": \""+ab.getTransporte()
				+ "\",\"dias_incapacidades\": \""+ab.getDias_incapacidades()
				+ "\",\"dias_trabajados\": \""+ab.getDias_trabajados()
				+ "\",\"novedad_antes\": \""+ab.isNovedad_antes()
				+ "\",\"novedad_despues\": \""+ab.isNovedad_despues()
				+ "\",\"numero_dias\": \""+ab.getNumero_dias()
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
		url = new URL(sitio+"Nomina/eliminar/"+cedula);
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
		url = new URL(sitio+"Empresarios/editar/");
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
	public void agregarcsv() {
		String archivo = "C:\\Users\\USER\\git\\Basededatos-COLPLUS\\Frontend\\memoria\\Nomina.csv";
		ArrayList<Nomina> empresario = new ArrayList<>();
		String linea = "";
		String separacion = ";";
		String[] datos;
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
		    while ((linea = br.readLine()) != null) {
		        datos = linea.split(separacion);
		        boolean arl=false, arl2=false;
		        if(datos[1].equals("X") && datos[1]!=null) {
		        	arl=true;
		        }
		        if(datos[2].equals("X") && datos[2]!=null) {
		        	arl2=true;
		        }
		        Nomina nomina_encontrado = new Nomina(Integer.parseInt(datos[0]), arl, arl2, Integer.parseInt(datos[3]),Integer.parseInt(datos[4]),Integer.parseInt(datos[5]), datos[6], datos[7], datos[8], datos[9], Double.parseDouble(datos[10]), Double.parseDouble(datos[11]));
		        empresario.add(nomina_encontrado);
		        postJSON(nomina_encontrado);
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
