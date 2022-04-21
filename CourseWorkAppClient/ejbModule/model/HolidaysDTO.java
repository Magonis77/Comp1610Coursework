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
	private Date date_Decision_Made;
	private Date request_Made_Date;
	private String status;
	private UserDTO user;
	private int overall_lenght;
	private String peak_time;
	private String constraints;

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
	
	public HolidaysDTO(int Lenght) {
	this.lenght = Lenght;
	}
	public HolidaysDTO(int id, Date start_Date,String Status, int Lenght, Date end_Date, Date decisiondate, Date requestdate, int overall_lenght, String peak_time, String constraints){
	this.status = Status;
	this.start_Date = start_Date;
	this.lenght = Lenght;
	this.end_Date = end_Date;
	this.id = id;
	this.request_Made_Date = requestdate;
	this.date_Decision_Made = decisiondate;
	this.overall_lenght = overall_lenght;
	this.peak_time= peak_time;
	this.constraints = constraints;
	}


	public Date getDate_Decision_Made() {
		return date_Decision_Made;
	}

	public void setDate_Decision_Made(Date date_Decision_Made) {
		this.date_Decision_Made = date_Decision_Made;
	}

	public Date getRequest_Made_Date() {
		return request_Made_Date;
	}

	public void setRequest_Made_Date(Date request_Made_Date) {
		this.request_Made_Date = request_Made_Date;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public int getOverall_lenght() {
		return overall_lenght;
	}

	public void setOverall_lenght(int overall_lenght) {
		this.overall_lenght = overall_lenght;
	}

	public String getPeak_time() {
		return peak_time;
	}

	public void setPeak_time(String peak_time) {
		this.peak_time = peak_time;
	}

	public String getConstraints() {
		return constraints;
	}

	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}
}
