package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class BaseDAOImpl<T> implements IBaseDAO<T> {
	
	protected Class<T> clase;
	protected EntityManager em;
	
	public BaseDAOImpl(Class<T> unaClase, EntityManager unEntityManager) {
		this.clase = unaClase;
		this.em = unEntityManager;
	}

	@Override
	public T buscaPorID(long id) {
		return em.find(clase, id);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listar() {
		CriteriaBuilder cBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> cQuery = cBuilder.createQuery(clase);
		Root<T> root = cQuery.from(clase);
		cQuery.select(root);
		Query q = em.createQuery(cQuery);
		return q.getResultList();
	}

	@Override
	public void persistir(T obj) {
		try {
			em.getTransaction().begin();
			em.persist(obj);
			em.flush();
			em.getTransaction().commit();		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void update(T obj) {
		try {				
	        em.getTransaction().begin();
	        em.merge(obj);
	        em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error SQL: "+e.getMessage());
		}
		
	}

	@Override
	public void borrar(T obj) {
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error SQL: " + e.getMessage());
		}
		
	}

	@Override
	public void borrarTodos() {
		try {
			em.getTransaction().begin();
			String jpql = String.format("delete from %s", clase.getName());
			Query query = em.createQuery(jpql);
			query.executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error SQL: " + e.getMessage());
		}
		
	}
	
	@Override
	public long contarElementos(){
		CriteriaBuilder cbuilder = em.getCriteriaBuilder();
		CriteriaQuery<Long> cquery = cbuilder.createQuery(Long.class);
		cquery.select(cbuilder.count(cquery.from(clase)));
		return em.createQuery(cquery).getSingleResult().intValue();
		
	}

}
