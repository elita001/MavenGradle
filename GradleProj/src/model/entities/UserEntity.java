package model.entities;

import java.util.List;

import javax.persistence.*;

@Entity(name = "users")
public class UserEntity {
	@Id
	@Column(name = "userID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userID;

	@Column(name = "firstName", length = 50)
	private String firstName;

	@Column(name = "lastName", length = 50)
	private String lastName;

	@Column(name = "phoneNumber")
	private int phoneNumber;

	@Column(name = "email", length = 50)
	private String email;

	@ManyToOne
	@JoinColumn(name = "roleID", referencedColumnName = "roleID")
	private RoleEntity role;

	@OneToMany(mappedBy = "customer", targetEntity = CarEntity.class)
	private List<CarEntity> cars;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public List<CarEntity> getCars() {
		return cars;
	}

	public void setCars(List<CarEntity> cars) {
		this.cars = cars;
	}
}
