package edu.unbosque.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.json.simple.parser.ParseException;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;

@ViewScoped
@ManagedBean
@RequestScoped
public class Pagina {
	private String orden; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private String dependencia; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private PolarAreaChartModel polarAreaModel; //Importa una libreria que permita ejecutar un grafico de frecuencias.
	private PolarAreaChartModel polarAreaModel2;
	private String dependencia2="Tecnologia";
	private String password;
	private String usuario;
	private int codigo; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private int id_pelicula; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private int id_libros; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private String nombre; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private String pension; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private String autores; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private String salud; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private float sueldo; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private String lenguajesql; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private String cargo; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private String lenguaje; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private String editorial; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private String eps; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private String isbn; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private String isbn13; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private String[] genero; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private int anio; //Guarda ese string par definir el orden hacia un elemento de la tabla en especifico
	private PieChartModel pieModel1;
	private BarChartModel barModel;
	private String[] generos = {"Adventure","Animation","Children","Comedy","Fantasy","Romance"};
	private ArrayList<Empresarios> empresarios;
	private ArrayList<EmpresarioNorma> codigonorma;
	private ArrayList<EmpresarioNorma> codigonorma_novedad;
	private ArrayList<Peliculas> peliculas;
	private ArrayList<Libros> libros;
	public Pagina() {
		try {
			peliculas = PeliculasJSON.getJSON();
			empresarios = EmpresariosJSON.getJSON();
			codigonorma = createSQLEmpNo();
			codigonorma_novedad = createSQLnovedad();
			libros = createSQLlibros();
			codigo = empresarios.get(empresarios.size()-1).getCodigo()+1;
			id_pelicula = peliculas.get(peliculas.size()-1).getCodigo()+1;
			id_libros = (int) (libros.get(libros.size()-1).getId_libro()+1);
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
		createBarModel();
		createPolarAreaModel();
		createPolarAreaModel2();
	}
	public List<String> completeArea(String query) {
        List<String> results = new ArrayList<String>();
        if(query.equals("DELETE")) {
            results.add("DELETE FROM Empresarios WHERE");
            results.add("DELETE FROM Nomina WHERE");
            results.add("DELETE FROM Libros WHERE");
            results.add("DELETE FORM Peliculas WHERE");
        }
        if(query.equals("UPDATE")) {
        	results.add("UPDATE Empresarios SET");
            results.add("UPDATE Nomina SET");
            results.add("UPDATE Libros SET");
            results.add("UPDATE Peliculas SET");
        }
        else {
            for(int i = 0; i < 10; i++) {
                results.add(query + i);
            }
        }    
        return results;
    }
	public void onSelect(SelectEvent<String> event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "select", event.getObject()));
    }
	private void createPolarAreaModel2() {
		polarAreaModel2 = new PolarAreaChartModel();
        ChartData data = new ChartData();
        ArrayList<Empresarios> empresariosProteccion = graficossqlconsulraepspension("Proteccion","Pension");
		ArrayList<Empresarios> empresariosSkandia = graficossqlconsulraepspension("Skandia","Pension");
		ArrayList<Empresarios> empresariosProvenir = graficossqlconsulraepspension("Provenir","Pension");
		ArrayList<Empresarios> empresariosColpensiones = graficossqlconsulraepspension("Colpensiones","Pension");
        PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(empresariosProteccion.size());
        values.add(empresariosSkandia.size());
        values.add(empresariosProvenir.size());
        values.add(empresariosColpensiones.size());
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(75, 192, 192)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(201, 203, 207)");
        dataSet.setBackgroundColor(bgColors);
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Proteccion");
        labels.add("Skandia");
        labels.add("Provenir");
        labels.add("Colpensiones");
        data.setLabels(labels);
        polarAreaModel2.setData(data);
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
	public void createBarModel() {
		barModel = new BarChartModel();
		barModel.setTitle("Resultados");
		barModel.setLegendPosition("ne");
        String[] dependencias= {"Tecnologia","Facturacion","Contabilidad","Comercial"};
        for(int i =0;i<dependencias.length;i++) {
        	ChartSeries values = new ChartSeries();
        	values.setLabel(dependencias[i]);
        	ArrayList<Empresarios> directorventas = graficossqlempresariosporcargo(dependencias[i],"Director de ventas");
    		ArrayList<Empresarios> ingdesarollo = graficossqlempresariosporcargo(dependencias[i],"Ingeniero de Desarrollo");
    		ArrayList<Empresarios> Auxiliaresp = graficossqlempresariosporcargo(dependencias[i],"Auxiliar especializado");
    		ArrayList<Empresarios> gerente = graficossqlempresariosporcargo(dependencias[i],"Gerente de ventas");
    		ArrayList<Empresarios> facturacion = graficossqlempresariosporcargo(dependencias[i],"Director de Facturacion");
    		ArrayList<Empresarios> impuestos = graficossqlempresariosporcargo(dependencias[i], "Director de Impuestos");
    		ArrayList<Empresarios> soporte = graficossqlempresariosporcargo(dependencias[i], "Ingeniero de Soporte");
    		ArrayList<Empresarios> infraestructura = graficossqlempresariosporcargo(dependencias[i], "Lider de infraestructura");
    		ArrayList<Empresarios> liderDQA = graficossqlempresariosporcargo(dependencias[i], "Lider de QA");
    		ArrayList<Empresarios> DBA = graficossqlempresariosporcargo(dependencias[i], "DBA");
    		ArrayList<Empresarios> Directorcartera = graficossqlempresariosporcargo(dependencias[i], "Director de cartera");
    		ArrayList<Empresarios> Auditorinterno = graficossqlempresariosporcargo(dependencias[i], "Auditor Interno");
    		ArrayList<Empresarios> Directorpresupuesto = graficossqlempresariosporcargo(dependencias[i], "Director de presupuestos");
    		ArrayList<Empresarios> Directorcostos = graficossqlempresariosporcargo(dependencias[i], "Director de costos");
    		values.set("Dr. ventas",directorventas.size());
            values.set("Ing. desarollo",ingdesarollo.size());
            values.set("Aux. esp.",Auxiliaresp.size());
            values.set("Gr. Ventas",gerente.size());
            values.set("Dir. facturas",facturacion.size());
            values.set("Dir. imp.",impuestos.size());
            values.set("Ing. Soporte",soporte.size());
            values.set("L. Infras",infraestructura.size());
            values.set("L. QA",liderDQA.size());
            values.set("DBA",DBA.size());
            values.set("Dir. Cartera",Directorcartera.size());
            values.set("Aud. interno",Auditorinterno.size());
            values.set("Dir. presp.",Directorpresupuesto.size());
            values.set("Dir. costos",Directorcostos.size());
            barModel.addSeries(values);
        }
	}
	private void createPolarAreaModel() {
        polarAreaModel = new PolarAreaChartModel();
        ChartData data = new ChartData();
        //Graficossqlconsultaepsporpension es una funcion que permite ejecutar el SQL de forma relacional.
        //Asi para obtener informacion del mismo.
        ArrayList<Empresarios> empresariosSanitas = graficossqlconsulraepspension("EPS-Sanitas","Salud");
		ArrayList<Empresarios> empresariosAliansa = graficossqlconsulraepspension("Aliansalud EPS","Salud");
		ArrayList<Empresarios> empresariosNuevaEps = graficossqlconsulraepspension("Nueva EPS","Salud");
		ArrayList<Empresarios> empresariosEpsSura = graficossqlconsulraepspension("EPS-Sura","Salud");
        PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(empresariosSanitas.size());
        values.add(empresariosAliansa.size());
        values.add(empresariosNuevaEps.size());
        values.add(empresariosEpsSura.size());
        dataSet.setData(values);     
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(75, 192, 192)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(201, 203, 207)");
        dataSet.setBackgroundColor(bgColors);
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Eps-Sanitas");
        labels.add("AliansaSalud");
        labels.add("Nueva Eps");
        labels.add("Eps-Sura");
        data.setLabels(labels);
        polarAreaModel.setData(data);
    }
	private ArrayList<Empresarios> graficossqlconsulraepspension(String string, String string2) {
		try {
			ArrayList<Empresarios> listaOrdenada = new ArrayList<>();
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String password = "";
			ResultSet rs;
			Connection cn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/coldatabase?useUnicode=true&useJDBCC", user, password);
			Statement stt = cn.createStatement();
			rs = stt.executeQuery("SELECT * FROM empresarios where pensiones = '"+string+"'");
			if(string2=="Salud") {
				rs = stt.executeQuery("SELECT * FROM empresarios where eps = '"+string+"'");
			}
			while (rs.next()) {
				Empresarios empresarios = new Empresarios();
				empresarios.setCodigo(rs.getInt("codigo"));
				listaOrdenada.add(empresarios);
			}
			cn.close();
			return listaOrdenada;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
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
			rs = stt.executeQuery("SELECT * FROM empresarios WHERE dependencia = '"+string+"'");
			while (rs.next()) {
				Empresarios empresarios = new Empresarios();
				empresarios.setCodigo(rs.getInt("codigo"));
				listaOrdenada.add(empresarios);
			}
			cn.close();
			return listaOrdenada;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
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
			rs = stt.executeQuery("SELECT * FROM empresarios WHERE dependencia = '"+dependencia+"' AND cargo = '"+cargo+"'");
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
				String fecha= rs.getNString("fecha");
				String año = fecha.substring(0, 4);
				String mes = fecha.substring(4, 6);
				String dia = fecha.substring(6, 8);
				String fechaformato = año + "/"+mes+"/"+dia;
				empresario.setFecha(fechaformato);
				empresario.setNovedad_antes(rs.getBoolean("novedad_antes"));
				empresario.setNovedad_antes(rs.getBoolean("novedad_despues"));
				empresario.setBonificacion(rs.getDouble("bonificacion"));
				empresario.setTransporte(rs.getDouble("transporte"));
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
			rs = stt.executeQuery("SELECT * FROM empresarios WHERE eps = '"+eps+"' OR pensiones = '"+pension+"' ORDER BY nombre "+ orden );
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
				empresario.setArl("Positiva");
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
	public String sqlautomatico(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String password = "";
			int rs;
			Connection cn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/coldatabase?useUnicode=true&useJDBCC", user, password);
			Statement stt = cn.createStatement();
			rs = stt.executeUpdate(lenguajesql);
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "AthenaHome.xhtml";
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
			rs = stt.executeQuery("SELECT * FROM empresarios WHERE dependencia = '"+dependencia+"' OR cargo = '"+cargo+"' ORDER BY nombre "+ orden );
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
	public String eliminarpel(int codigo) {
		try {
			PeliculasJSON.eliminar(codigo);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "AthenaHome.xhtml";
	}
	public String eliminarlib(int codigo) {
		try {
			LibrosJSON.eliminar(codigo);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "AthenaHome.xhtml";
	}
	public String eliminaremp(int codigo) {
		try {
			EmpresariosJSON.eliminar(codigo);
			NominaJSON.eliminar(codigo);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "AthenaHome.xhtml";
	}
	public String actualizaremp() {
		try {
			EmpresariosJSON.editar();
			NominaJSON.editar();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "AthenaHome.xhtml";
	}
	public String editarpel() {
		return "PeliculasEdd.xhtml";
	}
	public String editarlib() {
		return "LibrosEdd.xhtml";
	}
	public String editaremp() {
		return "EmpresariosEdd.xhtml";
	}
	public String crearpel() {
		return "PeliculasAdd.xhtml";
	}
	public String crearemp() {
		return "EmpresariosAdd.xhtml";
	}
	public String crearlib() {
		return "LibrosAdd.xhtml";
	}
	public String libroid() {
		return String.valueOf(id_libros);
	}
	public String peliculaid() {
		return String.valueOf(id_pelicula);
	}
	public String codigo() {
		return String.valueOf(codigo);
	}
	public String librosadd() {
		Libros libro = new Libros();
		libro.setId_libro(id_libros);
		libro.setAutores(autores);
		libro.setTitulo(nombre);
		libro.setEditorial(editorial);
		libro.setIsbn(isbn);
		libro.setIsbn13(isbn13);
		libro.setFecha_Publicada(anio);
		libro.setLenguaje(lenguaje);
		libro.setFIELD13(" ");
		libro.setRating(5.0);
		try {
			LibrosJSON.postJSON(libro);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "AthenaOpciones.xhtml";
	}
	public String peliculaadd() {
		Peliculas pelicula = new Peliculas();
		pelicula.setCodigo(id_pelicula);
		pelicula.setNombre(nombre);
		pelicula.setAnio(anio);
		String gen="";
		for(int i=0;i<genero.length;i++) {
			gen+=genero[i]+"|";
		}
		pelicula.setGenero(gen);
		peliculas.add(pelicula);
		try {
			PeliculasJSON.postJSON(pelicula);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "AthenaOpciones.xhtml";
	}
	public String empresarioadd() {
		Empresarios empresario = new Empresarios();
		Nomina nomina = new Nomina();
		empresario.setCodigo(codigo);
		empresario.setNombre(nombre);
		empresario.setCargo(cargo);
		empresario.setDependencia(dependencia);
		empresario.setEps(eps);
		empresario.setPensiones(pension);
		empresario.setSueldo(sueldo);
		empresario.setArl("Positiva");
		empresario.setFecha("20240512");
		nomina.setCodigo(codigo);
		nomina.setBonificacion(sueldo*0.00005);
		nomina.setTransporte(sueldo*0.000025);
		empresarios.add(empresario);
		try {
			EmpresariosJSON.postJSON(empresario);
			NominaJSON.postJSON(nomina);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "AthenaOpciones.xhtml";
	}
	public String submit() {
        boolean confirmacion = IngreseSQL();
        if(confirmacion == true) {
        	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Comprobacion: ", "Correcta");
        	FacesContext.getCurrentInstance().addMessage(null, msg);
        	return "AthenaHome.xhtml";
        }else {
        	FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:","Datos incorrectos");
        	FacesContext.getCurrentInstance().addMessage(null, msg2);
        	return "";
        }
    }
	private boolean IngreseSQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String user = usuario;
			String password = this.password;
			Connection cn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/coldatabase?useUnicode=true&useJDBCC", user, password);
			Statement stt = cn.createStatement();
			cn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public String home() {
		return "AthenaHome.xhtml";
	}
	public String lenguajessql() {
		return "Athenalenguajesql.xhtml";
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
	public String getOrden() {
		return orden;
	}
	public String getDependencia() {
		return dependencia;
	}
	public String getDependencia2() {
		return dependencia2;
	}
	public String getSalud() {
		return salud;
	}
	public String getEps() {
		return eps;
	}
	public int getId_pelicula() {
		return id_pelicula;
	}
	public int getAnio() {
		return anio;
	}
	public void setId_pelicula(int id_pelicula) {
		this.id_pelicula = id_pelicula;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public PieChartModel getPieModel1() {
		return pieModel1;
	}
	public ArrayList<Empresarios> getEmpresarios() {
		return empresarios;
	}
	public ArrayList<EmpresarioNorma> getCodigonorma() {
		return codigonorma;
	}
	public ArrayList<EmpresarioNorma> getCodigonorma_novedad() {
		return codigonorma_novedad;
	}
	public ArrayList<Peliculas> getPeliculas() {
		return peliculas;
	}
	public ArrayList<Libros> getLibros() {
		return libros;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}
	public void setDependencia2(String dependencia2) {
		this.dependencia2 = dependencia2;
	}
	public void setSalud(String salud) {
		this.salud = salud;
	}
	public void setEps(String eps) {
		this.eps = eps;
	}
	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}
	public void setEmpresarios(ArrayList<Empresarios> empresarios) {
		this.empresarios = empresarios;
	}
	public void setCodigonorma(ArrayList<EmpresarioNorma> codigonorma) {
		this.codigonorma = codigonorma;
	}
	public void setCodigonorma_novedad(ArrayList<EmpresarioNorma> codigonorma_novedad) {
		this.codigonorma_novedad = codigonorma_novedad;
	}
	public void setPeliculas(ArrayList<Peliculas> peliculas) {
		this.peliculas = peliculas;
	}
	public void setLibros(ArrayList<Libros> libros) {
		this.libros = libros;
	}
	public PolarAreaChartModel getPolarAreaModel() {
		return polarAreaModel;
	}
	public void setPolarAreaModel(PolarAreaChartModel polarAreaModel) {
		this.polarAreaModel = polarAreaModel;
	}
	public PolarAreaChartModel getPolarAreaModel2() {
		return polarAreaModel2;
	}
	public void setPolarAreaModel2(PolarAreaChartModel polarAreaModel2) {
		this.polarAreaModel2 = polarAreaModel2;
	}
	public String getPension() {
		return pension;
	}
	public void setPension(String pension) {
		this.pension = pension;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public BarChartModel getBarModel() {
		return barModel;
	}
	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getSueldo() {
		return sueldo;
	}
	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String[] getGenero() {
		return genero;
	}
	public void setGenero(String[] genero) {
		this.genero = genero;
	}
	public String[] getGeneros() {
		return generos;
	}
	public void setGeneros(String[] generos) {
		this.generos = generos;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public String getAutores() {
		return autores;
	}
	public void setAutores(String autores) {
		this.autores = autores;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getLenguaje() {
		return lenguaje;
	}
	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}
	public int getId_libros() {
		return id_libros;
	}
	public void setId_libros(int id_libros) {
		this.id_libros = id_libros;
	}
	public String getIsbn13() {
		return isbn13;
	}
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	public String getLenguajesql() {
		return lenguajesql;
	}
	public void setLenguajesql(String lenguajesql) {
		this.lenguajesql = lenguajesql;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
