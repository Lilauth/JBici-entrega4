package services;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EstadisticasPares {
	@XmlAttribute
	private long cantidad;
	@XmlAttribute
	private String op;
	
	public EstadisticasPares(){}
	
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String operacion) {
		this.op = operacion;
	}
	
	

}
