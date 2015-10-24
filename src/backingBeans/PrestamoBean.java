package backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import dao.FactoryDAO;
import model.Bicicleta;
import model.Denuncia;
import model.Estacion;
import model.EstadoBicicleta;
import model.PrestamoActual;
import model.PrestamoHistorico;
import model.Usuario;

public class PrestamoBean implements Serializable{
		
	private static final long serialVersionUID = 1L;
	private List<Estacion> estaciones;
	private List<Bicicleta> bicicletas;

	private Estacion estacionOrigen; 
	private Estacion estacionDestino;    
	private Bicicleta bicicleta;    
	private PrestamoActual prestamoActual;
    private Usuario usuario; 
    private Denuncia denunciaPrestamo;
    
    @ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
    
    private boolean denuncia;        
    
    public Denuncia getDenunciaPrestamo() {
		return denunciaPrestamo;
	}

	public void setDenunciaPrestamo(Denuncia denunciaPrestamo) {
		this.denunciaPrestamo = denunciaPrestamo;
	}

	public PrestamoActual getPrestamoActual() {
		return prestamoActual;
	}

	public void setPrestamoActual(PrestamoActual prestamoActual) {
		this.prestamoActual = prestamoActual;
	}

	public Estacion getEstacionDestino() {
		return estacionDestino;
	}

	public void setEstacionDestino(Estacion estacionDestino) {
		this.estacionDestino = estacionDestino;
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
	
	public boolean isDenuncia() {
		return denuncia;
	}

	public void setDenuncia(boolean denuncia) {
		this.denuncia = denuncia;
	}
	
	public PrestamoBean(){  
		//recupero el login bean de la sesion activa
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		LoginBean neededBean = (LoginBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "loginBean");
		this.estaciones = FactoryDAO.getEstacionDAO().listar();
		this.bicicletas = new ArrayList<Bicicleta>();
		this.setLoginBean(neededBean);
		this.usuario = neededBean.getUsuario();
    }
	
    public void displayLocation() {
    	
    	FacesMessage msg;
        if(estacionOrigen != null && bicicleta != null){ 
        	
            msg = new FacesMessage("Alta de Préstamo");
           
        }
        else
        	
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected."); 
             
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
    
    public void onChangeEstacion(){
		if(estacionOrigen != null){
		  this.bicicletas.clear();		 
		  List<Bicicleta> bicis = FactoryDAO.getBicicletaDAO().listar();
		  for(Bicicleta b : bicis){
			  if((b.getEstacionActual().equals(this.estacionOrigen)) && (b.getEstadoActual() == EstadoBicicleta.APTA_PARA_USO)){
				bicicletas.add(b);  
			  }
		  }
		  estacionOrigen.setBicicletas(this.bicicletas);
		}
	}
	
	public String nuevo(){		      
        long idUsuario = this.getLoginBean().getIdUsuario();
        this.setUsuario(FactoryDAO.getUsuarioDAO().buscaPorID(idUsuario));
		return "/prestamoActual.xhtml?faces-redirect=true";
	}		
    
	public String guardar(){
		try{		  		    
				PrestamoActual pa = new PrestamoActual();
			    pa.setUsuario(this.getUsuario());
			    pa.setEstacion(this.getEstacionOrigen());
			    pa.setFechaHora(new Date());
			    pa.setBicicleta(bicicleta);
			    FactoryDAO.getPrestamoActualDAO().persistir(pa);		    		    		
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
        long denuncia = Long.parseLong(params.get("denuncia"));        
        this.setDenuncia(denuncia == 1);
        this.setDenunciaPrestamo(new Denuncia());        
        
        this.prestamoActual = FactoryDAO.getPrestamoActualDAO().buscaPorID(idPrestamoActual);        
        
		return "/devolverPrestamo.xhtml?faces-redirect=true";	
	}
	
	public String aceptarDevolucion(){			
		PrestamoHistorico ph = new PrestamoHistorico();
		ph.asignar(prestamoActual);
		ph.setEstacionDevolucion(estacionDestino);
		ph.setFechaHoraDevolucion(new Date());		
		//seteo la ubicación actual en la bicicleta
		Bicicleta b = prestamoActual.getBicicleta();
		b.setEstacionActual(estacionDestino);
		FactoryDAO.getBicicletaDAO().update(b);
		//elimino el préstamo actual
		FactoryDAO.getPrestamoActualDAO().borrar(prestamoActual);
		this.prestamoActual = null;		
		//doy de alta el préstamo histórico
		FactoryDAO.getPrestamoHistoricoDAO().persistir(ph);
		if(denuncia){
			this.denunciaPrestamo.setPrestamoOrigen(ph);
			FactoryDAO.getDenunciaDAO().persistir(this.denunciaPrestamo);
			this.denunciaPrestamo = null;
		}		
		return "/prestamos.xhtml?faces-redirect=true";
	}
	
    
}
