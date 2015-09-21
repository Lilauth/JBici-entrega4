package backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dao.FactoryDAO;
import model.Perfil;
import model.Usuario;

public class UsuarioBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//att
	private long id;	
	private Perfil perfil;
	private Perfil[] perfilesDisponibles;
	private Usuario usuario;
	private String oper;
	private boolean esAlta;	
	

	public boolean getEsAlta() {
		return esAlta;
	}

	public void setEsAlta(boolean esAlta) {
		this.esAlta = esAlta;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	//const
	public UsuarioBean(){		
		perfilesDisponibles = Perfil.values();

	}
	
	//setters and getters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
		
	public Perfil getPerfil() {
		return perfil;
	}
	
	public Perfil[] getPerfilesDisponibles(){		
		return this.perfilesDisponibles;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}		
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario2) {
		this.usuario = usuario2;
	}
	
	public String editar(){
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long selectedEntityId = Long.parseLong(params.get("id"));        
        this.setOper("M"); 
        this.setEsAlta(false);
   	    //cargar usuario por id        	
   	    this.setUsuario(FactoryDAO.getUsuarioDAO().buscaPorID(selectedEntityId));
   	    id = selectedEntityId;
   	    this.perfil = this.getUsuario().getPerfil();
   	    return "/usuario.xhtml?faces-redirect=true";
	}
	
	public String eliminar(){
		this.setEsAlta(false);
   	    //cargar usuario por id  
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long selectedEntityId = Long.parseLong(params.get("id"));        
   	    this.setUsuario(FactoryDAO.getUsuarioDAO().buscaPorID(selectedEntityId));
   	    FactoryDAO.getUsuarioDAO().borrar(this.getUsuario());
		return "/usuarios.xhtml?faces-redirect=true"; 
	}
	
	private boolean guardar(){
		//crea el dao y persiste al nuevo usuario		
		if(this.getUsuario().getId() == 0){ //alta			
		  FactoryDAO.getUsuarioDAO().persistir(this.getUsuario());
		}
		else{ //modificación
			/*if(perfil.equals("administrador")){
				usuario.setPerfil(FactoryDAO.getPerfilDAO().buscaPorID(1));
			} 
			else{
				usuario.setPerfil(FactoryDAO.getPerfilDAO().buscaPorID(2));
			}*/
			usuario.setPerfil(perfil);
			FactoryDAO.getUsuarioDAO().update(this.getUsuario());
			} 		
		return true;
	}
	
	public String nuevo(){		
        this.setOper("A"); 
        this.setEsAlta(true);
    	this.setUsuario(new Usuario());
    	this.getUsuario().setId(0);
    	this.getUsuario().setPerfil(Perfil.USUARIO);
    	this.getUsuario().setActivo(true);
		return "/usuario.xhtml?faces-redirect=true";
	}
	
	public String registrar(){
		try{		  
		  this.guardar();
		  if(this.getOper().equals("A")){
			  return "success";
			  }
		  else{			 			 
			 return "/bienvenido.xhtml?faces-redirect=true"; 
		  }
		}
		catch(Exception e){
			//algo dio error
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage("Invalid Username and/or Password.");
			context.addMessage("newUserForm", message);
			return "failure";
		}				
	}		

}
