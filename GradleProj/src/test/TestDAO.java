package test;

import java.util.List;

import model.domain.Car;
import model.domain.Role;
import model.entities.CarEntity;
import model.entities.RoleEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.CustomServicesImpl;
import service.DomainDAO;
import service.EntityManagerHandler;
import service.interfaces.GenericDAO;

public class TestDAO {
	final static int CAR_FIND_TEST_ID = 5;
	final static int CAR_DELETE_TEST_ID = 3;
	final static int TEST_MILEAGE = 1000;
	final static String TEST_ROLE_NAME = "MyOwnRole";
	final static Logger logger = LoggerFactory.getLogger("app.Logger");

	public static void main(String[] args) {
		logger.debug("Start examples");

		// Test custom services
		//CustomServicesImpl csi = new CustomServicesImpl();
		//List<Car> servicedCars = csi.getServicedCars();
		//Car firstServicedCar = servicedCars.get(0);
		//logger.debug("Get list of serviced Cars. Example: first Car with id "
		//		+ firstServicedCar.getCarID());

		// Test DomainDAO
		// Test getOne():
		GenericDAO<Car> gdCar = new DomainDAO<Car, CarEntity>(Car.class,
				CarEntity.class);
		Car c = gdCar.getOne(CAR_FIND_TEST_ID);
		logger.debug("Get Car with id " + CAR_FIND_TEST_ID + ": mileage="
				+ c.getMileage() + "; model=" + c.getModel() + "; number="
				+ c.getNumber());

		// Test update():
		c.setMileage(TEST_MILEAGE);
		gdCar.update(c);
		logger.debug("Car with id " + CAR_FIND_TEST_ID + " updated: mileage="
				+ TEST_MILEAGE);

		// Test delete():
		gdCar.delete(CAR_DELETE_TEST_ID);
		logger.debug("Car with id " + CAR_DELETE_TEST_ID + " deleted");

		// Test getAll():
		GenericDAO<Role> gdRole = new DomainDAO<Role, RoleEntity>(Role.class,
				RoleEntity.class);
		List<Role> roles = gdRole.getAll();
		Role r = roles.get(0);
		logger.debug("Get all roles. First role: name=" + r.getName());

		// Test create():
		r = new Role();
		r.setName(TEST_ROLE_NAME);
		r = gdRole.create(r);
		logger.debug("New role with name " + TEST_ROLE_NAME
				+ " added to database");
		logger.debug("Examples done");
		EntityManagerHandler.closeConnections();
		logger.debug("Connections closed");
	}

}
