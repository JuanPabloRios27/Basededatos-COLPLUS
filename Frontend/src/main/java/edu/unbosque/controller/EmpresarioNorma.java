package edu.unbosque.controller;

public class EmpresarioNorma extends Empresarios{
	private boolean novedad_antes;
	private boolean novedad_despues;
	private String fecha_inicio;
	private String fecha_final;
	private String inicio_vacaciones;
	private String final_vacaciones;
	private double bonificacion;
	private double transporte;
	
	public double getBonificacion() {
		return bonificacion;
	}

	public double getTransporte() {
		return transporte;
	}

	public void setBonificacion(double bonificacion) {
		this.bonificacion = bonificacion;
	}

	public void setTransporte(double transporte) {
		this.transporte = transporte;
	}

	public boolean isNovedad_antes() {
		return novedad_antes;
	}

	public boolean isNovedad_despues() {
		return novedad_despues;
	}

	public void setNovedad_antes(boolean novedad_antes) {
		this.novedad_antes = novedad_antes;
	}

	public void setNovedad_despues(boolean novedad_despues) {
		this.novedad_despues = novedad_despues;
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

	public EmpresarioNorma(int codigo, String arl, String cargo, String dependencia, String eps, String fecha,
			String nombre, String pensiones, float sueldo) {
		super(codigo, arl, cargo, dependencia, eps, fecha, nombre, pensiones, sueldo);
	}

	public EmpresarioNorma() {
		// TODO Auto-generated constructor stub
	}
	

}
