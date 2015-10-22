package backingBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import dao.FactoryDAO;
import model.Bicicleta;
import model.Estacion;
import model.PrestamoActual;
import model.Usuario;

public class PrestamoBean implements Serializable{
		
	private static final long serialVersionUID = 1L;
	private List<Estacion> estaciones;
	private List<Bicicleta> bicicletas;

	private Estacion estacionOrigen; 
    private Bicicleta bicicleta;
    
    private Usuario usuario; 
    @ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
 
    public void displayLocation() {
    	
    	FacesMessage msg;
        if(estacionOrigen != null && bicicleta != null){ 
        	
            msg = new FacesMessage("Alta de Préstamo");
           
        }
        else
        	
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected."); 
             
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
    
    public List<Bicicleta> getBicicletas(){  
    	return this.bicicletas;
    }
    
    public void setBicicletas(List<Bicicleta> list){
    	this.bicicletas = list;
    }
    
    public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
       
	public Bicicleta getBicicleta() {
		return bicicleta;
	}


	public void setBicicleta(Bicicleta bicicleta) {
		this.bicicleta = bicicleta;
	}


	public PrestamoBean(){    	
    	this.estaciones = FactoryDAO.getEstacionDAO().listar();	    	
    	//recupero el login bean de la sesion activa
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		LoginBean neededBean = (LoginBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "loginBean");
		this.setLoginBean(neededBean); 				
    }
	
           
    public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	
	public void onChangeEstacion(){
		if(estacionOrigen != null){			
		  bicicletas = estacionOrigen.getBicicletas();
		}
	}
	
	public String nuevo(){		      
        //Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        //long idUsuario = Long.parseLong(params.get("idUsuario"));
        long idUsuario = this.getLoginBean().getIdUsuario();
        this.setUsuario(FactoryDAO.getUsuarioDAO().buscaPorID(idUsuario));
		return "/prestamoActual.xhtml?faces-redirect=true";
	}	
    
	public String guardar(){
		try{		  		    
		    //genero el prestamo para el usuario
			//for(int i=0;i<this.cantidad;i++){
				PrestamoActual pa = new PrestamoActual();
			    pa.setUsuario(this.getUsuario());
			    pa.setEstacion(this.getEstacionOrigen());
			    pa.setFechaHora(new Date());
			    pa.setBicicleta(bicicleta);
			    FactoryDAO.getPrestamoActualDAO().persistir(pa);
			//}		    		    		
		    return "/prestamos.xhtml?faces-redirect=true";					
		}
		catch(Exception e){
			//algo dio error
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage("Los datos del formulario no son correctos");
			context.addMessage("prestarBici", message);
			return "failure";
		}				
	}
	
	public String devolver(){
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long idPrestamoActual = Long.parseLong(params.get("id"));
        PrestamoActual pa = FactoryDAO.getPrestamoActualDAO().buscaPorID(idPrestamoActual);        
        pa.devolver();                
		//abre la ventana para que elija la estación donde devuelve
        //y tira un memo por si quiere denunciar algo
		return "/prestamos.xhtml?faces-redirect=true";	
	}
    
}
