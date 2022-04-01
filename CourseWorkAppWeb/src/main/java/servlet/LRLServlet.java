package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class LRLServlet
 */
@WebServlet("/LRLServlet")
public class LRLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private LRLDTORemote lrlDTO;
	@EJB
	private RoleDTORemote rDTO;
	@EJB
	private DepartmentDTORemote dDTO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LRLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param_action = request.getParameter("action");
		String tableStr = new String();
		switch(param_action) {
		case "showAllUsers":
		{
			List<UserDTO> list = lrlDTO.allUsers();
			request.setAttribute("userlist", list);
			
			// Forward to /WEB-INF/views/productListView.jsp
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/userListView.jsp");
			dispatcher.forward(request, response);
		}
	break;
		case "registeruser": {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String name = request.getParameter("FirstName");
			String Gender = request.getParameter("Gender");
			String cxRole = request.getParameter("cxRole");
			int idRole = Integer.parseInt(cxRole);
			String cxDepartment = request.getParameter("cxDepartment");
			int idDepartment = Integer.parseInt(cxDepartment);
			String LastName = request.getParameter("LastName");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date(); 
			
			lrlDTO.insertUser(username, password, name, LastName, Gender, date, idRole, idDepartment);
			List<UserDTO> list = lrlDTO.allUsers();
			request.setAttribute("userlist", list);
			
			// Forward to /WEB-INF/views/productListView.jsp
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/userListView.jsp");
			dispatcher.forward(request, response);
		}
		
		break;
		case "register":
		{
			List<RolesDTO> rolelist = rDTO.allRoles();
			List<DepartmentsDTO> departmentlist = dDTO.allDepartments();
			HttpSession session = request.getSession();
			session.setAttribute("rolelist", rolelist);
			session.setAttribute("departmentlist", departmentlist);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
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
