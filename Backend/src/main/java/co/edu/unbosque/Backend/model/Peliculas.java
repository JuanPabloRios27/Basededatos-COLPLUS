package co.edu.unbosque.Backend.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Peliculas {
	@Id
	private int codigo;
	private String nombre;
	private int anio;
	private List<String> genero;
	public int getCodigo() {
		return codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public int getAnio() {
		return anio;
	}
	public List<String> getGenero() {
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
	public void setGenero(List<String> genero) {
		this.genero = genero;
	}
	public Peliculas(int codigo, String nombre, int anio, List<String> genero) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.anio = anio;
		this.genero = genero;
	}
}
