package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import dao.LRLDTORemote;
import dao.RoleDTORemote;
import model.DepartmentsDTO;

import model.RolesDTO;

import model.UserDTO;

/**
 * Servlet implementation class edituser
 */
@WebServlet("/edituser")
public class edituser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private LRLDTORemote lrlDTO;
	@EJB
	private RoleDTORemote rDTO;
	@EJB
	private DepartmentDTORemote depDTO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public edituser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //gets the information from JSP file and passes to the DTO then redirects
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = (String) request.getParameter("code");
		int userID = Integer.parseInt(code);
		
		
		UserDTO usr = lrlDTO.getuserinfobyUserID(userID);

		List<RolesDTO> rolelist = rDTO.allRoles();
		List<DepartmentsDTO> departmentlist = depDTO.allDepartments();
		
		HttpSession session = request.getSession();
		session.setAttribute("user", usr);
		session.setAttribute("rolelist", rolelist);
		session.setAttribute("departmentlist", departmentlist);
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/edituser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ID = (String) request.getParameter("ID");
		int userID = Integer.parseInt(ID);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("FirstName");
		String Gender = request.getParameter("Gender");
		String cxRole = request.getParameter("cxRole");
		int idRole = Integer.parseInt(cxRole);
		String cxDepartment = request.getParameter("cxDepartment");
		int idDepartment = Integer.parseInt(cxDepartment);
		String LastName = request.getParameter("LastName");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date(); 
		
		lrlDTO.amendUser(username, password, name, LastName, Gender, idRole, idDepartment, userID);
		List<UserDTO> list = lrlDTO.allUsers();
		// Store info in request attribute, before forward to views
		request.setAttribute("userlist", list);
		
		// Forward to /WEB-INF/views/productListView.jsp
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/userListView.jsp");
		dispatcher.forward(request, response);
	}

}
