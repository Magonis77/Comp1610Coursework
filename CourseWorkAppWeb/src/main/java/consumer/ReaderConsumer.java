package consumer;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ReaderConsumer implements MessageListener {

	public ReaderConsumer()
	{
		Context jndiContext;
		
		try {
			jndiContext = new InitialContext();
			ConnectionFactory factory = (ConnectionFactory)jndiContext.lookup ("java:/ConnectionFactory");
	      
		    Topic resultTopic = (Topic) jndiContext.lookup ("java:/jms/CourseworkTopic");
		    
		    Connection connect = factory.createConnection();      
			Session session = connect.createSession(false,Session.AUTO_ACKNOWLEDGE);      
			MessageConsumer receiver = session.createConsumer(resultTopic); 
			
			receiver.setMessageListener(this);
			
			System.out.println ("ReaderConsumer Listening for messages on java:/jms/CourseworkTopic...");
			connect.start ();
			    
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onMessage(Message message) {

		try {
			TextMessage objMsg = (TextMessage)message;
			String msg = objMsg.getText();
			
			System.out.println ("Reader going to find and read: " + msg);
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
