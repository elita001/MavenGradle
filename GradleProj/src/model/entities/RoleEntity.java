package model.entities;

import java.util.List;

import javax.persistence.*;

@Entity(name = "roles")
public class RoleEntity {
	@Id
	@Column(name = "roleID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int roleID;

	@Column(name = "name", length = 50)
	private String name;

	@OneToMany(mappedBy = "role", targetEntity = UserEntity.class)
	private List<UserEntity> users;

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
}
