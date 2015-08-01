package aspects;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import dao.AuditoriaDAOImpl;

@Entity
public class Auditoria {
	@Id @GeneratedValue
	private long id;
	private String claseObjetivo;
	private Date fechaHora;
	private long idEnClaseObjetivo;
	private String operacion;
	
	public Auditoria(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClaseObjetivo() {
		return claseObjetivo;
	}

	public void setClaseObjetivo(String claseObjetivo) {
		this.claseObjetivo = claseObjetivo;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public long getIdEnClaseObjetivo() {
		return idEnClaseObjetivo;
	}

	public void setIdEnClaseObjetivo(long idEnClaseObjetivo) {
		this.idEnClaseObjetivo = idEnClaseObjetivo;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	
	public void guardar(){
		AuditoriaDAOImpl audDAO = new AuditoriaDAOImpl();
		audDAO.guardar(this);	
	}
	
}
