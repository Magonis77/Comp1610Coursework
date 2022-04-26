package servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HolidayDTORemote;
import model.HolidaysDTO;

/**
 * Servlet implementation class holidayreject
 */
@WebServlet("/holidayreject")
public class holidayreject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private HolidayDTORemote hDTO;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public holidayreject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //gets the information from JSP file and passes to the DTO then redirects
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = (String) request.getParameter("code");
		
		int ID = Integer.parseInt(code);
		
		hDTO.rejectholiday(ID);
		String Status = "Outstanding";
		List<HolidaysDTO> holidaylist = hDTO.allOutstandingHolidays(Status);
		HttpSession session = request.getSession();
		session.setAttribute("holidaylist", holidaylist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/OutstandingHolidaysView.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
