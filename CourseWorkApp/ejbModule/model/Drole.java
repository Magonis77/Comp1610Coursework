package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the drole database table.
 * 
 */
@Entity
@NamedQuery(name="Drole.findAll", query="SELECT d FROM Drole d")
public class Drole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String role;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="drole")
	private List<User> users;

	public Drole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setDrole(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setDrole(null);

		return user;
	}

}