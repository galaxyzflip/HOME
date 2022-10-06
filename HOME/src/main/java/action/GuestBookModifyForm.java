package action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.GuestBookDAO;
import guestbook.GuestBookDTO;
import logon.jdbcUtil;
import messagebook.ConnectionProvider;

public class GuestBookModifyForm implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		String messageId = request.getParameter("messageId");
		
		Connection conn = ConnectionProvider.getConnection();
		GuestBookDTO message = null;
		
		//레코드 하나 꺼내서 보여주는거
		
		
		try {
			message = GuestBookDAO.getInstance().getMessage(conn, messageId);
			
		}finally {
			jdbcUtil.close(conn);
		}
		
		request.setAttribute("message", message);
		
		return "/guestbook/modifyForm.jsp";
	}

}
