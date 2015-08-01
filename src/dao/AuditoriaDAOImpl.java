package dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import connection.Conexion;
import aspects.Auditoria;

public class AuditoriaDAOImpl {
	private String st;
//	private Query query;
	
	public AuditoriaDAOImpl(){
		this.st = ("SELECT COUNT(a) ");
		this.st = this.st +   ("FROM Auditoria a ");
		this.st = this.st +   ("WHERE ((idEnClaseObjetivo = :param1) or (:param1 = -1))");
		this.st = this.st +   ("AND (operacion = :param2) ");
		this.st = this.st +   ("AND (claseObjetivo = :param3) ");
	}
	
	public void guardar(Auditoria a){
		EntityManager em = Conexion.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(a);
			em.flush();
			em.getTransaction().commit();		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public long altasEnEntidad(long id, String entidad){
		EntityManager em = Conexion.getEntityManager();
	//	Query query = em.createQuery("SELECT COUNT(a) FROM Auditoria a WHERE (idEnClaseObjetivo = :param1) and (operacion = :param2) and (claseObjetivo = :param3)");
		Query query = em.createQuery(this.st);
		query.setParameter("param1", id);
		query.setParameter("param2", "void dao.BaseDAOImpl.persistir(Object)");
		query.setParameter("param3", entidad);
		Object result = query.getSingleResult();
		return ((Long) result);
	}
	
	public long altasGenerales(String entidad){		
	  return altasEnEntidad(-1, entidad);	
	}
	
	public long modificacionesEnEntidad(long id, String entidad){
		EntityManager em = Conexion.getEntityManager();			
		Query query = em.createQuery(this.st);
		query.setParameter("param1", id);
		query.setParameter("param2", "void dao.BaseDAOImpl.update(Object)");
		query.setParameter("param3", entidad);
		Object result = query.getSingleResult();
		return ((Long) result);
	}
	
	public long modificacionesGenerales(String entidad){
		return modificacionesEnEntidad(-1, entidad);
	}
	
	public long bajasEnEntidad(long id, String entidad){
		EntityManager em = Conexion.getEntityManager();			
		Query query = em.createQuery(this.st);
		query.setParameter("param1", id);
		query.setParameter("param2", "void dao.UsuarioDAOImpl.borrar(Usuario)");
		query.setParameter("param3", entidad);
		Object result = query.getSingleResult();
		return ((Long) result);
	}
	
	public long bajasGenerales(String entidad){
		return bajasEnEntidad(-1, entidad);
	}

}
