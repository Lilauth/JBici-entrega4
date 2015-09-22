package dao;

import java.util.List;

import javax.persistence.EntityManager;

import model.PrestamoActual;
import model.Usuario;

public class PrestamoActualDAOImpl extends BaseDAOImpl<PrestamoActual> implements IPrestamoActual {		

	public PrestamoActualDAOImpl(Class<PrestamoActual> unaClase,EntityManager unEntityManager) {
		super(unaClase, unEntityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PrestamoActual> listar(Usuario usuario) {
		//lista los prestamos actuales de un usuario dado
			
		return null;
	}

}
