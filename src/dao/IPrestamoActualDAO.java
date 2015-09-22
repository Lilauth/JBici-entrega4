package dao;

import java.util.List;

import model.PrestamoActual;
import model.Usuario;

public interface IPrestamoActualDAO extends IBaseDAO<PrestamoActual> {
	
	List<PrestamoActual> listar(Usuario usuario);

}
