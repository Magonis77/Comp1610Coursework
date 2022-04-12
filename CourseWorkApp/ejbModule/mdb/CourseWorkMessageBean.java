package mdb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.ActivationConfigProperty;
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

import model.Department;
import model.Drole;
import model.Holiday;
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
    /**
     * Default constructor. 
     */
    public CourseWorkMessageBean() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
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
    
    public int requestholiday(int user, Date start, Date end, int lenght, String Status) {
		Holiday h = new Holiday();
		h.setStart_Date(start);
		h.setEnd_Date(end);
		h.setLenght(lenght);
		h.setStatus(Status);
		User u = em.find(User.class, user);
		h.setUser(u);
		em.persist(h);
		em.flush(); // The ID is only guaranteed to be generated at flush time.

		return h.getId();
	}

}
