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
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
@ManagedBean
public class LibrosJSON {
	private static URL url;
	private static String sitio = "http://localhost:8088/";
	
	public static ArrayList<Libros> getJSON() throws IOException, ParseException{
		url = new URL(sitio+"Libros/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		ArrayList<Libros> lista = new ArrayList<Libros>();
		lista = parsingLibros(json);
		http.disconnect();
		return lista;
	}
	
	@SuppressWarnings("deprecation")
	public static ArrayList<Libros> parsingLibros(String json) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Libros> lista = new ArrayList<Libros>();
        JSONArray libros = (JSONArray) jsonParser.parse(json);
        Iterator i = libros.iterator();
        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            Libros libro = new Libros();
            libro.setAutores(innerObj.get("autores").toString());
            libro.setEditorial(innerObj.get("editorial").toString());
            libro.setFecha_Publicada(Integer.parseInt(innerObj.get("fecha_publicada").toString()));
            libro.setFIELD13(innerObj.get("field13").toString());
            libro.setId_libro(Integer.parseInt(innerObj.get("id_libro").toString()));
            libro.setIsbn(innerObj.get("isbn").toString());
            libro.setIsbn13(innerObj.get("isbn13").toString());
            libro.setLenguaje(innerObj.get("lenguaje").toString());
            libro.setNum_Megusta(Integer.parseInt(json));
            /*empresario.setCargo(innerObj.get("cargo").toString());
            empresario.setDependencia(innerObj.get("dependencia").toString());
            empresario.setEps(innerObj.get("eps").toString());
            empresario.setFecha(innerObj.get("fecha").toString());
            empresario.setNombre(innerObj.get("nombre").toString());
            empresario.setPensiones(innerObj.get("pensiones").toString());
            empresario.setSueldo(Float.parseFloat(innerObj.get("cargo").toString()));
            lista.add(empresario);*/
        }
        return lista;
	}
	public static int postJSON(Libros ab) throws IOException {
		url = new URL(sitio+"Libros/guardar");
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
				+ "\"autores\":\""+ab.getAutores()
				+ "\",\"editorial\":\""+ab.getEditorial()
				+ "\",\"field13\": \""+ab.getFIELD13()
				+ "\",\"id_libro\": \""+ab.getId_libro()
				+ "\",\"titulo\": \""+ab.getTitulo()
				+ "\",\"fecha_Publicada\": \""+ab.getFecha_Publicada()
				+ "\",\"isbn\": \""+ab.getIsbn()
				+ "\",\"isbn13\": \""+ab.getIsbn13()
				+ "\",\"lenguaje\": \""+ab.getLenguaje()
				+ "\",\"num_Megusta\": \""+ab.getNum_Megusta()
				+ "\",\"num_Paginas\": \""+ab.getNum_Paginas()
				+ "\",\"num_Reseñas\":\""+ab.getNum_Reseñas()
				+ "\",\"rating\":\""+ab.getRating()
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
		url = new URL(sitio+"Libros/eliminar/"+cedula);
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
		String archivo = "C:\\Users\\USER\\git\\Basededatos-COLPLUS\\Frontend\\memoria\\books.csv";
		ArrayList<Libros> libros = new ArrayList<>();
		String linea = "";
		String separacion = ";";
		String[] datos;
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
		    while ((linea = br.readLine()) != null) {
		        datos = linea.split(separacion);
		        String[] fecha = datos[10].split("/");
		        Libros libros_encontrado = new Libros(Integer.parseInt(datos[0]), datos[1], datos[2], Double.parseDouble(datos[3]), datos[4],datos[5], datos[6], Integer.parseInt(datos[7]), Integer.parseInt(datos[8]), Integer.parseInt(datos[9]), Integer.parseInt(fecha[2]), datos[11], "");
		        libros.add(libros_encontrado);
		        postJSON(libros_encontrado);
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}

	}
}
