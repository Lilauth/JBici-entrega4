package backingBeans;

import java.io.Serializable;
import java.util.List;

import model.Estacion;

public class EstacionBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;	
	private String estado;
	private List<String> estadosDisponibles;
	private Estacion estacion;
	private String oper;
	
	public EstacionBean(){}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<String> getEstadosDisponibles() {
		return estadosDisponibles;
	}
	public void setEstadosDisponibles(List<String> estadosDisponibles) {
		this.estadosDisponibles = estadosDisponibles;
	}
	public Estacion getEstacion() {
		return estacion;
	}
	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
