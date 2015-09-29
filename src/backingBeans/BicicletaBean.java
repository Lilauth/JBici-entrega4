package backingBeans;

import java.io.Serializable;
import java.util.Map;
import javax.faces.context.FacesContext;
import dao.FactoryDAO;
import dao.IBaseDAO;
import model.Bicicleta;
import model.EstadoBicicleta;;

public class BicicletaBean  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Bicicleta bicicleta;
	private Operacion oper;

	
	public BicicletaBean(){
		
	}
	
	public boolean getEsAlta() {
		return (oper == Operacion.NUEVA_BICICLETA);
	}
	
	public EstadoBicicleta[] getEstadosDisponibles() {
		return EstadoBicicleta.values();
	}

	public Bicicleta getEstacion() {
		return bicicleta;
	}
	public void setEstacion(Bicicleta bicicleta) {
		this.bicicleta = bicicleta;
	}

	public String nuevaEstacion() {
		oper = Operacion.NUEVA_BICICLETA;
		
		bicicleta = new Bicicleta();
		bicicleta.setEstadoActual(EstadoBicicleta.APTA_PARA_USO);
		
		
		return "/estacion.xhtml?faces-redirect=true";
	}
	
	public String editarEstacion() {
		oper = Operacion.EDITAR_BICICLETA;
		
		// Recupero el idEstacion pasado x parametro
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long idBicicleta = Long.parseLong(params.get("id"));  
        
        bicicleta = FactoryDAO.getBicicletaDAO().buscaPorID(idBicicleta);
		
		return "/estacion.xhtml?faces-redirect=true";
	}
	
	public String eliminarEstacion() {
		oper = Operacion.ELIMINAR_BICICLETA;
		
		// Recupero el idEstacion pasado x parametro
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long idBicicleta = Long.parseLong(params.get("id"));  
        
        IBaseDAO<Bicicleta> daoEstacion = FactoryDAO.getBicicletaDAO();
        bicicleta = daoEstacion.buscaPorID(idBicicleta);
        
        // borro la estacion
        daoEstacion.borrar(bicicleta);
		
		return "/estaciones.xhtml?faces-redirect=true";
	}
	
	public String guardar(){
		
		try{		  
			FactoryDAO.getBicicletaDAO().persistir(bicicleta);
			

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

	private enum Operacion {NUEVA_BICICLETA, EDITAR_BICICLETA, ELIMINAR_BICICLETA}
}
