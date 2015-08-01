package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Estacion {
	
	@Id @GeneratedValue
	private long id;	
	private String nombre;
	
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idEstadoEstacion")
	private EstadoEstacion estado;
	private int cantBiciletas;
	private int cantEstacionamientos;
	@OneToMany(mappedBy="ubicacionActual")
	private List<Bicicleta> bicisDisponibles;
	private double latitud;
	private double longitud;
	
	//constructor vacío hibernate
	public Estacion(){}
	
	public void setId(long id){
		this.id = id;
	}
	
	public long getId(){
		return this.id;
	}
	
    public List<Bicicleta> getBicisDisponibles() {
		return bicisDisponibles;
	}
	public void setBicisDisponibles(List<Bicicleta> bicisDisponibles) {
		this.bicisDisponibles = bicisDisponibles;
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
	public int getCantBiciletas() {
		return cantBiciletas;
	}
	public void setCantBiciletas(int cantBiciletas) {
		this.cantBiciletas = cantBiciletas;
	}
	public int getCantEstacionamientos() {
		return cantEstacionamientos;
	}
	public void setCantEstacionamientos(int cantEstacionamientos) {
		this.cantEstacionamientos = cantEstacionamientos;
	}
	
	public Bicicleta prestarBicicleta(Usuario user){
		//crea un prestamo actual
		//se lo pasa a la bicicleta
		//saca la bicicleta de la lista de disponibles
		//entrega la bicicleta
		return null;
	}
	
	public void devolverBicicleta(Bicicleta unaBicileta){
		//le pasa los datos que necesite a la bicicleta
		//agrega la bici a la lista de disponibles.
	}
	
	public void devolverYDenunciarBicicleta(Bicicleta unaBicicleta, Denuncia unaDenuncia){
		//
	}
	
	public int cantidadEstacionamientosLibres(){
		//return cantEstacionamientos - bicisDisponibles.size();
		return 0;
	}
	

}
