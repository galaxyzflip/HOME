package action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.GuestBookDAO;
import guestbook.GuestBookDTO;
import logon.jdbcUtil;
import messagebook.ConnectionProvider;

public class GuestBookWriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		Connection conn = ConnectionProvider.getConnection();
		
		GuestBookDTO message = new GuestBookDTO();
		
		message.setGuestName(request.getParameter("guestName"));
		message.setPassword(request.getParameter("password"));
		message.setMessage(request.getParameter("message"));
		
		try {
			GuestBookDAO.getInstance().writeMessage(conn, message);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			jdbcUtil.close(conn);
			
		}
		
		return "/guestbook/writePro.jsp";
		
	}

}
