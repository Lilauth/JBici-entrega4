package backingBeans;

import java.util.List;
import dao.FactoryDAO;
import model.Usuario;

public class UsuariosBean {

	public UsuariosBean(){
				
	}

	public List<Usuario> getListaUsuarios(){

		return FactoryDAO.getUsuarioDAO().listar();
	} 

		
}
