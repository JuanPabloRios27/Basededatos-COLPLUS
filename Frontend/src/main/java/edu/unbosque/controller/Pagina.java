package edu.unbosque.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.json.simple.parser.ParseException;
import org.primefaces.model.chart.PieChartModel;

@ViewScoped
@ManagedBean
@RequestScoped
public class Pagina {
	private String orden;
	private String dependencia;
	private String cargo;
	private PieChartModel pieModel1;
	private ArrayList<Empresarios> empresarios;

	@PostConstruct
	public void init() {
		createPieModel1();
	}

	public Pagina() {
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

	public void createPieModel1() {
		ArrayList<Empresarios> datos = empresarios;
		pieModel1 = new PieChartModel();
		pieModel1.set("Ingeniero de Desarrollo", 5);
		pieModel1.set("Facturacion", 325);
		pieModel1.set("Contabilidad", 702);
		pieModel1.set("Comercial", 421);
		pieModel1.setTitle("");
		pieModel1.setLegendPosition("w");
		pieModel1.setShadow(false);
	}

	public void ordenarEnMySQL() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String password = "";
			ResultSet rs;
			Connection cn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/coldatabase?useUnicode=true&useJDBCC", user, password);
			Statement stt = cn.createStatement();
			System.out.println("la dependencia: "+dependencia);
			rs = stt.executeQuery("SELECT * FROM empresarios WHERE dependencia = '"+dependencia+"' ORDER BY nombre "+ orden );
			ArrayList<Empresarios> listaOrdenada = new ArrayList<>();
			while (rs.next()) {
				Empresarios empresario = new Empresarios();
				empresario.setCodigo(rs.getInt("codigo"));
				empresario.setNombre(rs.getString("nombre"));
				empresario.setArl(rs.getNString("arl"));
				empresario.setDependencia(rs.getString("dependencia"));
				empresario.setEps(rs.getNString("eps"));
				empresario.setCargo(rs.getNString("cargo"));
				empresario.setPensiones(rs.getNString("pensiones"));
				empresario.setSueldo(rs.getFloat("sueldo"));
				listaOrdenada.add(empresario);
			}
			setEmpresarios(listaOrdenada);
			cn.close();
		} catch (SQLException e) {
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

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}
}
