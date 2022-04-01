import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import dao.LRLDTORemote;
import model.UserDTO;

public class Main {
	public static void main(String[] args) {
		
		Main m = new Main();
		
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
		
		try {
			

			System.out.println("Calling EJB from Client!");
			
			final Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

			env.put("jboss.naming.client.ejb.context", true);
			InitialContext remoteContext = new InitialContext(env);
			
			LRLDTORemote ejb = (LRLDTORemote) remoteContext
					.lookup("" + "ejb:CourseWorkAppEJBEAR/CourseWorkApp/LRLDTO!dao.LRLDTORemote");
			
			List<UserDTO> listUsers = ejb.allUsers();
			
			System.out.println("*** Result returned from EJB ***");
			System.out.println("Total Users :" + listUsers.size());
			
			System.out.println("| ID | Username |");
			
			for(int i = 0; i < listUsers.size(); i++)
			{
				UserDTO b = listUsers.get(i);
				String text = String.format("| %d | %s", b.getId(), b.getUsername());
				System.out.println(text);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}