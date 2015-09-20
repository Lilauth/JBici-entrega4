package backingBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dao.FactoryDAO;
import model.Estacion;
import model.PrestamoActual;
import model.Usuario;

public class PrestamoBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private List<Estacion> estaciones;
    private Estacion estacionOrigen;
    private String oper;
    private int cantidad;
    private Usuario usuario;
       
    
    public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public PrestamoBean(){    	
    	this.estaciones = FactoryDAO.getEstacionDAO().listar();	
    	this.estacionOrigen = FactoryDAO.getEstacionDAO().buscaPorID(1);
    }
    
    
	public String getOper() {
		return oper;
	}



	public void setOper(String oper) {
		this.oper = oper;
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
	
	public String nuevo(){		
        this.setOper("A"); 
        this.setCantidad(1);
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long idUsuario = Long.parseLong(params.get("idUsuario"));
        this.setUsuario(FactoryDAO.getUsuarioDAO().buscaPorID(idUsuario));
		return "/prestamoActual.xhtml?faces-redirect=true";
	}	
    
	public String guardar(){
		try{		  
		    if(this.oper.equals("A")){
		    	//genero el prestamo para el usuario
		    	PrestamoActual pa = new PrestamoActual();
		    	pa.setUsuario(this.getUsuario());
		    	pa.setEstacion(this.getEstacionOrigen());
		    	pa.setFechaHora(new Date());
		    	FactoryDAO.getPrestamoActualDAO().persistir(pa);
		    }
			System.out.println(this.estacionOrigen.getNombre());
			return "/prestamos.xhtml?faces-redirect=true";			
		  //}
		}
		catch(Exception e){
			//algo dio error
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage("Los datos del formulario no son correctos");
			context.addMessage("prestarBici", message);
			return "failure";
		}				
	}		

}
