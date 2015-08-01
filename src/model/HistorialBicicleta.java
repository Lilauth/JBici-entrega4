package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class HistorialBicicleta {
	@Id @GeneratedValue
	private long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="idEstadoBicicleta")
	private EstadoBicicleta estado;
	
	private Date fechaYHora;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="idEstacion")
	private Estacion estacion;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="idUsuario")
	private Usuario responsable;
	
	@ManyToOne(optional = true)
	@JoinColumn(name="idBicicleta")
	private Bicicleta bicicleta;
	
	public HistorialBicicleta(){}
			
	public Bicicleta getBicicleta() {
		return bicicleta;
	}

	public void setBicicleta(Bicicleta bicicleta) {
		this.bicicleta = bicicleta;
	}



	/*getters and setters*/
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public EstadoBicicleta getEstado() {
		return estado;
	}
	
	public void setEstado(EstadoBicicleta estado) {
		this.estado = estado;
	}
	public Date getFechaYHora() {
		return fechaYHora;
	}
	public void setFechaYHora(Date fechaYHora) {
		this.fechaYHora = fechaYHora;
	}
	public Estacion getEstacion() {
		return estacion;
	}
	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}
	public Usuario getResponsable() {
		return responsable;
	}
	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}
	
	
}
