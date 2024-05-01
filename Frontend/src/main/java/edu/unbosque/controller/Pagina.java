package edu.unbosque.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public void ordenarEnMySQL() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
            String password = "";
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coldatabase?useUnicode=true&useJDBCC",user,password);
			Statement stt = cn.createStatement();
			ResultSet rs = stt.executeQuery("SELECT * FROM empresarios ORDER BY dependencia ASC");
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
		}catch(SQLException e) {
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
