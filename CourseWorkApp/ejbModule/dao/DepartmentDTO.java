package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Department;
import model.DepartmentsDTO;

/**
 * Session Bean implementation class DepartmentDTO
 */
@Stateless
@LocalBean
@Remote(DepartmentDTORemote.class)
public class DepartmentDTO implements DepartmentDTORemote {
	@PersistenceContext(unitName="CourseWorkApp")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public DepartmentDTO() {
        // TODO Auto-generated constructor stub
    }
    
    
    //Gets the list of all departments and sends to the WEB through the Client
    public List<DepartmentsDTO> allDepartments() {
    	List<Department> queryResults = em.createQuery("SELECT d FROM Department d", Department.class).getResultList();
    	List<DepartmentsDTO> listResult = new ArrayList<DepartmentsDTO>();
    	
    	queryResults.forEach(d ->
    		listResult.add(new DepartmentsDTO(d.getId(), d.getDepartment())));
    	
    	return listResult;
    	
    }
}
