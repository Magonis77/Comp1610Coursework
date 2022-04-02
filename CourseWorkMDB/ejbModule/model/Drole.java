package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the drole database table.
 * 
 */
@Entity
@NamedQuery(name="Drole.findAll", query="SELECT d FROM Drole d")
public class Drole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String role;

	//bi-directional many-to-one association to Info
	@OneToMany(mappedBy="drole")
	private List<Info> infos;

	public Drole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Info> getInfos() {
		return this.infos;
	}

	public void setInfos(List<Info> infos) {
		this.infos = infos;
	}

	public Info addInfo(Info info) {
		getInfos().add(info);
		info.setDrole(this);

		return info;
	}

	public Info removeInfo(Info info) {
		getInfos().remove(info);
		info.setDrole(null);

		return info;
	}

}