package dao;

import java.util.List;

import model.PrestamoActual;
import model.Usuario;

public interface IPrestamoActual extends IBaseDAO<PrestamoActual> {
	
	List<PrestamoActual> listar(Usuario usuario);

}
