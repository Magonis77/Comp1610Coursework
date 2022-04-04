package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			List<HolidaysDTO> list = hDTO.allHolidays();
			request.setAttribute("holidaylist", list);
			
			// Forward to /WEB-INF/views/productListView.jsp
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/HolidayListView.jsp");
			dispatcher.forward(request, response);
		}
		break;
		case "Requestholiday":{
			try {
			String id = request.getParameter("id");
			int uid = Integer.parseInt(id);
			String start = request.getParameter("startdate");
			String end = request.getParameter("enddate");
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
				Date startdate = dateFormat.parse(end);
				Date enddate = dateFormat.parse(start);
				long diff = startdate.getTime() - enddate.getTime();
				long diffDays = diff / (24 * 60 * 60 * 1000);
				int lenght = (int)diffDays;
				String status = "In Review";
				
				hDTO.requestholiday(uid,startdate,enddate,lenght, status);
				
				tableStr +=	"<br/><strong>Holiday Requested</strong>";
				tableStr += "</table>";
				tableStr += "<a href=index.html>Home</a>";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
