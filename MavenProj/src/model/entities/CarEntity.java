package model.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "cars")
public class CarEntity {
	@Id
	@Column(name = "carID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int carID;

	@Column(name = "number", length = 6)
	private String number;

	@Column(name = "model", length = 30)
	private String model;

	@Column(name = "mileage")
	private int mileage;

	@ManyToOne
	@JoinColumn(name = "customerID")
	private UserEntity customer;

	@ManyToOne
	@JoinColumn(name = "manufacturerID")
	private ManufacturerEntity manufacturer;

	@OneToMany(mappedBy = "car", targetEntity = OrderEntity.class)
	private List<OrderEntity> orders;

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public UserEntity getCustomer() {
		return customer;
	}

	public void setCustomer(UserEntity customer) {
		this.customer = customer;
	}

	public ManufacturerEntity getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(ManufacturerEntity manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}
}
