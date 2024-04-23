package edu.unbosque.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@ManagedBean
public class DemoServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String listar = request.getParameter("listar");
		String agregar = request.getParameter("agregar");
		if(agregar!=null) {
			agregarEmpresario(request,response);
		}
		if(listar!=null) {
			listarEmpresario(request,response);
		}
	}

	private void listarEmpresario(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	public void agregarEmpresario(HttpServletRequest request, HttpServletResponse response) {
		Empresarios empresarios = new Empresarios();
		empresarios.setNombre(request.getParameter("nombre"));
		empresarios.setArl(request.getParameter("arl"));
		empresarios.setCargo(request.getParameter("cargo"));
		empresarios.setCodigo(Integer.parseInt(request.getParameter("codigo")));
		empresarios.setDependencia(request.getParameter("dependencia"));
		empresarios.setEps(request.getParameter("eps"));
		empresarios.setFecha(request.getParameter("fecha"));
		empresarios.setPensiones(request.getParameter("pensiones"));
		empresarios.setSueldo(Long.parseLong(request.getParameter("sueldo")));
		int respuesta = 0;
		try {
			respuesta = TestJSON.postJSON(empresarios);
			PrintWriter writer = response.getWriter();
			if(respuesta==200) {
				writer.println("Se pudo completar el registro");
			}else {
				writer.println("Hubo un problema hacia el registro: "+respuesta);
			}
			writer.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
