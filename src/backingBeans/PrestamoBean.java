package backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import dao.FactoryDAO;
import model.Bicicleta;
import model.Estacion;
import model.PrestamoActual;
import model.Usuario;

public class PrestamoBean implements Serializable{
		
	private static final long serialVersionUID = 1L;
	private List<Estacion> estaciones;
    private Estacion estacionOrigen; 
    private Bicicleta bicicleta;
    private int cantidad;
    private Usuario usuario; 
    //variables agregadas para probar ajax
    private String name;
    private List<Bicicleta> cars;
    private String selectedCar;
    private HtmlSelectOneMenu htmlSelectCars;
    
    private int idSelectedStation;
    
    public int getIdSelectedStation(){
    	return this.idSelectedStation;
    }
    
    public void setIdSelectedStation(int idEstacion){
    	this.idSelectedStation = idEstacion;
    } 
    
    //funciones agregadas para probar AJAX
    public void setCars(List<Bicicleta> cars){
    	this.cars = cars;
    }
    
    public HtmlSelectOneMenu getHtmlSelectCars() {
		return htmlSelectCars;
	}

	public void setHtmlSelectCars(HtmlSelectOneMenu htmlSelectCars) {
		this.htmlSelectCars = htmlSelectCars;
	}
	
	public void editMyCarsList(AjaxBehaviorEvent event) {
        if (htmlSelectCars == null) {
            htmlSelectCars = new HtmlSelectOneMenu();
        }
 
        htmlSelectCars.getChildren().clear();
 
        UISelectItems items = new UISelectItems();
        items.setValue(getCars());
        htmlSelectCars.getChildren().add(items);
    }

	private boolean isNameInCorrect() {
        return name == null || "".equals(name.trim()) || name.length() < 3;
    }
    
    public String getSelectedCar() {
		return selectedCar;
	}

	public void setSelectedCar(String selectedCar) {
		this.selectedCar = selectedCar;
	}

	public List<Bicicleta> getCars() {
            cars.clear();
            cars = (FactoryDAO.getEstacionDAO().buscaPorID(idSelectedStation)).getBicicletasDisponibles();         
            return cars;
    }
    
    
    public void setName(String aName){
    	this.name= aName;
    }
    
    public String getName(){
    	return this.name;
    }
    
    public String sayHello() {
        if (isNameInCorrect()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Too small", "Can you write it a little bigger?"));
        }        
        return null;
    }
    
    //funciones agregadas para probar AJAX
    
    
	public Bicicleta getBicicleta() {
		return bicicleta;
	}


	public void setBicicleta(Bicicleta bicicleta) {
		this.bicicleta = bicicleta;
	}


	public PrestamoBean(){    	
    	this.estaciones = FactoryDAO.getEstacionDAO().listar();	
    	this.estacionOrigen = FactoryDAO.getEstacionDAO().buscaPorID(1);
    	this.setCantidad(1);
    	//quitar!!!
    	this.cars = new ArrayList<Bicicleta>();
    	
    }
       
    
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
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long idUsuario = Long.parseLong(params.get("idUsuario"));
        this.setUsuario(FactoryDAO.getUsuarioDAO().buscaPorID(idUsuario));
		return "/prestamoActual.xhtml?faces-redirect=true";
	}	
    
	public String guardar(){
		try{		  		    
		    //genero el prestamo para el usuario
			for(int i=0;i<=this.cantidad;i++){
				PrestamoActual pa = new PrestamoActual();
			    pa.setUsuario(this.getUsuario());
			    pa.setEstacion(this.getEstacionOrigen());
			    pa.setFechaHora(new Date());
			    FactoryDAO.getPrestamoActualDAO().persistir(pa);
			}		    		    		
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
