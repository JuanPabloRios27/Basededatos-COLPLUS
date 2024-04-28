package edu.unbosque.controller;



public class Peliculas{
	private int codigo;
	private String nombre;
	private int anio;
	private String genero;
	
	
	public int getCodigo() {
		return codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public int getAnio() {
		return anio;
	}


	public String getGenero() {
		return genero;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setAnio(int anio) {
		this.anio = anio;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public Peliculas(int codigo, String nombre, int anio, String genero) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.anio = anio;
		this.genero = genero;
	}


	public Peliculas() {
		super();
	}
	
}
