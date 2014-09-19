package service;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import service.interfaces.GenericDAO;

public class EntityDAO<EntityType> implements GenericDAO<EntityType> {
	protected Class<EntityType> entityType;
	private String entityName;

	public EntityDAO(Class<EntityType> entityType) {
		this.entityType = entityType;
		entityName = entityType.getAnnotation(Entity.class).name();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityType> getAll() {
		EntityManager em = EntityManagerHandler.getEntityManager();
		try {
			System.out.println(entityName);
			Query query = em.createQuery("SELECT entity FROM " + entityName
					+ " entity");
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public EntityType update(EntityType entity) {
		EntityManager em = EntityManagerHandler.getEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			EntityType ent = em.merge(entity);
			transaction.commit();
			return ent;
		} finally {
			em.close();
		}
	}

	@Override
	public EntityType getOne(int id) {
		EntityManager em = EntityManagerHandler.getEntityManager();
		try {
			return em.find(entityType, id);
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(int id) {
		EntityManager em = EntityManagerHandler.getEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(getOne(id));
			transaction.commit();
		} finally {
			em.close();
		}
	}

	@Override
	public EntityType create(EntityType entity) {
		EntityManager em = EntityManagerHandler.getEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.persist(entity);
			em.flush();
			transaction.commit();
			return entity;
		} finally {
			em.close();
		}
	}
}
