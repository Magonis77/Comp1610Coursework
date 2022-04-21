package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
 * Servlet implementation class HolidaysServlet
 */
@WebServlet("/HolidaysServlet")
public class HolidaysServlet extends HttpServlet {
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
    public HolidaysServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param_action = request.getParameter("action");
		String tableStr = new String();
		switch(param_action) {
		case "getallholidays":
		{
			List<UserDTO> userlist = lrlDTO.allUsers();
			request.setAttribute("userlist", userlist);
			List<HolidaysDTO> list = hDTO.allHolidays();
			request.setAttribute("holidaylist", list);
			
			// Forward to /WEB-INF/views/productListView.jsp
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/HolidayListView.jsp");
			dispatcher.forward(request, response);
		}
		break;
		case "getalloutstandingholidays":{
			String Status = "Outstanding";
			List<HolidaysDTO> holidaylist = hDTO.allOutstandingHolidays(Status);
				
			HttpSession session = request.getSession();
			session.setAttribute("holidaylist", holidaylist);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/OutstandingHolidaysView.jsp");
			dispatcher.forward(request, response);
		}
		break;
		case "allUsers":{
			List<UserDTO> userlist = lrlDTO.allUsers();
			
			HttpSession session = request.getSession();
			session.setAttribute("userlist", userlist);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("Holidaybyuser.jsp");
			dispatcher.forward(request, response);
		}
		break;
		case "HolidaysByUser":{
			String idstring = request.getParameter("UserID");
			int UserID = Integer.parseInt(idstring);
			List<HolidaysDTO> holidaylist = hDTO.allUserHolidays(UserID);
			HttpSession session = request.getSession();
			session.setAttribute("holidaylist", holidaylist);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/UserHolidayView.jsp");
			dispatcher.forward(request, response);
		}
		break;
		default:
			break;}
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

	private HolidayDTORemote requestholiday(int uid, Date startdate, Date enddate, int lenght) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
