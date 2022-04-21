package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Department;
import model.Drole;
import model.User;
import model.UserDTO;

/**
 * Session Bean implementation class LRLDTO
 */
@Stateless
@LocalBean
@Remote(LRLDTORemote.class)
public class LRLDTO implements LRLDTORemote {

	@PersistenceContext(unitName="CourseWorkApp")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public LRLDTO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertUser(String username, String password, String name, String LastName, String Gender, Date JoinDate, int Roleid, int Departmentid) {
		User u = new User();
    	u.setUsername(username);
    	u.setPassword(password);
    	Drole dr = em.find(Drole.class, Roleid);
    	Department dp = em.find(Department.class, Departmentid);
		u.setFirstname(name);
		u.setLastname(LastName);
		u.setGender(Gender);
		u.setJoinDate(JoinDate);
		u.setDrole(dr);
		u.setDepartment(dp);
		em.persist(u);
		
	}

	@Override
	public List<UserDTO> allUsers() {
		List<User> queryResults = em.createQuery("SELECT u FROM User u", User.class).getResultList();
    	List<UserDTO> listResult = new ArrayList<UserDTO>();
    	
    	queryResults.forEach(u ->
    		listResult.add(new UserDTO(u.getId(),u.getUsername(), u.getFirstname(), u.getLastname(), u.getGender(), u.getJoinDate(), u.getPassword())));
    	return listResult;
	}
	   
    public void updateAUserSimple(String user, String pass)
    {
    	User u = em.find(User.class, user);
    	
    }
    public UserDTO getuserinfobyUserID(int userid)
    {
    	User user = em.find(User.class, userid);
    	UserDTO u = new UserDTO(user.getId(),user.getUsername(), user.getFirstname(), user.getLastname(), user.getGender(), user.getJoinDate(), user.getPassword());
    	if (u == null) {
    		throw new EntityNotFoundException("Can't find User for ID "
                    + userid);	
    	}
    	return u;
    }
    
    public UserDTO removeuser(int userid) {
    	
    	Query queryResult = em.createNativeQuery("DELETE FROM USER WHERE ID = " + userid);
    	queryResult.executeUpdate();
		return null;
    	
    }
    
    public List<UserDTO> finduserbyholiday(int holidayID){
    	List<User> user = em.createNamedQuery("User.findUserByHolidayID", User.class)
    			.setParameter("id", holidayID)
    			.getResultList();
    	List<UserDTO> listResult = new ArrayList<UserDTO>();
    	
    	user.forEach(u ->
    		listResult.add(new UserDTO(u.getId(),u.getUsername(), u.getFirstname(), u.getLastname(), u.getGender(), u.getJoinDate(), u.getPassword())));
    	return listResult;
    	
    }

	public void amendUser(String username, String password, String name, String lastName, String gender,
			int idRole, int idDepartment, int userID) {
		User u = em.find(User.class, userID);
    	if (u == null) {
    		throw new EntityNotFoundException("Can't find User for ID "
                    + userID);	
    	}
    	u.setUsername(username);
    	u.setPassword(password);
    	Drole dr = em.find(Drole.class, idRole);
    	Department dp = em.find(Department.class, idDepartment);
		u.setFirstname(name);
		u.setLastname(lastName);
		u.setGender(gender);
		u.setDrole(dr);
		u.setDepartment(dp);
		em.persist(u);
		
	}


	
}
