package backingBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
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
    
/**
 * muchas pruebas ajax*/
    private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
    private String country; 
    private String city;  
    private Map<String,String> countries;
    private Map<String,String> cities;
 
    public Map<String, Map<String, String>> getData() {
        return data;
    }
 
    public String getCountry() {
        return country;
    }
 
    public void setCountry(String country) {
        this.country = country;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
 
    public Map<String, String> getCountries() {
        return countries;
    }
 
    public Map<String, String> getCities() {
        return cities;
    }
 
    public void onCountryChange() {    	
    	System.out.println("ejecuta onchange");
    	 if(country !=null && !country.equals(""))
             cities = data.get(country);
         else
             cities = new HashMap<String, String>();
    }
     
    public void displayLocation() {
    	FacesMessage msg;
        if(city != null && country != null){
        	
            msg = new FacesMessage("Selected", city + " of " + country);
           
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
		this.bicicletas = FactoryDAO.getBicicletaDAO().listar();
		
		/*pruebas ajax*/
		countries  = new HashMap<String, String>();
        countries.put("USA", "USA");
        countries.put("Germany", "Germany");
        countries.put("Brazil", "Brazil");
         
        Map<String,String> map = new HashMap<String, String>();
        map.put("New York", "New York");
        map.put("San Francisco", "San Francisco");
        map.put("Denver", "Denver");
        data.put("USA", map);
         
        map = new HashMap<String, String>();
        map.put("Berlin", "Berlin");
        map.put("Munich", "Munich");
        map.put("Frankfurt", "Frankfurt");
        data.put("Germany", map);
         
        map = new HashMap<String, String>();
        map.put("Sao Paolo", "Sao Paolo");
        map.put("Rio de Janerio", "Rio de Janerio");
        map.put("Salvador", "Salvador");
        data.put("Brazil", map);
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
