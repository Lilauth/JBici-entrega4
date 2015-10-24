package backingBeans;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;

import dao.FactoryDAO;
import dao.IBaseDAO;
import model.Estacion;
import model.EstadoEstacion;

public class EstacionBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Estacion estacion;
	private Operacion oper;

	
	public EstacionBean(){
		
	}

	public boolean getEsAlta() {
		return (oper == Operacion.NUEVA_ESTACION);
	}
	

	public EstadoEstacion[] getEstadosDisponibles() {
		return EstadoEstacion.values();
	}

	public Estacion getEstacion() {
		return estacion;
	}
	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	public String nuevaEstacion() {
		oper = Operacion.NUEVA_ESTACION;
		
		estacion = new Estacion();
		estacion.setEstado(EstadoEstacion.OPERATIVA);
		// Posicion inicial para estaciones nuevas (Plaza Moreno)
		estacion.setLatitud(-34.921417);
		estacion.setLongitud(-57.954515);		
		
		return "/estacion.xhtml?faces-redirect=true";
	}
	
	public String editarEstacion() {
		oper = Operacion.EDITAR_ESTACION;
		
		// Recupero el idEstacion pasado x parametro
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long idEstacion = Long.parseLong(params.get("id"));  
        
        estacion = FactoryDAO.getEstacionDAO().buscaPorID(idEstacion);
		
		return "/estacion.xhtml?faces-redirect=true";
	}
	
	public String eliminarEstacion() {
		oper = Operacion.ELIMINAR_ESTACION;
		
		// Recupero el idEstacion pasado x parametro
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long idEstacion = Long.parseLong(params.get("id"));  
        
        IBaseDAO<Estacion> daoEstacion = FactoryDAO.getEstacionDAO();
        estacion = daoEstacion.buscaPorID(idEstacion);
        
        // borro la estacion
        daoEstacion.borrar(estacion);
		
		return "/estaciones.xhtml?faces-redirect=true";
	}
	
	public String guardar(){
		
		try{		  
			FactoryDAO.getEstacionDAO().persistir(estacion);
			

			return "/estaciones.xhtml?faces-redirect=true"; 	  

		}
		catch(Exception e){
			// TODO revisar que hacer cuando da error

			return "failure";
		}				
	}	
	
	public String getCancelarURL(){
		
		return "./estaciones.xhtml?faces-redirect=true"; 
			
	}	
	
	
	
	
	
	
	
	private enum Operacion {NUEVA_ESTACION, EDITAR_ESTACION, ELIMINAR_ESTACION}

}
