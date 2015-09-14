package backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ComponentSystemEvent;

import dao.FactoryDAO;
import model.Estacion;

public class ReservaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<Estacion> estaciones;
    private Estacion estacionOrigen;
    
    public void init(ComponentSystemEvent e){
    	this.estaciones = new ArrayList<>();
    	this.estaciones = FactoryDAO.getEstacionDAO().listar();
    }

}
