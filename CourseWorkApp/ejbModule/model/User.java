package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQueries(
		{
			@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
@NamedQuery(name="User.findHolidaysByUserID", query="Select u from User u join fetch u.holidays Where u.id=:id"),
@NamedQuery(name="User.findusersbydepartmentid", query="Select u from User u Where u.department=:department"),
@NamedQuery(name="User.findusersbydepartmentidandrole", query="Select u from User u Where u.department=:department and u.drole.role=:role")
		}
		)
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String firstname;

	private String gender;

	@Temporal(TemporalType.DATE)
	private Date joinDate;

	private String lastname;

	private String password;

	private String status;

	private String username;

	//bi-directional many-to-one association to Holiday
	@OneToMany(mappedBy="user")
	private List<Holiday> holidays;

	//bi-directional many-to-one association to Department
	@ManyToOne
	private Department department;

	//bi-directional many-to-one association to Drole
	@ManyToOne
	@JoinColumn(name="Role_ID")
	private Drole drole;

	public User() {
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

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Holiday> getHolidays() {
		return this.holidays;
	}

	public void setHolidays(List<Holiday> holidays) {
		this.holidays = holidays;
	}

	public Holiday addHoliday(Holiday holiday) {
		getHolidays().add(holiday);
		holiday.setUser(this);

		return holiday;
	}

	public Holiday removeHoliday(Holiday holiday) {
		getHolidays().remove(holiday);
		holiday.setUser(null);

		return holiday;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Drole getDrole() {
		return this.drole;
	}

	public void setDrole(Drole drole) {
		this.drole = drole;
	}

}