package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import connection.Conexion;
import model.Usuario;

public class UsuarioDAOImpl extends BaseDAOImpl<Usuario> implements IUsuarioDAO {

	public UsuarioDAOImpl(Class<Usuario> unaClase, EntityManager unEntityManager) {
		super(unaClase, unEntityManager);
	}

	@Override
	public Usuario buscarPorEMail(String emailUsuario) {	
		Query qry = this.em.createQuery("select u from Usuario u where u.email = :email");
		qry.setParameter("email", emailUsuario);
		qry.setMaxResults(1);
		try{
			return ((Usuario) qry.getSingleResult());
		}
		catch (NoResultException nre){
			//
			return null;
		}
	}
	
	@Override
	public void borrar(Usuario u){
		//pasa activo a false
		u.setActivo(false);
		EntityManager em = Conexion.getEntityManager();
		try {				
	        em.getTransaction().begin();
	        em.merge(u);
	        em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error SQL: "+e.getMessage());
		}
	}
	
	@Override
	public List<Usuario> listar(){
		List<Usuario> uActivos = new ArrayList<Usuario>();
		List<Usuario> usuarios = super.listar();
		for(Usuario u: usuarios){
			if(u.getActivo()){
				uActivos.add(u);
			}
		}
		return uActivos;
	}

}
