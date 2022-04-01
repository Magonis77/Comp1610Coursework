package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class HolidaysDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;

	private Date end_Date;

	private int lenght;

	private Date start_Date;

	private String status;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="holidays")
	private List<UserDTO> users;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStart_Date() {
		return start_Date;
	}

	public void setStart_Date(Date start_Date) {
		this.start_Date = start_Date;
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public Date getEnd_Date() {
		return end_Date;
	}

	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public HolidaysDTO() {
		
	}
	public HolidaysDTO(int id, Date start_Date,String Status, int Lenght, Date end_Date){
	this.status = Status;
	this.start_Date = start_Date;
	this.lenght = Lenght;
	this.end_Date = end_Date;
	this.users = new ArrayList<UserDTO>();
	this.id = id;
	}

	private List<UserDTO> getUsers() {
		return users;
	}

	private void setUsers(List<UserDTO> users) {
		this.users = users;
	}
}
