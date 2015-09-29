package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.PrestamoActual;
import model.Usuario;

public class PrestamoActualDAOImpl extends BaseDAOImpl<PrestamoActual> implements IPrestamoActualDAO {		

	public PrestamoActualDAOImpl(Class<PrestamoActual> unaClase,EntityManager unEntityManager) {
		super(unaClase, unEntityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PrestamoActual> listar(Usuario usuario) {		
		Query qry = this.em.createQuery("select p from PrestamoActual p where idUsuario = :id_usuario");		
		qry.setParameter("id_usuario", usuario.getId());		
		try{			
			@SuppressWarnings("unchecked")
			List<PrestamoActual> p = qry.getResultList();
			return p;
		}
		catch (NoResultException nre){
			//
			return null;
		}
	}

}
