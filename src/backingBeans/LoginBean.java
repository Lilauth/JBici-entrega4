package backingBeans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import model.Perfil;
import model.Usuario;
import dao.FactoryDAO;

public class LoginBean {
	
	private String username;
	private String password;
	private long idUsuario;
	private boolean esAdmin;	
	private Usuario usuario;
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LoginBean() {
		this.nuevaSesion();	
	}
		
	public boolean isEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	private void nuevaSesion(){
		this.setUsername("");
		this.setIdUsuario(-1);
		this.setPassword("");
		this.setEsAdmin(false);	
	}
	//behavior
	private boolean match(){
		Usuario user = FactoryDAO.getUsuarioDAO().buscarPorEMail(this.username);
		if(user != null){		
		  this.setEsAdmin(user.getPerfil() == Perfil.ADMINISTRADOR);
		  this.setIdUsuario(user.getId());
		  if(user.getPassword().equals(this.password)){
			  this.setUsuario(user);			  
		  }
		  return(user.getPassword().equals(this.password));
		}
		else{
			return false;
		}
			
	}
	
	public String login() {
	    //primero comprueba datos válidos
		//si son válidos then
		if(this.match()){		
			return "success";
		} 
		else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage("Invalid Username and/or Password.");
			context.addMessage("loginForm", message);
			return "failure";
		}
	}
	
	public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.nuevaSesion();
        return "/login.xhtml?faces-redirect=true";
    }

}
