package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHandler {
	private static EntityManagerFactory emf;
	/**
	 * @return EntityManager instance from container
	 */
	public static EntityManager getEntityManager(){
		if (emf != null)
			return emf.createEntityManager();
		else {
			emf = Persistence.createEntityManagerFactory("persistence");
			return emf.createEntityManager();
		}
	}
	public static void closeConnections() {
		emf.close();
	}
}
