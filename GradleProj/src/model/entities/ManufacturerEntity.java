package model.entities;

import java.util.List;

import javax.persistence.*;

@Entity(name = "manufacturers")
public class ManufacturerEntity {
	@Id
	@Column(name = "manufacturerID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int manufacturerID;

	@Column(name = "name", length = 50)
	private String name;

	@OneToMany(mappedBy = "manufacturer", targetEntity = CarEntity.class)
	private List<CarEntity> cars;

	public int getManufacturerID() {
		return manufacturerID;
	}

	public void setManufacturerID(int manufacturerID) {
		this.manufacturerID = manufacturerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CarEntity> getCars() {
		return cars;
	}

	public void setCars(List<CarEntity> cars) {
		this.cars = cars;
	}
}
