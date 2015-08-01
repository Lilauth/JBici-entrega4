package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Perfil {	
	private String descripcion;
	@Id
	private long id;
	
	//constructor vacío hibernate
	public Perfil(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
