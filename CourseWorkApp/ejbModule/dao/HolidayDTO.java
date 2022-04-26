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
import model.UserDTO;

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

  //Gets the list of all holidays and sends to the WEB through the Client
	@Override
	public List<HolidaysDTO> allHolidays() {
		List<Holiday> queryResults = em.createQuery("SELECT h FROM Holiday h", Holiday.class).getResultList();
    	List<HolidaysDTO> listResult = new ArrayList<HolidaysDTO>();
    	
    	queryResults.forEach(h ->
    		listResult.add(new HolidaysDTO(h.getId(), h.getEnd_Date(),h.getStatus(),h.getLenght(),h.getStart_Date(),h.getDate_Decision_Made(),h.getRequest_Made_Date(), h.getOverall_Length(),h.getPeak_Time(),h.getConstraints())));
    	return listResult;
	}
	//accepts holiday when the holiday is accepted and stores it in the database as accepted
   public void acceptholiday(int iD) {
	Holiday h = em.find(Holiday.class,iD);
	h.setStatus("Accepted");
	int approveddays = h.getOverall_Length();
	int newdays = approveddays + h.getLenght();
	h.setOverall_Length(newdays);
	Date date = new Date(); 
	h.setDate_Decision_Made(date);
	em.persist(h);
   }
   //rejects the holiday and stores it in the database as rejected
   public void rejectholiday(int iD) {
	Holiday h = em.find(Holiday.class,iD);
	Date date = new Date(); 
	h.setDate_Decision_Made(date);
	h.setStatus("Rejected");
	em.persist(h);
   }
   
   //did the request holiday part before the MDB got introduced, for now it has no usage.
   public void requestholiday(int id, Date startdate, Date enddate, int Lenght, String status) {
	 Holiday h = new Holiday();
	   h.setStart_Date(startdate);
	   h.setEnd_Date(enddate);
	   h.setLenght(Lenght);
	   h.setStatus(status);
	   em.persist(h);
	   
}

 //gets all holidays by user ID.
public List<HolidaysDTO> allUserHolidays(int userID) {
	List<Holiday> queryResults = em.createNamedQuery("Holiday.findHolidaysByUserID", Holiday.class)
			.setParameter("id", userID)
			.getResultList();
	List<HolidaysDTO> listResult = new ArrayList<HolidaysDTO>();
	
	queryResults.forEach(h ->
		listResult.add(new HolidaysDTO(h.getId(), h.getEnd_Date(),h.getStatus(),h.getLenght(),h.getStart_Date(),h.getDate_Decision_Made(),h.getRequest_Made_Date(), h.getOverall_Length(),h.getPeak_Time(),h.getConstraints())));
	return listResult;
}


//gets all holidays by user id but only passes through the lenght of the holidays
public List<HolidaysDTO> countuserlenght(int userID) {
	List<Holiday> queryResults = em.createNamedQuery("Holiday.findHolidaysByUserID", Holiday.class)
			.setParameter("id", userID)
			.getResultList();
	
	List<HolidaysDTO> listResult = new ArrayList<HolidaysDTO>();
	
	queryResults.forEach(h ->
		listResult.add(new HolidaysDTO(h.getLenght())));
	return listResult;
}

//gets the accepted holidays by user and passes only the length of it.
public List<HolidaysDTO> countuseracceptedlenght(int userID) {
	List<Holiday> queryResults = em.createNamedQuery("Holiday.findHolidaysByUserIDandstatus", Holiday.class)
			.setParameter("id", userID)
			.setParameter("status", "Accepted")
			.getResultList();
	
	List<HolidaysDTO> listResult = new ArrayList<HolidaysDTO>();
	
	queryResults.forEach(h ->
		listResult.add(new HolidaysDTO(h.getLenght())));
	return listResult;
}

//gets all outstanding holidays with constraint in them and passes to the web through client
public List<HolidaysDTO> allOutstandingHolidayswcontrain(String status, int i){
	
	List<Holiday> queryResults = em.createNamedQuery("Holiday.findOutstandingHolidayswconstraint", Holiday.class)
			.setParameter("status", status)
			.setParameter("cid", i)
			.getResultList();
	List<HolidaysDTO> listResult = new ArrayList<HolidaysDTO>();
	queryResults.forEach(h ->
		listResult.add(new HolidaysDTO(h.getId(), h.getEnd_Date(),h.getStatus(),h.getLenght(),h.getStart_Date(),h.getDate_Decision_Made(),h.getRequest_Made_Date(),h.getOverall_Length(),h.getPeak_Time(),h.getConstraints())));
	return listResult;
	
}

//gets all outstanding holidays without constraints and passes to the web through the client
@Override
public List<HolidaysDTO> allOutstandingHolidays(String status) {
	List<Holiday> queryResults = em.createNamedQuery("Holiday.findOutstandingHolidays", Holiday.class)
			.setParameter("status", status)
			.setParameter("cid", 0)
			.getResultList();
	List<HolidaysDTO> listResult = new ArrayList<HolidaysDTO>();
	queryResults.forEach(h ->
		listResult.add(new HolidaysDTO(h.getId(), h.getEnd_Date(),h.getStatus(),h.getLenght(),h.getStart_Date(),h.getDate_Decision_Made(),h.getRequest_Made_Date(),h.getOverall_Length(),h.getPeak_Time(),h.getConstraints())));
	return listResult;
}

}
