package model;

import java.io.Serializable;

public class DepartmentsDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String department;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public DepartmentsDTO(){
		
	}
	
	public DepartmentsDTO(int id, String department) {
		this.id = id;
		this.department = department;
	}
}
