package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Drole;
import model.RolesDTO;
import model.UserDTO;

/**
 * Session Bean implementation class RoleDTO
 */
@Stateless
@LocalBean
@Remote(RoleDTORemote.class)
public class RoleDTO implements RoleDTORemote {
	@PersistenceContext(unitName="CourseWorkApp")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public RoleDTO() {
        // TODO Auto-generated constructor stub
    }
    public List<RolesDTO> allRoles() {
    	List<Drole> queryResults = em.createQuery("SELECT d FROM Drole d").getResultList();
    	List<RolesDTO> listRole = new ArrayList<RolesDTO>();
    	queryResults.forEach(r ->
		listRole.add(new RolesDTO(r.getId(), r.getRole())));
    	
    	return listRole;
    	
    }
}
