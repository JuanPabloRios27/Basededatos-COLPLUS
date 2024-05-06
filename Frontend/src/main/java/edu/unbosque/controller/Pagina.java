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
	private String dependencia2;
	private String eps;
	private PieChartModel pieModel1;
	private PieChartModel pieModel2;
	private ArrayList<Empresarios> empresarios;
	private ArrayList<EmpresarioNorma> codigonorma;
	private ArrayList<EmpresarioNorma> codigonorma_novedad;
	private ArrayList<Peliculas> peliculas;
	private ArrayList<Libros> libros;
	public Pagina() {
		try {
			empresarios = EmpresariosJSON.getJSON();
			codigonorma = createSQLEmpNo();
			codigonorma_novedad = createSQLnovedad();
			peliculas = createSQLpeliculas();
			libros = createSQLlibros();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@PostConstruct
    public void init() {
		createPieModel1();
		createPieModel2();
	}
	private ArrayList<Empresarios> graficossqlempresarios(String string) {
		try {
			ArrayList<Empresarios> listaOrdenada = new ArrayList<>();
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String password = "";
			ResultSet rs;
			Connection cn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/coldatabase?useUnicode=true&useJDBCC", user, password);
			Statement stt = cn.createStatement();
			rs = stt.executeQuery("SELECT * FROM empresarios where dependencia = '"+string+"'");
			while (rs.next()) {
				Empresarios empresarios = new Empresarios();
				empresarios.setCodigo(rs.getInt("codigo"));
				listaOrdenada.add(empresarios);
			}
			cn.close();
			return listaOrdenada;
		}catch(Exception e) {
			
		}
		return null;
	}
	public void createPieModel1() {
		ArrayList<Empresarios> empresariosTecnologia = graficossqlempresarios("Tecnologia");
		ArrayList<Empresarios> empresariosFacturacion = graficossqlempresarios("Facturacion");
		ArrayList<Empresarios> empresariosContabilidad = graficossqlempresarios("Contabilidad");
		ArrayList<Empresarios> empresariosComercial = graficossqlempresarios("Comercial");
		pieModel1 = new PieChartModel();
		pieModel1.set("Tecnologia", empresariosTecnologia.size());
		pieModel1.set("Facturacion", empresariosFacturacion.size());
		pieModel1.set("Contabilidad", empresariosContabilidad.size());
		pieModel1.set("Comercial", empresariosComercial.size());
		pieModel1.setTitle("");
		pieModel1.setLegendPosition("e");
		pieModel1.setShadow(false);
	}
	public void createPieModel2() {
		ArrayList<Empresarios> directorventas = graficossqlempresariosporcargo(dependencia2,"Director de ventas");
		ArrayList<Empresarios> ingdesarollo = graficossqlempresariosporcargo(dependencia2,"Ingeniero de Desarrollo");
		ArrayList<Empresarios> Auxiliaresp = graficossqlempresariosporcargo(dependencia2,"Auxiliar especializado");
		ArrayList<Empresarios> gerente = graficossqlempresariosporcargo(dependencia2,"Gerente de ventas");
		ArrayList<Empresarios> facturacion = graficossqlempresariosporcargo(dependencia2,"Director de Facturacion");
		ArrayList<Empresarios> impuestos = graficossqlempresariosporcargo(dependencia2, "Director de Impuestos");
		ArrayList<Empresarios> soporte = graficossqlempresariosporcargo(dependencia2, "Ingeniero de Soporte");
		ArrayList<Empresarios> infraestructura = graficossqlempresariosporcargo(dependencia2, "Lider de infraestructura");
		ArrayList<Empresarios> liderDQA = graficossqlempresariosporcargo(dependencia2, "Lider de QA");
		ArrayList<Empresarios> DBA = graficossqlempresariosporcargo(dependencia2, "DBA");
		ArrayList<Empresarios> Directorcartera = graficossqlempresariosporcargo(dependencia2, "Director de cartera");
		ArrayList<Empresarios> Auditorinterno = graficossqlempresariosporcargo(dependencia2, "Auditor Interno");
		ArrayList<Empresarios> Directorpresupuesto = graficossqlempresariosporcargo(dependencia2, "Director de presupuestos");
		ArrayList<Empresarios> Directorcostos = graficossqlempresariosporcargo(dependencia2, "Director de costos");
		pieModel2 = new PieChartModel();
		pieModel2.set("Director de ventas", directorventas.size());
		pieModel2.set("Ingeniero de desarollo", ingdesarollo.size());
		pieModel2.set("Auxiliar especializado", Auxiliaresp.size());
		pieModel2.set("Gerente de ventas", gerente.size());
		pieModel2.set("Gerente de facturacion", facturacion.size());
		pieModel2.set("Director de impuestos", impuestos.size());
		pieModel2.set("Ingeniero de soporte", soporte.size());
		pieModel2.set("Lider de infraestructura", infraestructura.size());
		pieModel2.set("Lider de QA", liderDQA.size());
		pieModel2.set("Director de cartera", Directorcartera.size());
		pieModel2.set("DBA", DBA.size());
		pieModel2.set("Auditor interno", Auditorinterno.size());
		pieModel2.set("Director de presupuestos", Directorpresupuesto.size());
		pieModel2.set("Director de costos", Directorcostos.size());
		pieModel2.setTitle("Total de empleados por "+dependencia2+" por cargo");
		pieModel2.setLegendPosition("e");
		pieModel2.setShadow(false);
	}

	private ArrayList<Empresarios> graficossqlempresariosporcargo(String dependencia, String cargo) {
		try {
			ArrayList<Empresarios> listaOrdenada = new ArrayList<>();
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String password = "";
			ResultSet rs;
			Connection cn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/coldatabase?useUnicode=true&useJDBCC", user, password);
			Statement stt = cn.createStatement();
			rs = stt.executeQuery("SELECT * FROM empresarios where dependencia = '"+dependencia+"' AND cargo = '"+cargo+"'");
			while (rs.next()) {
				Empresarios empresarios = new Empresarios();
				empresarios.setCodigo(rs.getInt("codigo"));
				listaOrdenada.add(empresarios);
			}
			cn.close();
			return listaOrdenada;
		}catch(Exception e) {
			
		}
		return null;
	}
	
	private ArrayList<Libros> createSQLlibros() {
		try {
			ArrayList<Libros> listaOrdenada = new ArrayList<>();
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String password = "";
			ResultSet rs;
			Connection cn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/coldatabase?useUnicode=true&useJDBCC", user, password);
			Statement stt = cn.createStatement();
			rs = stt.executeQuery("SELECT * FROM libros");
			while (rs.next()) {
				Libros libros = new Libros();
				libros.setId_libro(rs.getInt("id_libro"));
				libros.setTitulo(rs.getString("titulo"));
				libros.setAutores(rs.getNString("autores"));
				libros.setEditorial(rs.getNString("editorial"));
				libros.setFecha_Publicada(rs.getInt("fecha_publicada"));
				libros.setIsbn(rs.getNString("isbn"));
				libros.setLenguaje(rs.getNString("lenguaje"));
				libros.setIsbn13(rs.getNString("isbn13"));
				libros.setNum_Megusta(rs.getInt("num_megusta"));
				libros.setNum_Paginas(rs.getInt("num_paginas"));
				libros.setNum_Reseñas(rs.getInt("num_reseñas"));
				libros.setRating(rs.getInt("rating"));
				libros.setFIELD13(rs.getString("field13"));
				listaOrdenada.add(libros);
			}
			cn.close();
			return listaOrdenada;
		}catch(Exception e) {
			
		}
		return null;
	}

	private ArrayList<Peliculas> createSQLpeliculas() {
		try {
			ArrayList<Peliculas> listaOrdenada = new ArrayList<>();
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String password = "";
			ResultSet rs;
			Connection cn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/coldatabase?useUnicode=true&useJDBCC", user, password);
			Statement stt = cn.createStatement();
			rs = stt.executeQuery("SELECT * FROM peliculas");
			while (rs.next()) {
				Peliculas peliculas = new Peliculas();
				peliculas.setCodigo(rs.getInt("codigo"));
				peliculas.setNombre(rs.getString("nombre"));
				peliculas.setAnio(rs.getInt("anio"));
				peliculas.setGenero(rs.getNString("genero"));
				listaOrdenada.add(peliculas);
			}
			cn.close();
			return listaOrdenada;
		}catch(Exception e) {
			
		}
		return null;
	}

	private ArrayList<EmpresarioNorma> createSQLnovedad() {
		try {
			ArrayList<EmpresarioNorma> listaOrdenada = new ArrayList<>();
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String password = "";
			ResultSet rs;
			Connection cn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/coldatabase?useUnicode=true&useJDBCC", user, password);
			Statement stt = cn.createStatement();
			rs = stt.executeQuery("SELECT empresarios.codigo,empresarios.nombre, nomina.fecha_inicio, nomina.fecha_final, "
					+ "nomina.inicio_vacaciones, nomina.final_vacaciones "
					+ "FROM empresarios JOIN nomina ON empresarios.codigo = nomina.codigo "
					+ "WHERE nomina.novedad_antes = 1 OR nomina.novedad_despues= 1");
			while (rs.next()) {
				EmpresarioNorma empresario = new EmpresarioNorma();
				empresario.setCodigo(rs.getInt("codigo"));
				empresario.setNombre(rs.getString("nombre"));
				empresario.setFecha_inicio(rs.getNString("fecha_inicio"));
				empresario.setFecha_final(rs.getNString("fecha_final"));
				empresario.setInicio_vacaciones(rs.getNString("inicio_vacaciones"));
				empresario.setFinal_vacaciones(rs.getNString("final_vacaciones"));
				listaOrdenada.add(empresario);
			}
			cn.close();
			return listaOrdenada;
		}catch(Exception e) {
			
		}
		return null;
	}

	private ArrayList<EmpresarioNorma> createSQLEmpNo() {
		try {
			ArrayList<EmpresarioNorma> listaOrdenada = new ArrayList<>();
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String password = "";
			ResultSet rs;
			Connection cn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/coldatabase?useUnicode=true&useJDBCC", user, password);
			Statement stt = cn.createStatement();
			rs = stt.executeQuery("SELECT * FROM empresarios JOIN nomina ON empresarios.codigo = nomina.codigo");
			while (rs.next()) {
				EmpresarioNorma empresario = new EmpresarioNorma();
				empresario.setCodigo(rs.getInt("codigo"));
				empresario.setNombre(rs.getString("nombre"));
				empresario.setArl(rs.getNString("arl"));
				empresario.setDependencia(rs.getString("dependencia"));
				empresario.setEps(rs.getNString("eps"));
				empresario.setCargo(rs.getNString("cargo"));
				empresario.setPensiones(rs.getNString("pensiones"));
				empresario.setSueldo(rs.getFloat("sueldo"));
				empresario.setFecha(rs.getNString("fecha"));
				empresario.setNovedad_antes(rs.getBoolean("novedad_antes"));
				empresario.setNovedad_antes(rs.getBoolean("novedad_despues"));
				listaOrdenada.add(empresario);
			}
			cn.close();
			return listaOrdenada;
		}catch(Exception e) {
			
		}
		return null;
	}
	public void ordenarEnMySQLeps(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String password = "";
			ResultSet rs;
			Connection cn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/coldatabase?useUnicode=true&useJDBCC", user, password);
			Statement stt = cn.createStatement();
			rs = stt.executeQuery("SELECT * FROM empresarios WHERE eps = '"+eps+"' ORDER BY nombre "+ orden );
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ordenarEnMySQL(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String password = "";
			ResultSet rs;
			Connection cn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/coldatabase?useUnicode=true&useJDBCC", user, password);
			Statement stt = cn.createStatement();
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String opcionesentretenimiento() {
		return "OpcionesEntretenimiento.xhtml";
	}
	public String reportentreteniminetolibros() {
		return "ReporteEntretenimientoLibros.xhtml";
	}
	public String reportentreteniminetopeliculas() {
		return "ReporteEntretenimientoPeliculas.xhtml";
	}
	public String reportenovedad() {
		return "ReporteNovedades.xhtml";
	}
	public String reportenomina() {
		return "ReporteNomina.xhtml";
	}
	public String reporteEmpresarial() {
		return "ReporteEmpresarial.xhtml";
	}
	public String reportesalud() {
		return "ReporteSalud.xhtml";
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
	

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	public void setPieModel2(PieChartModel pieModel2) {
		this.pieModel2 = pieModel2;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}

	public String getDependencia2() {
		return dependencia2;
	}

	public void setDependencia2(String dependencia2) {
		this.dependencia2 = dependencia2;
	}

	public ArrayList<EmpresarioNorma> getCodigonorma() {
		return codigonorma;
	}

	public void setCodigonorma(ArrayList<EmpresarioNorma> codigonorma) {
		this.codigonorma = codigonorma;
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

	public ArrayList<EmpresarioNorma> getCodigonorma_novedad() {
		return codigonorma_novedad;
	}

	public void setCodigonorma_novedad(ArrayList<EmpresarioNorma> codigonorma_novedad) {
		this.codigonorma_novedad = codigonorma_novedad;
	}

	public ArrayList<Peliculas> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(ArrayList<Peliculas> peliculas) {
		this.peliculas = peliculas;
	}

	public ArrayList<Libros> getLibros() {
		return libros;
	}

	public void setLibros(ArrayList<Libros> libros) {
		this.libros = libros;
	}
}
