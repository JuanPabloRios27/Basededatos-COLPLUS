package co.edu.unbosque.Backend.model;




import jakarta.persistence.Entity;
import jakarta.persistence.Id;
//Objeto que inicializara como elemento a la base de datos
@Entity
public class Empresarios {
	//La llave es el codigo
	@Id
	private int codigo;
	//resto de sus elementos y creacion de su programa
	private String nombre;
	private String dependencia;
	private String cargo;
	private String fecha;
	private String eps;
	private String arl;
	private String pensiones;
	private float sueldo;
	public int getCodigo() {
		return codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDependencia() {
		return dependencia;
	}
	public String getCargo() {
		return cargo;
	}
	public String getFecha() {
		return fecha;
	}
	public String getEps() {
		return eps;
	}
	public String getArl() {
		return arl;
	}
	public String getPensiones() {
		return pensiones;
	}
	public float getSueldo() {
		return sueldo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public void setEps(String eps) {
		this.eps = eps;
	}
	public void setArl(String arl) {
		this.arl = arl;
	}
	public void setPensiones(String pensiones) {
		this.pensiones = pensiones;
	}
	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}
	public Empresarios(int codigo, String nombre, String dependencia, String cargo, String fecha, String eps,
			String arl, String pensiones, float sueldo) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.dependencia = dependencia;
		this.cargo = cargo;
		this.fecha = fecha;
		this.eps = eps;
		this.arl = arl;
		this.pensiones = pensiones;
		this.sueldo = sueldo;
	}
	public Empresarios() {
		super();
	}
	
	
}
