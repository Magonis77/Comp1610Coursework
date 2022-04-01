package model;

import java.io.Serializable;
import java.util.Date;

public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private String firstname;
	private DepartmentsDTO department;
	private String gender;
	private RolesDTO drole;
	private Date joinDate;

	private String lastname;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	public UserDTO() {
		
	}
	
	public UserDTO(int id, String username, String firstname, String lastname, String gender, Date joinDate, String password) {
//		try {
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
//		 SimpleDateFormat formatter2=new SimpleDateFormat("dd-MMM-yyyy");  
//		    Date 
//				joindate1 = formatter2.parse(joindate);
		    this.joinDate = joinDate;
			this.password = password;
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
		
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}


}
