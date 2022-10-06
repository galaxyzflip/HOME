package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logon.LogonDBBean;

public class ConfirmPhoneAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		String phone = request.getParameter("phone");
		int check = LogonDBBean.getInstance().checkPhone(phone);
		
		request.setAttribute("check", check);
		request.setAttribute("phone", phone);
		
		// -1 : 중복 / 1 : 사용가능
		return "/logon2/confirmPhonePro.jsp";
		
	}

}
