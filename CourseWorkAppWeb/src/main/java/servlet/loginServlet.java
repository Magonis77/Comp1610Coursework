package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LRLDTORemote;
import model.UserDTO;


/**
 * Servlet implementation class login
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private LRLDTORemote lrlDTO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //gets the information from JSP file and passes to the DTO then redirects
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableStr = new String();
		String databaseUsername = "";
		String databasePassword = "";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDTO e = new UserDTO();
		List <UserDTO> userlist = lrlDTO.allUsers();
		int is = 1;
		for(int i = 0; i < userlist.size(); i++)
		{
			databaseUsername = String.valueOf(userlist.get(i).getUsername());
			databasePassword = String.valueOf(userlist.get(i).getPassword());
			if (username.equals(databaseUsername) && password.equals(databasePassword)) {
				request.setAttribute("username", username);
				response.sendRedirect("index.jsp");
				is = 1;
				break;
		    }
			else {
				is = 0;
			}
			
		
	}
		if(is == 0) {
			tableStr += "Error incorrect password or username!";
			tableStr += "<a href=\"login.jsp\">Try Again</a>|<br/>";
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
