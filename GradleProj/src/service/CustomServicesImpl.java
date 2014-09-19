package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import mapping.Mapper;
import model.domain.Car;
import model.domain.Service;
import service.interfaces.CustomServices;

public class CustomServicesImpl implements CustomServices{
	private EntityManager em;
	private Mapper mapper;
	
	public CustomServicesImpl() {
		em = EntityManagerHandler.getEntityManager();
		mapper = new Mapper();
	}
	@Override
	public double getAverageOrderPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getServicedCars() {
		//Query query = em.createQuery("SELECT c FROM cars c WHERE c.status = :status")
		//		.setParameter("status", "processing").setFirstResult(0).setMaxResults(100);
		//List<Car> result = new ArrayList<Car>();
		//query.getResultList().stream().forEach(entity -> result.add(mapper.map(entity, Car.class)));
		//return result;
		return null;
	}

	@Override
	public double getDoneOrdersPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void increasePrice(double percent, Service service) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decreasePrice(double percent, Service service) {
		// TODO Auto-generated method stub
		
	}

}
