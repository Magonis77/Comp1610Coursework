package mdb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.HolidayDTO;
import model.Department;
import model.Drole;
import model.Holiday;
import model.HolidaysDTO;
import model.User;

/**
 * Message-Driven Bean implementation class for: CourseWorkMessageBean
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/CourseworkQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/CourseworkQueue")
public class CourseWorkMessageBean implements MessageListener {
	@PersistenceContext(unitName = "CourseWorkApp")
	EntityManager em;
	@EJB
	private HolidayDTO hDTO;
    /**
     * Default constructor. 
     */
    public CourseWorkMessageBean() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    
    //receives the message
    public void onMessage(Message message) {
System.out.println("Message received by MDB");
    	
    	try {
    		MapMessage holidayMsg = (MapMessage) message;
			String idu = holidayMsg.getString("id");
			int user = Integer.parseInt(idu);
			String startdate = holidayMsg.getString("startdate");
			
			String enddate = holidayMsg.getString("enddate");

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
				Date end = dateFormat.parse(enddate);
				Date start = dateFormat.parse(startdate);
				long diff = end.getTime() - start.getTime();
				long diffDays = diff / (24 * 60 * 60 * 1000);
				int lenght = (int)diffDays;
				String status = "Outstanding";
			int id = requestholiday(user,start,end,lenght, status);
			
			System.out.println(String.format("The Holiday Reguest: %s with ID %d ", status, id));
			
			deliverResult(holidayMsg, id);
						
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //delivers the results to DTO etc.
    public void deliverResult(MapMessage holidayMsg, int id) throws JMSException, NamingException, ParseException {
		Context jndiContext = new InitialContext();
		ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("java:/ConnectionFactory");
		
		Topic resultTopic = (Topic) jndiContext.lookup("java:/jms/CourseworkTopic");
		Connection connect = connectionFactory.createConnection();
		Session session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);

			String status1 = "Outstanding";

		MessageProducer sender = session.createProducer(resultTopic);
		TextMessage message = session.createTextMessage();

		message.setText(String.format("The Holiday Reguest: %s with ID %d ", status1, id));
		sender.send(message);
		connect.close();
	}
    
    //REQUESTS HOLIDAYS AFTER CONSTRAINT CHECKING
    @SuppressWarnings("unlikely-arg-type")
	public int requestholiday(int user, Date start, Date end, int lenght, String Status) throws ParseException {
    	int sum = 0;
    	int accpeteddays = 0;
		List<HolidaysDTO> list = hDTO.countuserlenght(user);
		List<HolidaysDTO> acceptedlist = hDTO.countuseracceptedlenght(user);
		User userfind = em.find(User.class, user);
		Department department  = userfind.getDepartment();
		String dep = department.getDepartment();
		Drole roles = userfind.getDrole();
		String rolename = roles.getRole();
		 
		//gets list of all roles by department
		List<User> roleusers = em.createNamedQuery("User.findusersbydepartmentid", User.class)
				.setParameter("department", department)
				.getResultList();
		
		//gets the head of the department
		List<User> head = em.createNamedQuery("User.findusersbydepartmentidandrole", User.class)
				.setParameter("department", department)
				.setParameter("role", "Head")
				.getResultList();
		//gets the deputy head of department
		List<User> Deputyhead = em.createNamedQuery("User.findusersbydepartmentidandrole", User.class)
				.setParameter("department", department)
				.setParameter("role", "Deputy Head")
				.getResultList();
		//gets the managers of department
		List<User> Manager = em.createNamedQuery("User.findusersbydepartmentidandrole", User.class)
				.setParameter("department", department)
				.setParameter("role", "Manager")
				.getResultList();
		//gets the apprentices of the department
		List<User> Apprentice = em.createNamedQuery("User.findusersbydepartmentidandrole", User.class)
				.setParameter("department", department)
				.setParameter("role", "Apprentice")
				.getResultList();
		
		
		//checks if head is on holiday
		int userid = head.get(0).getId();
		String username = head.get(0).getUsername();
		List<HolidaysDTO> holidaylist1 = hDTO.allUserHolidays(userid);
		boolean Headonleave = false;
		for(int i =0; i < holidaylist1.size(); i++) {
			Date enddate1 = holidaylist1.get(i).getStart_Date();
					
					Date startdate1 = holidaylist1.get(i).getEnd_Date();
					
					if(this.isWithinRange1(start,startdate1, enddate1) == true) {
						Headonleave = true;

			    		
			
		}
					if(this.isWithinRange1(end,startdate1, enddate1) == true) {
		    			Headonleave = true;
		    
		    		}
					
		}
		//checks if department head is on hooliday
		int Depuserid = Deputyhead.get(0).getId();
		String username2 = Deputyhead.get(0).getUsername();
		List<HolidaysDTO> holidaylist2 = hDTO.allUserHolidays(Depuserid);
		boolean DepHeadonleave = false;
		for(int i =0; i < holidaylist2.size(); i++) {
			Date enddate1 = holidaylist2.get(i).getStart_Date();
					
					Date startdate1 = holidaylist2.get(i).getEnd_Date();
					
					if(this.isWithinRange1(start,startdate1, enddate1) == true) {
						DepHeadonleave = true;
						
			    		
			
		}
					if(this.isWithinRange1(end,startdate1, enddate1) == true) {
						DepHeadonleave = true;
		    			
		    		}
					
		}
		//checks if atleast one manager is working
		boolean Manageronleave = false;
		for(int i =0; i < Manager.size(); i++) {
		int Managerid = Manager.get(i).getId();
		List<HolidaysDTO> holidaylistManagerid = hDTO.allUserHolidays(Managerid);
		for(int a =0; a < holidaylistManagerid.size(); a++) {
			Date enddate1 = holidaylistManagerid.get(a).getStart_Date();
					
					Date startdate1 = holidaylistManagerid.get(a).getEnd_Date();
					
					if(this.isWithinRange1(start,startdate1, enddate1) == true) {
						Manageronleave = true;

			    		
			
		}
					if(this.isWithinRange1(end,startdate1, enddate1) == true) {
						Manageronleave = true;
		    
		    		}
					
		}
		}
		
		//checks if atleast one apprentice is working
		boolean Apprenticeonleave = false;	
			for(int i =0; i < Apprentice.size(); i++) {
				int Apprenticeid = Apprentice.get(i).getId();
				List<HolidaysDTO> holidaylistApprenticeid = hDTO.allUserHolidays(Apprenticeid);
				
				for(int a =0; a < holidaylistApprenticeid.size(); a++) {
					Date enddate1 = holidaylistApprenticeid.get(a).getStart_Date();
							
							Date startdate1 = holidaylistApprenticeid.get(a).getEnd_Date();
							
							if(this.isWithinRange1(start,startdate1, enddate1) == true) {
								Apprenticeonleave = true;

					    		
					
				}
							if(this.isWithinRange1(end,startdate1, enddate1) == true) {
								Apprenticeonleave = true;
				    
				    		}
							
				}
			}
		
		
		
		for(int i =0; i < list.size(); i++)
		{
			sum += list.get(i).getLenght();
			
		}
		
		for(int i =0; i < acceptedlist.size(); i++)
		{
			accpeteddays += acceptedlist.get(i).getLenght();
			
		}

		int sumnew = sum +lenght;

		
		//checks if the date is within holidays
		if(this.isWithinRange(start) == true) {
  
			//checks if the date is within holidays
    		if(this.isWithinRange(end) == true) {
    			Holiday h = new Holiday();
    			
    			h.setStart_Date(start);
    			h.setEnd_Date(end);
    			h.setLenght(lenght);
    			h.setStatus(Status);
    			h.setPeak_Time("A - Yes Christmas time");
    			h.setConstraints("");
    			h.setContraintbreak(0);
    			h.setOverall_Length(accpeteddays);
    			Date date = new Date(); 
    			h.setRequest_Made_Date(date);
    			User u = em.find(User.class, user);
    			h.setUser(u);
    			em.persist(h);
    			em.flush(); // The ID is only guaranteed to be generated at flush time.
    			return h.getId();
    			
    		}
    		}
    		else {
    			//checks if the employee is not getting more gholidays than entiteled
    			if(sumnew > 30) {
    				Holiday h = new Holiday();
        			h.setStart_Date(start);
        			h.setEnd_Date(end);
        			h.setLenght(lenght);
        			h.setStatus(Status);
        			if(Peaktime1(start) == true) {
        				if(Peaktime1(end) == true) {
        					h.setPeak_Time("A - Yes");
        				}
        			}
        			if(Peaktime2(start) == true) {
        				if(Peaktime2(end) == true) {
        					h.setPeak_Time("A - Yes");
        				}
        			}
        			else {
        				h.setPeak_Time("B - No");
        			}
        			h.setContraintbreak(1);
        			h.setConstraints("Requesting more days than entiteled");
        			h.setOverall_Length(accpeteddays);
        			Date date = new Date(); 
        			h.setRequest_Made_Date(date);
        			User u = em.find(User.class, user);
        			h.setUser(u);
        			em.persist(h);
        			em.flush(); // The ID is only guaranteed to be generated at flush time.
        			return h.getId();
    	    		
    			}
    			if(sumnew <= 30) {
    				if(Headonleave == true) {
    					Holiday h = new Holiday();
    					h.setStart_Date(start);
    					h.setEnd_Date(end);
    					h.setLenght(lenght);
    					h.setStatus(Status);
    					if(Peaktime1(start) == true) {
            				if(Peaktime1(end) == true) {
            					h.setPeak_Time("A - Yes");
            				}
            			}
            			if(Peaktime2(start) == true) {
            				if(Peaktime2(end) == true) {
            					h.setPeak_Time("A - Yes");
            				}
            			}
            			else {
            				h.setPeak_Time("B - No");
            			}
    					h.setContraintbreak(1);
    					h.setConstraints("Head of the department is on leave");
    					h.setOverall_Length(accpeteddays);
    					Date date = new Date(); 
    					h.setRequest_Made_Date(date);
    					User u = em.find(User.class, user);
    					h.setUser(u);
    					em.persist(h);
    					em.flush(); // The ID is only guaranteed to be generated at flush time.
    					return h.getId();
    				}
    				if(Headonleave == false) {
    					if(DepHeadonleave == true) {
    						Holiday h = new Holiday();
        					h.setStart_Date(start);
        					h.setEnd_Date(end);
        					h.setLenght(lenght);
        					h.setStatus(Status);
        					if(Peaktime1(start) == true) {
                				if(Peaktime1(end) == true) {
                					h.setPeak_Time("A - Yes");
                				}
                			}
                			if(Peaktime2(start) == true) {
                				if(Peaktime2(end) == true) {
                					h.setPeak_Time("A - Yes");
                				}
                			}
                			else {
                				h.setPeak_Time("B - No");
                			}
        					h.setContraintbreak(1);
        					h.setConstraints("Depudy Head of the department is on leave");
        					h.setOverall_Length(accpeteddays);
        					Date date = new Date(); 
        					h.setRequest_Made_Date(date);
        					User u = em.find(User.class, user);
        					h.setUser(u);
        					em.persist(h);
        					em.flush(); // The ID is only guaranteed to be generated at flush time.
        					return h.getId();
        				}
    					if(DepHeadonleave == false) {
    						if(Apprenticeonleave == true && Manageronleave == true) {
    							Holiday h = new Holiday();
            					h.setStart_Date(start);
            					h.setEnd_Date(end);
            					h.setLenght(lenght);
            					h.setStatus(Status);
            					if(Peaktime1(start) == true) {
                    				if(Peaktime1(end) == true) {
                    					h.setPeak_Time("A - Yes");
                    				}
                    			}
                    			if(Peaktime2(start) == true) {
                    				if(Peaktime2(end) == true) {
                    					h.setPeak_Time("A - Yes");
                    				}
                    			}
                    			else {
                    				h.setPeak_Time("B - No");
                    			}
            					h.setContraintbreak(1);
            					h.setConstraints("manager or senior lead is on leave of the department is on leave");
            					h.setOverall_Length(accpeteddays);
            					Date date = new Date(); 
            					h.setRequest_Made_Date(date);
            					User u = em.find(User.class, user);
            					h.setUser(u);
            					em.persist(h);
            					em.flush(); // The ID is only guaranteed to be generated at flush time.
            					return h.getId();
    						}
    						if(Apprenticeonleave == false && Manageronleave == false) {
    							Holiday h = new Holiday();
            					h.setStart_Date(start);
            					h.setEnd_Date(end);
            					h.setLenght(lenght);
            					h.setStatus(Status);
            					if(Peaktime1(start) == true) {
                    				if(Peaktime1(end) == true) {
                    					h.setPeak_Time("A - Yes");
                    				}
                    			}
                    			if(Peaktime2(start) == true) {
                    				if(Peaktime2(end) == true) {
                    					h.setPeak_Time("A - Yes");
                    				}
                    			}
                    			else {
                    				h.setPeak_Time("B - No");
                    			}
            					h.setContraintbreak(0);
            					h.setConstraints("");
            					h.setOverall_Length(accpeteddays);
            					Date date = new Date(); 
            					h.setRequest_Made_Date(date);
            					User u = em.find(User.class, user);
            					h.setUser(u);
            					em.persist(h);
            					em.flush(); // The ID is only guaranteed to be generated at flush time.
            					return h.getId();
    						}
        				}
    				}
    				}
    			else {
    				
    				
    			}
    			
    		}
		
		return 0;
    	
    	}
    	
 
 //checks if the date is within range of xmas time
    boolean isWithinRange(Date testDate) throws ParseException {
    	String Start = "2022-12-23";
    	String End = "2023-01-03";
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date startDate = dateFormat.parse(Start);
		
    	Date endDate = 	dateFormat.parse(End);
    	
    
    	 return !(testDate.before(startDate) || testDate.after(endDate));

}
   //checks if the date is between the dates of easier constraints
    boolean isWithinRange1(Date testDate, Date startdate, Date enddate) throws ParseException {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	String Start = dateFormat.format(startdate);
    	String End = dateFormat.format(enddate);
    	Date startDate = dateFormat.parse(Start);
		
    	Date endDate = 	dateFormat.parse(End);
    	 return !(testDate.before(startDate) || testDate.after(endDate));

}
    //checks if the peaktime dates are requested
    boolean Peaktime1(Date testDate) throws ParseException {
    	String Start = "2022-07-15";
    	String End = "2022-08-31";
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date startDate = dateFormat.parse(Start);
		
    	Date endDate = 	dateFormat.parse(End);
    	
    
    	 return !(testDate.before(startDate) || testDate.after(endDate));

}    //checks if the peaktime dates are requested
    boolean Peaktime2(Date testDate) throws ParseException {
    	String Start = "2022-12-15";
    	String End = "2022-12-22";
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date startDate = dateFormat.parse(Start);
		
    	Date endDate = 	dateFormat.parse(End);
    	
    
    	 return !(testDate.before(startDate) || testDate.after(endDate));

}
    
}
