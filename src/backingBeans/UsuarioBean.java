package backingBeans;

import java.io.Serializable;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import dao.FactoryDAO;
import dao.IUsuarioDAO;
import model.Perfil;
import model.Usuario;


public class UsuarioBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;	
	private Perfil[] perfilesDisponibles;
	private Usuario usuario;
	private Operacion oper;
	
	public UsuarioBean() {		
		perfilesDisponibles = Perfil.values();

	}
	
	public Perfil[] getPerfilesDisponibles(){		
		return this.perfilesDisponibles;
	}	

	public Usuario getUsuario() {
		return usuario;
	}
	
	public boolean getEsAlta() {
		return (oper == Operacion.NUEVO_USUARIO) || (oper == Operacion.REGISTRARSE);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	
	public String editarUsuario() {
		oper = Operacion.EDITAR_USUARIO;
		
		// Recupero el idUsuario pasado x parametro
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long idUsuario = Long.parseLong(params.get("id"));        
                
   	    //cargar usuario por id        	
   	    usuario = FactoryDAO.getUsuarioDAO().buscaPorID(idUsuario);

   	    
   	    return "/usuario.xhtml?faces-redirect=true";
	}
	
	public String eliminarUsuario(){
		oper = Operacion.ELIMINAR_USUARIO;
		
		// Recupero el idUsuario pasado x parametro 
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long idUsuario = Long.parseLong(params.get("id"));   
        
        IUsuarioDAO daoUsuario = FactoryDAO.getUsuarioDAO(); 
        // cargar usuario por id 
   	    usuario = daoUsuario.buscaPorID(idUsuario);
   	    
   	    // Borro el usuario
   	    daoUsuario.borrar(usuario);
   	    
   	    // Retorno al listado de usuarios (solo el admin puede borrar usuarios desde el listado de usuarios)
		return "/usuarios.xhtml?faces-redirect=true"; 
	}
	

	public String nuevoUsuario(){		
		oper = Operacion.NUEVO_USUARIO; 
		
    	usuario = new Usuario();    	
    	usuario.setPerfil(Perfil.USUARIO);
    	usuario.setActivo(true);
    	    	
		return "/usuario.xhtml?faces-redirect=true";
	}
	
	public String modificarDatos(){		
		oper = Operacion.MODIFICAR_DATOS; 
		
		// Recupero el idUsuario pasado x parametro 
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long idUsuario = Long.parseLong(params.get("id"));   
        
        // cargar usuario por id 
   	    usuario = FactoryDAO.getUsuarioDAO().buscaPorID(idUsuario);
		
		return "/usuario.xhtml?faces-redirect=true";
	}
	
	public String registrarse(){		
		oper = Operacion.REGISTRARSE; 
		
		usuario = new Usuario();
		usuario.setPerfil(Perfil.USUARIO);
		usuario.setActivo(true);
		
		return "/usuario.xhtml?faces-redirect=true";
	}
	
	public String guardar(){
		
		try{		  
			this.persistir();
			
			switch (oper) {
				case NUEVO_USUARIO:
					return "/usuarios.xhtml?faces-redirect=true"; 
					
				case EDITAR_USUARIO:
					return "/usuarios.xhtml?faces-redirect=true"; 	
					
				case REGISTRARSE:
					return "/login.xhtml?faces-redirect=true"; 
					
				default:
					return "/bienvenido.xhtml?faces-redirect=true"; 
			}
		  

		}
		catch(Exception e){
			// TODO revisar que hacer cuando da error
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage("Invalid Username and/or Password.");
			context.addMessage("newUserForm", message);
			return "failure";
		}				
	}	
	
	public String getCancelarURL(){
		
		switch (oper) {
			case NUEVO_USUARIO:
				return "./usuarios.xhtml?faces-redirect=true"; 
				
			case EDITAR_USUARIO:
				return "./usuarios.xhtml?faces-redirect=true"; 	
				
			case REGISTRARSE:
				return "./login.xhtml?faces-redirect=true"; 	
				
			default:
				return "./bienvenido.xhtml?faces-redirect=true"; 
		}
			
	}	

	
	private boolean persistir(){
		//crea el dao y persiste al nuevo usuario		
		if(this.getEsAlta()) { 
			//alta			
			FactoryDAO.getUsuarioDAO().persistir(this.getUsuario());
		}
		else { 
			//modificacion			
			FactoryDAO.getUsuarioDAO().update(this.getUsuario());
		} 		
		return true;
	}

	private enum Operacion {REGISTRARSE, MODIFICAR_DATOS, NUEVO_USUARIO, EDITAR_USUARIO, ELIMINAR_USUARIO}
	
}
