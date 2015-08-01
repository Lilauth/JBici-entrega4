package connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexion {
	
	private static EntityManager entityManager;
	
	public static EntityManager getEntityManager() {
		if (entityManager == null || !entityManager.isOpen()) {           	
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("jyaa");            
            entityManager = emf.createEntityManager();              
        }       
        return entityManager;
    }   

}
