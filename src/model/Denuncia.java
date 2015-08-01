package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Denuncia {
	
	@Id @GeneratedValue
	private long id;
	@OneToOne(optional = false)
	private Prestamo prestamoOrigen;
	private String descripcion;
	
	public Denuncia(){}
	
	public void setId(long id){
		this.id = id;
	}
	
	public long getId(){
		return this.id;
	}
	
	public Prestamo getPrestamoOrigen() {
		return prestamoOrigen;
	}
	
	public void setPrestamoOrigen(Prestamo prestamoOrigen) {
		this.prestamoOrigen = prestamoOrigen;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String desccripcion) {
		this.descripcion = desccripcion;
	}		

}
