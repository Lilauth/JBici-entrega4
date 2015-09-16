package backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dao.FactoryDAO;
import model.Estacion;

public class ReservaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<Estacion> estaciones;
    private Estacion estacionOrigen;
    
    public ReservaBean(){    	
    	this.estaciones = FactoryDAO.getEstacionDAO().listar();	
    	this.estacionOrigen = FactoryDAO.getEstacionDAO().buscaPorID(1);
    }

	public List<Estacion> getEstaciones() {
		return estaciones;
	}

	public Estacion getEstacionOrigen() {
		return estacionOrigen;
	}

	public void setEstacionOrigen(Estacion estacionOrigen) {
		this.estacionOrigen = estacionOrigen;
	}
    
    

}
