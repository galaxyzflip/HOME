package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

import logon.LogonDataBean;
import logon.LogonDBBean;


public class LogonInputProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		LogonDataBean member = new LogonDataBean();
		
		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
		member.setBirthday(request.getParameter("birthday"));
		member.setMale(request.getParameter("male"));
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email"));
		member.setBlog(request.getParameter("blog"));
		member.setZipcode(request.getParameter("zipcode"));
		member.setAddress(request.getParameter("address"));
		member.setDetailAddress(request.getParameter("detailAddress"));
		member.setPhone(request.getParameter("phone").replace("-", ""));
		member.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		LogonDBBean.getInstance().insertMember(member);
		
		return "/logon2/inputPro.jsp";
		
	}

	
}
