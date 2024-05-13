package edu.unbosque.controller;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
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
public class PeliculasJSON {
	private static URL url;
	private static String sitio = "http://localhost:8088/";
	
	public static ArrayList<Peliculas> getJSON() throws IOException, ParseException{
		url = new URL(sitio+"Pelicula/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		ArrayList<Peliculas> lista = new ArrayList<Peliculas>();
		lista = parsingNomina(json);
		http.disconnect();
		return lista;
	}
	
	public static ArrayList<Peliculas> parsingNomina(String json) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Peliculas> lista = new ArrayList<Peliculas>();
        JSONArray nominas = (JSONArray) jsonParser.parse(json);
        Iterator i = nominas.iterator();
        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            Peliculas pelicula = new Peliculas();
            pelicula.setCodigo(Integer.parseInt(innerObj.get("codigo").toString()));
            pelicula.setAnio(Integer.parseInt(innerObj.get("anio").toString()));
            pelicula.setNombre(innerObj.get("nombre").toString());
            pelicula.setGenero(innerObj.get("genero").toString());
            lista.add(pelicula);
        }
        return lista;
	}
	public static int postJSON(Peliculas ab) throws IOException {
		url = new URL(sitio+"Pelicula/guardar");
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
				+ "\",\"nombre\": \""+ab.getNombre()
				+ "\",\"genero\": \""+ab.getGenero()
				+ "\",\"anio\":\""+ab.getAnio()
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
		url = new URL(sitio+"Pelicula/eliminar/"+cedula);
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
		url = new URL(sitio+"Peliculas/editar/");
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
		String archivo = "C:\\Users\\USER\\git\\Basededatos-COLPLUS\\Frontend\\memoria\\movies.csv";
		ArrayList<Peliculas> empresario = new ArrayList<>();
		String linea = "";
		String separacion = ";";
		String[] datos;
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
		    while ((linea = br.readLine()) != null) {
		        datos = linea.split(separacion);
		        Peliculas pelicula_encontrada = new Peliculas(Integer.parseInt(datos[0]),datos[1],Integer.parseInt(datos[2]),datos[3]);
		        empresario.add(pelicula_encontrada);
		        postJSON(pelicula_encontrada);
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
