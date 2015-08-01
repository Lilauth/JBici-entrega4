package backingBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dao.FactoryDAO;
import model.Usuario;

public class UsuarioBean {
	//att
	private long id;	
	private String perfil;
	private List<String> perfilesDisponibles;
	private Usuario usuario2;
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
		perfilesDisponibles = new ArrayList<String>();
		perfilesDisponibles.add(FactoryDAO.getPerfilDAO().buscaPorID(1).getDescripcion());
		perfilesDisponibles.add(FactoryDAO.getPerfilDAO().buscaPorID(2).getDescripcion());
	}
	
	//setters and getters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
		
	public String getPerfil() {
		return perfil;
	}
	
	public List<String> getPerfilesDisponibles(){		
		return this.perfilesDisponibles;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}		
		
	public Usuario getUsuario2() {
		return usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}
	
	//behavior
	@PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long selectedEntityId = Long.parseLong(params.get("id"));        
        this.setOper((params.get("oper"))); 
        //cargar usuario por id        
        if(!this.getOper().equals("A")) { //si es A, es un registro
        	this.setEsAlta(false);
        	 //cargar usuario por id        	
        	 this.setUsuario2(FactoryDAO.getUsuarioDAO().buscaPorID(selectedEntityId));
        	 if(this.getOper().equals("B")){ //quieren eliminar al usuario
        		 FactoryDAO.getUsuarioDAO().borrar(this.getUsuario2());         		
        	 }        	 
        }
        else{
        	this.setEsAlta(true);
        	this.setUsuario2(new Usuario());
        	this.getUsuario2().setId(0);
        	this.getUsuario2().setPerfil(FactoryDAO.getPerfilDAO().buscaPorID(2));
        	this.getUsuario2().setActivo(true);
        }
    }	
	
	private boolean guardar(){
		//crea el dao y persiste al nuevo usuario
		if(this.getUsuario2().getId() == 0){ //alta
			//this.getUsuario2().setPerfil(FactoryDAO.getPerfilDAO().buscaPorID(2));
		  FactoryDAO.getUsuarioDAO().persistir(this.getUsuario2());
		}
		else{
			if(this.oper.equals('M')){ //modificación
			  FactoryDAO.getUsuarioDAO().update(this.getUsuario2());
			}
			else{//borrar
			  FactoryDAO.getUsuarioDAO().borrar(this.getUsuario2());
			}
		}
		return true;
	}
	
	public String registrar(){
		try{		  
		  this.guardar();
		  //limpiar la variable usuario2
		  return "success";
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
