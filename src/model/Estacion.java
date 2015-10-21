package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import dao.FactoryDAO;


@Entity
public class Estacion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private long id;	
	private String nombre;
	
	private EstadoEstacion estado;
	private int cantEstacionamientos;
	@OneToMany(mappedBy="estacionActual")
	private List<Bicicleta> bicicletas;
	private double latitud;
	private double longitud;
	
	//constructor vacío hibernate
	public Estacion(){		
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public long getId(){
		return this.id;
	}	
	
    public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public List<Bicicleta> getBicicletas() {
		if(bicicletas == null){
			bicicletas = new ArrayList<Bicicleta>();
		}
		List<Bicicleta> todas = FactoryDAO.getBicicletaDAO().listar();
		for(Bicicleta b: todas){
			if(b.getEstacionActual() == this){
				bicicletas.add(b);
			}
		}		
		return bicicletas;
	}
	
    public void setBicicletas(List<Bicicleta> bicisDisponibles) {
		this.bicicletas = bicisDisponibles;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ArrayList<Double> getUbicacion(){
		ArrayList<Double> ubic = new ArrayList<Double>();
		ubic.add((Double)this.latitud);
		ubic.add((Double)this.longitud);
		return ubic;
	}
	
	public void setUbicacion(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
	public EstadoEstacion getEstado() {
		return estado;
	}
	public void setEstado(EstadoEstacion estado) {
		this.estado = estado;
	}
	public int getCantBicicletas() {
		return bicicletas.size();
	}
	
	public int getCantEstacionamientos() {
		return cantEstacionamientos;
	}
	
	public void setCantEstacionamientos(int cantEstacionamientos) {
		this.cantEstacionamientos = cantEstacionamientos;
	}
	/*agrega una bici a la estación*/
	public void agregarBicicleta(Bicicleta unaBici){
		this.bicicletas.add(unaBici);
	}
	/*saca una bicicleta de las bicicletas de la estacion*/
	public void prestarBicicleta(Bicicleta unaBici){
		this.bicicletas.remove(unaBici);		
	}
	/*agrega una bicicleta a la lista de bicicletas de la estación*/
	public void devolverBicicleta(Bicicleta unaBici){
		this.agregarBicicleta(unaBici);
	}
	/**esto no sé si es trabajo de la estación
	public void devolverYDenunciarBicicleta(Bicicleta unaBicicleta, Denuncia unaDenuncia){
		//
	}*/
	
	public String toString(){
		return this.getNombre();
	}
	
	public int cantidadEstacionamientosLibres(){
		return (cantEstacionamientos - bicicletas.size());		
	}
	

}
