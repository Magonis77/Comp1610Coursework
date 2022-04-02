package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String password;

	private String username;

	//bi-directional many-to-many association to Holiday
	@ManyToMany
	@JoinTable(
		name="user_holidays"
		, joinColumns={
			@JoinColumn(name="Users_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Holidays_ID")
			}
		)
	private List<Holiday> holidays;

	//bi-directional many-to-many association to Info
	@ManyToMany
	@JoinTable(
		name="user_info"
		, joinColumns={
			@JoinColumn(name="Users_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Infos_ID")
			}
		)
	private List<Info> infos;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public List<Info> getInfos() {
		return this.infos;
	}

	public void setInfos(List<Info> infos) {
		this.infos = infos;
	}

}