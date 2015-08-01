package backingBeans;

import java.util.List;

import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import dao.FactoryDAO;
import model.Usuario;

public class UsuariosBean {
	private List<Usuario> listaUsuarios;
	private DataModel<Usuario> usuariosList;	
	
	public UsuariosBean(){
		//inicializo lista de usuarios
		this.setListaUsuarios(FactoryDAO.getUsuarioDAO().listar());
		//this.usuariosList = new ArrayDataModel<Usuario>((Usuario[]) this.getListaUsuarios().toArray());
		this.usuariosList = new ArrayDataModel<Usuario>(); 
	}
	
	public void setListaUsuarios(List<Usuario> l){
		this.listaUsuarios = l;
	}
	
	public List<Usuario> getListaUsuarios(){		
		return this.listaUsuarios;
	} 
	
	public DataModel<Usuario> getUsuariosList(){
		this.usuariosList = new ArrayDataModel<Usuario>(this.listaUsuarios.toArray(new Usuario[this.listaUsuarios.size()]));		
		return this.usuariosList;
	}

		
}
