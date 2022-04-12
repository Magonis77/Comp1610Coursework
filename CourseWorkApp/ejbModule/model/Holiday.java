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
@NamedQuery(name="Holiday.findOutstandingHolidays", query="Select h from Holiday h Where h.status=:status")

		}
		)

public class Holiday implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="`End Date`")
	private Date end_Date;

	private int lenght;

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