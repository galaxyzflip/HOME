package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logon.LogonDBBean;
import logon.LogonDataBean;

public class LogonModifyProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		LogonDataBean member = new LogonDataBean();
		
		member.setId(id);
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email"));
		member.setBirthday(request.getParameter("birthday"));
		member.setBlog(request.getParameter("blog"));
		member.setPasswd(request.getParameter("passwd"));
		
		LogonDBBean.getInstance().updateMember(member);
		
		return "/logon2/modifyPro.jsp";
		
	}

}
