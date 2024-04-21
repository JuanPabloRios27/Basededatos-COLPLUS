package co.edu.unbosque.Backend.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Libros {
	@Id
	private long Id_libro;
	private String Titulo;
	private List <String> autores;
	private double rating;
	private long isbn;
	private long isbn13;
	private String lenguaje;
	private int num_Paginas;
	private int num_Megusta;
	private int num_Reseñas;
	private Date fecha_Publicada;
	private String editorial;
	private String FIELD13;
	public long getId_libro() {
		return Id_libro;
	}
	public String getTitulo() {
		return Titulo;
	}
	public List<String> getAutores() {
		return autores;
	}
	public double getRating() {
		return rating;
	}
	public long getIsbn() {
		return isbn;
	}
	public long getIsbn13() {
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
	public Date getFecha_Publicada() {
		return fecha_Publicada;
	}
	public String getEditorial() {
		return editorial;
	}
	public String getFIELD13() {
		return FIELD13;
	}
	public void setId_libro(long id_libro) {
		Id_libro = id_libro;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public void setAutores(List<String> autores) {
		this.autores = autores;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	public void setIsbn13(long isbn13) {
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
	public void setFecha_Publicada(Date fecha_Publicada) {
		this.fecha_Publicada = fecha_Publicada;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public void setFIELD13(String fIELD13) {
		FIELD13 = fIELD13;
	}
	public Libros(long id_libro, String titulo, List<String> autores, double rating, long isbn, long isbn13,
			String lenguaje, int num_Paginas, int num_Megusta, int num_Reseñas, Date fecha_Publicada, String editorial,
			String fIELD13) {
		super();
		Id_libro = id_libro;
		Titulo = titulo;
		this.autores = autores;
		this.rating = rating;
		this.isbn = isbn;
		this.isbn13 = isbn13;
		this.lenguaje = lenguaje;
		this.num_Paginas = num_Paginas;
		this.num_Megusta = num_Megusta;
		this.num_Reseñas = num_Reseñas;
		this.fecha_Publicada = fecha_Publicada;
		this.editorial = editorial;
		FIELD13 = fIELD13;
	}
	
}
