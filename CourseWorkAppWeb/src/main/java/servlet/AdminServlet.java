package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DepartmentDTORemote;
import dao.HolidayDTORemote;
import dao.LRLDTORemote;
import dao.RoleDTORemote;
import model.HolidaysDTO;
import model.UserDTO;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private LRLDTORemote lrlDTO;
	@EJB
	private RoleDTORemote rDTO;
	@EJB
	private DepartmentDTORemote dDTO;
	@EJB
	private HolidayDTORemote hDTO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
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
		switch(param_action) {
		case "allUsers":{
			List<UserDTO> userlist = lrlDTO.allUsers();
			
			HttpSession session = request.getSession();
			session.setAttribute("userlist", userlist);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("HolidayByUserA.jsp");
			dispatcher.forward(request, response);
		}
		break;
		case "HolidaysByUser":{
			String idstring = request.getParameter("UserID");
			int UserID = Integer.parseInt(idstring);
			List<HolidaysDTO> holidaylist = hDTO.allUserHolidays(UserID);
			HttpSession session = request.getSession();
			session.setAttribute("holidaylist", holidaylist);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/UserHolidayViewA.jsp");
			dispatcher.forward(request, response);
		}
	
	break;
	default:
		break;
		}
	response.setContentType("text/html;charset=UTF-8");
	
	PrintWriter out = response.getWriter();
	out.println("<html>");
	out.println("<head>");
	out.println("<title> CourseWork App Holiday System </title>");
	out.println("</head>");
	out.println("<body>");
	out.println(tableStr);
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
