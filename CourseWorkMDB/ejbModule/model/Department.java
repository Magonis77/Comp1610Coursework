package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the department database table.
 * 
 */
@Entity
@NamedQuery(name="Department.findAll", query="SELECT d FROM Department d")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String department;

	//bi-directional many-to-one association to Info
	@OneToMany(mappedBy="department")
	private List<Info> infos;

	public Department() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<Info> getInfos() {
		return this.infos;
	}

	public void setInfos(List<Info> infos) {
		this.infos = infos;
	}

	public Info addInfo(Info info) {
		getInfos().add(info);
		info.setDepartment(this);

		return info;
	}

	public Info removeInfo(Info info) {
		getInfos().remove(info);
		info.setDepartment(null);

		return info;
	}

}