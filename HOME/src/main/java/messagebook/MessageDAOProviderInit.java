package messagebook;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class MessageDAOProviderInit extends HttpServlet{

	public void init(ServletConfig config) throws ServletException{
		String dbms = config.getInitParameter("dbms");
		
		if(dbms != null) {
			MessageDAOProvider.getInstance().setDbms(dbms);
		}
	}
}
