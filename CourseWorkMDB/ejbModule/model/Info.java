package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the info database table.
 * 
 */
@Entity
@NamedQuery(name="Info.findAll", query="SELECT i FROM Info i")
public class Info implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String firstname;

	@Column(name="`Join Date`")
	private String join_Date;

	private String lastname;

	//bi-directional many-to-one association to Drole
	@ManyToOne
	@JoinColumn(name="RoleID")
	private Drole drole;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="DepartmentID")
	private Department department;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="infos")
	private List<User> users;

	public Info() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getJoin_Date() {
		return this.join_Date;
	}

	public void setJoin_Date(String join_Date) {
		this.join_Date = join_Date;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Drole getDrole() {
		return this.drole;
	}

	public void setDrole(Drole drole) {
		this.drole = drole;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}