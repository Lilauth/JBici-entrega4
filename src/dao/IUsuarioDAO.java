package dao;

import model.Usuario;

public interface IUsuarioDAO extends IBaseDAO<Usuario> {

	Usuario buscarPorEMail(String emailUsuario);		

}
