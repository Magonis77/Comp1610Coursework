package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the holidays database table.
 * 
 */
@Entity
@Table(name="holidays")
@NamedQueries(
		{
@NamedQuery(name="Holiday.findAll", query="SELECT h FROM Holiday h"),
@NamedQuery(name="Holiday.findHolidaysByUserID", query="Select h from Holiday h Where h.user.id=:id"),
@NamedQuery(name="Holiday.findOutstandingHolidays", query="Select h from Holiday h Where h.status=:status and h.contraintbreak=:cid order by h.peak_Time,h.overall_Length"),
@NamedQuery(name="Holiday.findHolidaysByUserIDandstatus", query="Select h from Holiday h Where h.user.id=:id and h.status=:status"),
@NamedQuery(name="Holiday.findOutstandingHolidayswconstraint", query="Select h from Holiday h Where h.status=:status and h.contraintbreak=:cid"),
		}
		)

public class Holiday implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String constraints;

	private int contraintbreak;

	@Temporal(TemporalType.DATE)
	@Column(name="`Date Decision Made`")
	private Date date_Decision_Made;

	@Temporal(TemporalType.DATE)
	@Column(name="`End Date`")
	private Date end_Date;

	private int lenght;

	@Column(name="`Overall Length`")
	private int overall_Length;

	@Column(name="`Peak Time`")
	private String peak_Time;

	@Temporal(TemporalType.DATE)
	@Column(name="`Request Made Date`")
	private Date request_Made_Date;

	@Temporal(TemporalType.DATE)
	@Column(name="`Start Date`")
	private Date start_Date;

	private String status;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Holiday() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConstraints() {
		return this.constraints;
	}

	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}

	public int getContraintbreak() {
		return this.contraintbreak;
	}

	public void setContraintbreak(int contraintbreak) {
		this.contraintbreak = contraintbreak;
	}

	public Date getDate_Decision_Made() {
		return this.date_Decision_Made;
	}

	public void setDate_Decision_Made(Date date_Decision_Made) {
		this.date_Decision_Made = date_Decision_Made;
	}

	public Date getEnd_Date() {
		return this.end_Date;
	}

	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}

	public int getLenght() {
		return this.lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public int getOverall_Length() {
		return this.overall_Length;
	}

	public void setOverall_Length(int overall_Length) {
		this.overall_Length = overall_Length;
	}

	public String getPeak_Time() {
		return this.peak_Time;
	}

	public void setPeak_Time(String peak_Time) {
		this.peak_Time = peak_Time;
	}

	public Date getRequest_Made_Date() {
		return this.request_Made_Date;
	}

	public void setRequest_Made_Date(Date request_Made_Date) {
		this.request_Made_Date = request_Made_Date;
	}

	public Date getStart_Date() {
		return this.start_Date;
	}

	public void setStart_Date(Date start_Date) {
		this.start_Date = start_Date;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}