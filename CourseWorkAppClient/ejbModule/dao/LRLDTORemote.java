package dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import model.UserDTO;

@Remote
public interface LRLDTORemote {
	public void insertUser(String username, String password, String firstname, String lastname, String gender, Date joinDate, int idRole, int idDepartment);
	public List<UserDTO> allUsers();
	public UserDTO getuserinfobyUserID(int userID);
	public void amendUser(String username, String password, String name, String lastName, String gender, int idRole, int idDepartment, int userID);
	public UserDTO removeuser(int iD);
}
