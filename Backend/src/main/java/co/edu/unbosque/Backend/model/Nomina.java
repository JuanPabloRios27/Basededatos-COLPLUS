package co.edu.unbosque.Backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Nomina {
	@Id
	private int codigo;
	private boolean novedad_antes;
	private boolean novedad_despues;
	private int dias_trabajados;
	private int dias_incapacidades;
	private int numero_dias;
	private String fecha_inicio;
	private String fecha_final;
	private String inicio_vacaciones;
	private String final_vacaciones;
	private double Bonificacion;
	private double Transporte;
	
	public int getCodigo() {
		return codigo;
	}

	public boolean isNovedad_antes() {
		return novedad_antes;
	}

	public boolean isNovedad_despues() {
		return novedad_despues;
	}

	public int getDias_trabajados() {
		return dias_trabajados;
	}

	public int getDias_incapacidades() {
		return dias_incapacidades;
	}

	public int getNumero_dias() {
		return numero_dias;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public String getFecha_final() {
		return fecha_final;
	}

	public String getInicio_vacaciones() {
		return inicio_vacaciones;
	}

	public String getFinal_vacaciones() {
		return final_vacaciones;
	}

	public double getBonificacion() {
		return Bonificacion;
	}

	public double getTransporte() {
		return Transporte;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setNovedad_antes(boolean novedad_antes) {
		this.novedad_antes = novedad_antes;
	}

	public void setNovedad_despues(boolean novedad_despues) {
		this.novedad_despues = novedad_despues;
	}

	public void setDias_trabajados(int dias_trabajados) {
		this.dias_trabajados = dias_trabajados;
	}

	public void setDias_incapacidades(int dias_incapacidades) {
		this.dias_incapacidades = dias_incapacidades;
	}

	public void setNumero_dias(int numero_dias) {
		this.numero_dias = numero_dias;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public void setFecha_final(String fecha_final) {
		this.fecha_final = fecha_final;
	}

	public void setInicio_vacaciones(String inicio_vacaciones) {
		this.inicio_vacaciones = inicio_vacaciones;
	}

	public void setFinal_vacaciones(String final_vacaciones) {
		this.final_vacaciones = final_vacaciones;
	}

	public void setBonificacion(double bonificacion) {
		Bonificacion = bonificacion;
	}

	public void setTransporte(double transporte) {
		Transporte = transporte;
	}

	@Override
	public String toString() {
		return "Nomina [codigo=" + codigo + ", novedad_antes=" + novedad_antes + ", novedad_despues=" + novedad_despues
				+ ", dias_trabajados=" + dias_trabajados + ", dias_incapacidades=" + dias_incapacidades
				+ ", numero_dias=" + numero_dias + ", fecha_inicio=" + fecha_inicio + ", fecha_final=" + fecha_final
				+ ", inicio_vacaciones=" + inicio_vacaciones + ", final_vacaciones=" + final_vacaciones
				+ ", Bonificacion=" + Bonificacion + ", Transporte=" + Transporte + "]";
	}

	public Nomina(int codigo, boolean novedad_antes, boolean novedad_despues, int dias_trabajados,
			int dias_incapacidades, int numero_dias, String fecha_inicio, String fecha_final, String inicio_vacaciones,
			String final_vacaciones, double bonificacion, double transporte) {
		super();
		this.codigo = codigo;
		this.novedad_antes = novedad_antes;
		this.novedad_despues = novedad_despues;
		this.dias_trabajados = dias_trabajados;
		this.dias_incapacidades = dias_incapacidades;
		this.numero_dias = numero_dias;
		this.fecha_inicio = fecha_inicio;
		this.fecha_final = fecha_final;
		this.inicio_vacaciones = inicio_vacaciones;
		this.final_vacaciones = final_vacaciones;
		Bonificacion = bonificacion;
		Transporte = transporte;
	}

	public Nomina() {
		super();
	}
}
