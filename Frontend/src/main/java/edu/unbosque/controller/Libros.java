package edu.unbosque.controller;

import java.util.Date;
public class Libros {
	private long id_libro;
	private String titulo;
	private String autores;
	private double rating;
	private String isbn;
	private String isbn13;
	private String lenguaje;
	private int num_Paginas;
	private int num_Megusta;
	private int num_Reseñas;
	private int fecha_Publicada;
	private String editorial;
	private String FIELD13;
	
	
	public long getId_libro() {
		return id_libro;
	}


	public String getTitulo() {
		return titulo;
	}


	public String getAutores() {
		return autores;
	}


	public double getRating() {
		return rating;
	}


	public String getIsbn() {
		return isbn;
	}


	public String getIsbn13() {
		return isbn13;
	}


	public String getLenguaje() {
		return lenguaje;
	}


	public int getNum_Paginas() {
		return num_Paginas;
	}


	public int getNum_Megusta() {
		return num_Megusta;
	}


	public int getNum_Reseñas() {
		return num_Reseñas;
	}


	public int getFecha_Publicada() {
		return fecha_Publicada;
	}


	public String getEditorial() {
		return editorial;
	}


	public String getFIELD13() {
		return FIELD13;
	}


	public void setId_libro(long id_libro) {
		this.id_libro = id_libro;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public void setAutores(String autores) {
		this.autores = autores;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}


	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}


	public void setNum_Paginas(int num_Paginas) {
		this.num_Paginas = num_Paginas;
	}


	public void setNum_Megusta(int num_Megusta) {
		this.num_Megusta = num_Megusta;
	}


	public void setNum_Reseñas(int num_Reseñas) {
		this.num_Reseñas = num_Reseñas;
	}


	public void setFecha_Publicada(int fecha_Publicada) {
		this.fecha_Publicada = fecha_Publicada;
	}


	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}


	public void setFIELD13(String fIELD13) {
		FIELD13 = fIELD13;
	}
	
	

	public Libros(long id_libro, String titulo, String autores, double rating, String isbn, String isbn13,
			String lenguaje, int num_Paginas, int num_Megusta, int num_Reseñas, int i, String editorial,
			String fIELD13) {
		super();
		this.id_libro = id_libro;
		this.titulo = titulo;
		this.autores = autores;
		this.rating = rating;
		this.isbn = isbn;
		this.isbn13 = isbn13;
		this.lenguaje = lenguaje;
		this.num_Paginas = num_Paginas;
		this.num_Megusta = num_Megusta;
		this.num_Reseñas = num_Reseñas;
		this.fecha_Publicada = i;
		this.editorial = editorial;
		FIELD13 = fIELD13;
	}


	@Override
	public String toString() {
		return "Libros [id_libro=" + id_libro + ", titulo=" + titulo + ", autores=" + autores + ", rating=" + rating
				+ ", isbn=" + isbn + ", isbn13=" + isbn13 + ", lenguaje=" + lenguaje + ", num_Paginas=" + num_Paginas
				+ ", num_Megusta=" + num_Megusta + ", num_Reseñas=" + num_Reseñas + ", fecha_Publicada="
				+ fecha_Publicada + ", editorial=" + editorial + ", FIELD13=" + FIELD13 + "]";
	}


	public Libros() {
		super();
	}
	
}
