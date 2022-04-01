package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Holiday;
import model.HolidaysDTO;

/**
 * Session Bean implementation class HolidayDTO
 */
@Stateless
@LocalBean
public class HolidayDTO implements HolidayDTORemote {
	@PersistenceContext(unitName="CourseWorkApp")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public HolidayDTO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<HolidaysDTO> allHolidays() {
		List<Holiday> queryResults = em.createQuery("SELECT h FROM Holiday h", Holiday.class).getResultList();
    	List<HolidaysDTO> listResult = new ArrayList<HolidaysDTO>();
    	
    	queryResults.forEach(h ->
    		listResult.add(new HolidaysDTO(h.getId(), h.getEnd_Date(),h.getStatus(),h.getLenght(),h.getStart_Date())));
    	return listResult;
	}

}
