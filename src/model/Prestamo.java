package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Prestamo {
	@Id @GeneratedValue
	private long id;
	private Date fechaHora;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="idEstacion")
	private Estacion estacion;
	
	@ManyToOne(optional = true)
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	@ManyToOne(optional = true)
	@JoinColumn(name="idBicicleta")
	private Bicicleta bicicleta;
	
	public Prestamo(){}
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	public Estacion getEstacion() {
		return estacion;
	}
	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Bicicleta getBicicleta() {
		return bicicleta;
	}
	public void setBicicleta(Bicicleta bicicleta) {
		this.bicicleta = bicicleta;
	}
	
	

}
