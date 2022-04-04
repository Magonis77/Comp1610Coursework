package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Holiday;
import model.HolidaysDTO;
import model.User;

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
	
    public User getHolidaysByUserIDUsingNamedQuery(int userid)
    {
    	User queryResult = em.createNamedQuery("User.findHolidaysByUserID", User.class)
    							.setParameter("id", userid)
    							.getSingleResult();
    	return queryResult;
    }
   public void acceptholiday(int iD) {
	Holiday h = em.find(Holiday.class,iD);
	h.setStatus("Accepted");
	em.persist(h);
   }
   
   public void rejectholiday(int iD) {
	Holiday h = em.find(Holiday.class,iD);
	h.setStatus("Rejected");
	em.persist(h);
   }

   public void requestholiday(int id, Date startdate, Date enddate, int Lenght, String status) {
	 Holiday h = new Holiday();
	   h.setStart_Date(startdate);
	   h.setEnd_Date(enddate);
	   h.setLenght(Lenght);
	   h.setStatus(status);
	   em.persist(h);
	   
}

}
