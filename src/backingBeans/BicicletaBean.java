package backingBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import dao.FactoryDAO;
import dao.IBaseDAO;
import model.Bicicleta;
import model.Estacion;
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
	
	public List<Estacion> getEstacionesDisponibles() {
		return FactoryDAO.getEstacionDAO().listar();
	}

	public Bicicleta getBicicleta() {
		return bicicleta;
	}
	public void setBicicleta(Bicicleta bicicleta) {
		this.bicicleta = bicicleta;
	}

	public String nuevaBicicleta() {
		oper = Operacion.NUEVA_BICICLETA;
		
		bicicleta = new Bicicleta();
		bicicleta.setEstadoActual(EstadoBicicleta.APTA_PARA_USO);
		//bicicleta.setEstacionActual(FactoryDAO.getEstacionDAO().listar().get(0));
		
		
		return "/bicicleta.xhtml?faces-redirect=true";
	}
	
	public String editarBicicleta() {
		oper = Operacion.EDITAR_BICICLETA;
		
		// Recupero el idEstacion pasado x parametro
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long idBicicleta = Long.parseLong(params.get("id"));  
        
        bicicleta = FactoryDAO.getBicicletaDAO().buscaPorID(idBicicleta);
		
		return "/bicicleta.xhtml?faces-redirect=true";
	}
	
	public String eliminarBicicleta() {
		oper = Operacion.ELIMINAR_BICICLETA;
		
		// Recupero el idEstacion pasado x parametro
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long idBicicleta = Long.parseLong(params.get("id"));  
        
        IBaseDAO<Bicicleta> daoEstacion = FactoryDAO.getBicicletaDAO();
        bicicleta = daoEstacion.buscaPorID(idBicicleta);
        
        // borro la estacion
        daoEstacion.borrar(bicicleta);
		
		return "/bicicletas.xhtml?faces-redirect=true";
	}
	
	public String guardar(){
		
		try{		  
			if (oper == Operacion.NUEVA_BICICLETA) {
				bicicleta.setFechaIngreso(new Date());
			}
			
			FactoryDAO.getBicicletaDAO().persistir(bicicleta);
			

			return "/bicicletas.xhtml?faces-redirect=true"; 	  

		}
		catch(Exception e){
			// TODO revisar que hacer cuando da error

			return "failure";
		}				
	}	
	
	public String getCancelarURL(){
		
		return "./bicicletas.xhtml?faces-redirect=true"; 
			
	}

	private enum Operacion {NUEVA_BICICLETA, EDITAR_BICICLETA, ELIMINAR_BICICLETA}
}
