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
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class TestJSON {
	private static URL url;
	private static String sitio = "http://localhost:50/";
	public static ArrayList<Empresarios> getJSON() throws IOException, ParseException{
		url = new URL(sitio+"usuarios/listar");
		HttpURLConnection htpp = (HttpURLConnection)url.openConnection();
		htpp.setRequestMethod("GET");
		htpp.setRequestProperty("Accept", "application/json");
		InputStream respuesta = htpp.getInputStream();
		String json = "/Frontend/memoria/Empleados.csv";
		ArrayList<Empresarios> lista = new ArrayList<Empresarios>();
		htpp.disconnect();
		return lista;
	}
	public void agregarcsv() {
		System.out.println("AMOGUS");
		String archivo = "C:\\Users\\USER\\Documents\\Universidad el Bosque\\Base de datos\\Configuracion\\Frontend\\memoria\\Empleados.csv";
		ArrayList<Empresarios> empresario = new ArrayList<>();
		String linea = "";
		String separacion = ";";
		String[] datos;
		boolean arl=false;
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
		    while ((linea = br.readLine()) != null) {
		        datos = linea.split(separacion);
		        Empresarios empresario_encontrado = new Empresarios(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4], datos[5], arl, datos[7], Float.parseFloat(datos[8]));
		        postJSON(empresario_encontrado);
		        empresario.add(empresario_encontrado);
		    }
		    
		    System.out.println(empresario);
		} catch (IOException e) {
		    e.printStackTrace();
		}

	}
	//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
	public static int postJSON(Empresarios empresarios) throws IOException{
		url = new URL(sitio+"usuarios/guardar");
		System.out.println("Amogus23");
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
				+ "\"codigo\": \""+empresarios.getCodigo()
				+ "\",\"arl\": \""+empresarios.isArl()
				+ "\",\"cargo\": \""+empresarios.getCargo()
				+ "\",\"dependencia\": \""+empresarios.getDependencia()
				+ "\",\"eps\": \""+empresarios.getEps()
				+ "\",\"dependencia\": \""+empresarios.getDependencia()
				+ "\",\"pensiones\": \""+empresarios.getPensiones()
				+ "\",\"sueldo\": \""+empresarios.getSueldo()
				+"\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
}
