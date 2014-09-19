package service.interfaces;

import java.util.List;

import model.domain.Car;
import model.domain.Service;

public interface CustomServices {
	public double getAverageOrderPrice();

	public List<Car> getServicedCars();

	public double getDoneOrdersPercentage();

	public void increasePrice(double percent, Service service);

	public void decreasePrice(double percent, Service service);
}
