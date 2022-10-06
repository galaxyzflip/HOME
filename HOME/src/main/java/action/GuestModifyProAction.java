package action;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.GuestBookDAO;
import guestbook.GuestBookDTO;
import logon.jdbcUtil;
import messagebook.ConnectionProvider;

public class GuestModifyProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		GuestBookDTO message = new GuestBookDTO();
		message.setGuestName(request.getParameter("guestName"));
		message.setMessage(request.getParameter("message"));
		message.setPassword(request.getParameter("password"));
		message.setMessageId(Integer.parseInt(request.getParameter("messageId")));
		
		Connection conn = null;
		int check = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			check = GuestBookDAO.getInstance().modifyMessage(conn, message);
			
			
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			jdbcUtil.close(conn);
		}
		
		request.setAttribute("check", check);
		return "/guestbook/modifyPro.jsp";
	}

}
