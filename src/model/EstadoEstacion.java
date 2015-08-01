package model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * operativa
 * cerrada
 * en construcción
 */

@Entity
public class EstadoEstacion {

	@Id
	private long id;
	private String descripcion;
	//constructor vacío hibernate
	public EstadoEstacion(){}

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
