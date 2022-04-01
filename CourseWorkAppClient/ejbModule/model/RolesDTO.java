package model;

import java.io.Serializable;

public class RolesDTO implements Serializable {
	private int id;

	private String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public RolesDTO() {
		
	}
	public RolesDTO(int id, String role) {
		this.id = id;
		this.role = role;
		
	}
}
