package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logon.LogonDBBean;

public class LogonDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		int check = LogonDBBean.getInstance().deleteMember(id, passwd);
		
		request.setAttribute("check", new Integer(check));

		return "/logon2/deletePro.jsp";
	}

}
