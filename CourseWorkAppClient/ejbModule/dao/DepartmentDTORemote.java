package dao;

import java.util.List;

import javax.ejb.Remote;
import model.DepartmentsDTO;

@Remote
public interface DepartmentDTORemote {
	
public List<DepartmentsDTO> allDepartments();
}
