package edu.unbosque.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.json.simple.parser.ParseException;
@ViewScoped
@ManagedBean
@RequestScoped
public class Pagina {
	private ArrayList<Empresarios> empresarios;
	public String hola = "ghffgh";
	public Pagina(){
		try {
			empresarios = EmpresariosJSON.getJSON();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String reportenomina() {
		return "ReporteNomina.xhtml";
	}
	public ArrayList<Empresarios> getEmpresarios() {
		return empresarios;
	}
	public void setEmpresarios(ArrayList<Empresarios> empresarios) {
		this.empresarios = empresarios;
	}
}
