package co.edu.unbosque.Backend.model;

import java.util.Date;

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
	private Date Fecha_Inicio;
	private Date Fecha_Final;
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
	public Date getFecha_Inicio() {
		return Fecha_Inicio;
	}
	public Date getFecha_Final() {
		return Fecha_Final;
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
	public void setFecha_Inicio(Date fecha_Inicio) {
		Fecha_Inicio = fecha_Inicio;
	}
	public void setFecha_Final(Date fecha_Final) {
		Fecha_Final = fecha_Final;
	}
	public void setBonificacion(double bonificacion) {
		Bonificacion = bonificacion;
	}
	public void setTransporte(double transporte) {
		Transporte = transporte;
	}
	public Nomina(int codigo, boolean novedad_antes, boolean novedad_despues, int dias_trabajados,
			int dias_incapacidades, int numero_dias, Date fecha_Inicio, Date fecha_Final, double bonificacion,
			double transporte) {
		super();
		this.codigo = codigo;
		this.novedad_antes = novedad_antes;
		this.novedad_despues = novedad_despues;
		this.dias_trabajados = dias_trabajados;
		this.dias_incapacidades = dias_incapacidades;
		this.numero_dias = numero_dias;
		Fecha_Inicio = fecha_Inicio;
		Fecha_Final = fecha_Final;
		Bonificacion = bonificacion;
		Transporte = transporte;
	}
}
