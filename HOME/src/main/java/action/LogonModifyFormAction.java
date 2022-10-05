package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logon.LogonDBBean;
import logon.LogonDataBean;

public class LogonModifyFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		LogonDBBean dbPro = LogonDBBean.getInstance();
		LogonDataBean member = dbPro.getMember(id);

		request.setAttribute("member", member);

		return "/logon2/modifyForm.jsp";// 해당뷰

	}

}
