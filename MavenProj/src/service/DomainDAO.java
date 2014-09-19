package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mapping.Mapper;
import service.interfaces.GenericDAO;

public class DomainDAO<DomainType, EntityType> implements
		GenericDAO<DomainType> {
	@PersistenceContext
	protected Class<DomainType> domainType;
	protected Class<EntityType> entityType;
	private Mapper mapper;
	private String entityName;

	public DomainDAO(Class<DomainType> domainType, Class<EntityType> entityType) {
		this.entityType = entityType;
		this.domainType = domainType;
		mapper = new Mapper();
		entityName = entityType.getAnnotation(Entity.class).name();
	}

	@Override
	public DomainType create(DomainType domainEntity) {
		EntityManager em = EntityManagerHandler.getEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			EntityType entity = mapper.map(domainEntity, entityType);
			transaction.begin();
			em.persist(entity);
			em.flush();
			transaction.commit();
			return mapper.map(entity, domainType);
		} finally {
			em.close();
		}
	}

	@Override
	public DomainType update(DomainType domainEntity) {
		EntityManager em = EntityManagerHandler.getEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			EntityType entity = mapper.map(domainEntity, entityType);
			transaction.begin();
			entity = em.merge(entity);
			transaction.commit();
			return mapper.map(entity, domainType);
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DomainType> getAll() {
		EntityManager em = EntityManagerHandler.getEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			Query query = em.createQuery("SELECT entity FROM " + entityName
					+ " entity");
			List<DomainType> result = new ArrayList<DomainType>();
			query.getResultList()
					.stream()
					.forEach(
							entity -> result.add(mapper.map(entity, domainType)));
			transaction.commit();
			return result;
		} finally {
			em.close();
		}
	}

	@Override
	public DomainType getOne(int id) {
		EntityManager em = EntityManagerHandler.getEntityManager();
		try {
			return mapper.map(em.find(entityType, id), domainType);
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(int id) {
		EntityManager em = EntityManagerHandler.getEntityManager();
		try {
			em.remove(em.find(entityType, id));
		} finally {
			em.close();
		}
	}
}
