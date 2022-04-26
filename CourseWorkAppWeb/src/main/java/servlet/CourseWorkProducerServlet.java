package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CourseWorkProducerServlet
 */
@WebServlet("/CourseWorkProducerServlet")
public class CourseWorkProducerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseWorkProducerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //gets the information from JSP file and passes to the DTO then redirects
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param_action = request.getParameter("action");
		String tableStr = new String();
		
		try {
			Context jndiContext = new InitialContext();
			ConnectionFactory factory = (ConnectionFactory) jndiContext.lookup("java:/ConnectionFactory");
			Queue calculationQueue = (Queue) jndiContext.lookup("java:/jms/CourseworkQueue");
			Connection connect = factory.createConnection();

			Session session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);

			MessageProducer sender = session.createProducer(calculationQueue);
			
			switch (param_action) {
			case "requestholiday": {
				String id = request.getParameter("id");
				String startdate = request.getParameter("startdate");
				String enddate = request.getParameter("enddate");
				
				MapMessage message = session.createMapMessage();
				
				message.setString("id", id);
				message.setString("startdate", startdate);
				message.setString("enddate", enddate);
				
				System.out.println("Sending message");
				
				sender.send(message);
				
				connect.close();
				
				tableStr += " <br/><strong>New Holiday request was sent to the MDB</strong></br>";
				tableStr += "<a href=index.jsp>Home</a>";
			}
			break;
			
			default:
				break;
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Holiday Request App</title>");
		out.println("<head>");

		out.println("Displayed @ " + new java.util.Date() + "<br/>" + tableStr);
		out.println("<body>");
		out.println("</body>");
		out.println("</html>");
		out.close();		
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
