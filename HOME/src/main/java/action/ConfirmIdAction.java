package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logon.LogonDBBean;

public class ConfirmIdAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		
		int check = LogonDBBean.getInstance().confirmId(id);
		
		request.setAttribute("id", id);
		request.setAttribute("check", check);
		
		return "/logon2/confirmId.jsp";
		
		
	}

}
