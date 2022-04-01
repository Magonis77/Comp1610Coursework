package dao;

import java.util.List;

import javax.ejb.Remote;

import model.RolesDTO;

@Remote
public interface RoleDTORemote {

	public List<RolesDTO> allRoles();
}
