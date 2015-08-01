package model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("H")
public class PrestamoHistorico extends Prestamo {
	
	private Date fechaHoraDevolucion;
	@ManyToOne
	@JoinColumn(name = "idEstacionDevolucion")
	private Estacion estacionDevolucion;
	
	public PrestamoHistorico(){}
	@OneToOne(optional = true, mappedBy="prestamoOrigen")
	private Denuncia denuncia;

	public Denuncia getDenuncia() {
		return denuncia;
	}

	public void setDenuncia(Denuncia denuncia) {
		this.denuncia = denuncia;
	}

	public Estacion getEstacionDevolucion() {
		return estacionDevolucion;
	}

	public void setEstacionDevolucion(Estacion estacionDevolucion) {
		this.estacionDevolucion = estacionDevolucion;
	}

	public Date getFechaHoraDevolucion() {
		return fechaHoraDevolucion;
	}

	public void setFechaHoraDevolucion(Date fechaHoraDevolucion) {
		this.fechaHoraDevolucion = fechaHoraDevolucion;
	}
	
	public void asignar(PrestamoActual p){
		//PrestamoHistorico ph = new PrestamoHistorico();
		this.setBicicleta(p.getBicicleta());
		this.setEstacion(p.getEstacion());
		this.setFechaHora(p.getFechaHora());
		this.setUsuario(p.getUsuario());		
	}

}
