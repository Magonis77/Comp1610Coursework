package mdb;

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

import model.Holiday;

/**
 * Message-Driven Bean implementation class for: ClientMessageBean
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/CourseworkQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/CourseworkQueue")
public class ClientMessageBean implements MessageListener {
	@PersistenceContext(unitName = "CourseWorkMDB")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public ClientMessageBean() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	System.out.println("Message received by MDB");
    	
    	try {
    		MapMessage Clientmsg = (MapMessage) message;
			String StartDate = Clientmsg.getString("StartDate");
			String EndDate = Clientmsg.getString("EndDate");
			int id = RequestHoliday(StartDate,EndDate);
			
			System.out.println(String.format("The title: %s with ID %d ", StartDate,EndDate, id));
			
			deliverResult(Clientmsg, id);
						
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    public void deliverResult(MapMessage bookMsg, int id) throws JMSException, NamingException {
		Context jndiContext = new InitialContext();
		ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("java:/ConnectionFactory");
		
		Topic resultTopic = (Topic) jndiContext.lookup("java:/jms/CourseworkTopic");
		Connection connect = connectionFactory.createConnection();
		Session session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);

		String title = bookMsg.getString("title");

		MessageProducer sender = session.createProducer(resultTopic);
		TextMessage message = session.createTextMessage();

		message.setText(String.format("New book title: %s with ID %d ", title, id));
		sender.send(message);
		connect.close();
	}
    public int RequestHoliday(String StartDate, String EndDate) {
		Holiday h = new Holiday();
		 String sDate1= StartDate;
		 String sDate2 = EndDate;
		    Date date1;
		    Date date2;
			try {
				date1 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);
				date2 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate2);
				h.setStart_Date(date1);
				h.setEnd_Date(date2);
				h.setLenght(0);
				h.setStatus("In Review");

				em.persist(h);
				em.flush(); // The ID is only guaranteed to be generated at flush time.

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  

		return h.getId();
	}

}
