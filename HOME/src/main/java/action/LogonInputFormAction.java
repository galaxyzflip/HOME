package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

import logon.LogonDataBean;
import logon.LogonDBBean;


public class LogonInputFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		return "/logon2/inputForm.jsp";
		
	}

	
}
