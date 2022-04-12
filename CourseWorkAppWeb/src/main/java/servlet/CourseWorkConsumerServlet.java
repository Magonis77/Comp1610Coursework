package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import consumer.AdsConsumer;
import consumer.ReaderConsumer;

/**
 * Servlet implementation class CourseWorkConsumerServlet
 */
@WebServlet("/CourseWorkConsumerServlet")
public class CourseWorkConsumerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseWorkConsumerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableStr = new String();
		AdsConsumer ads = new AdsConsumer();
		ReaderConsumer reader = new ReaderConsumer();
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Holiday Request App</title>");
		out.println("<head>");
		out.println("Two consumers were started and waiting to consume messages");
		out.println("<a href=index.html>Home</a>");
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
