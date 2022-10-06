package action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.GuestBookDAO;
import guestbook.GuestBookDTO;
import logon.jdbcUtil;
import messagebook.ConnectionProvider;

public class GuestDeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");

		String messageId = request.getParameter("messageId");

		Connection conn = ConnectionProvider.getConnection();
		GuestBookDTO getMessage = null;

		// 레코드 하나 꺼내서 보여주는거

		try {
			getMessage = GuestBookDAO.getInstance().getMessage(conn, messageId);

		} finally {
			jdbcUtil.close(conn);
		}

		request.setAttribute("getMessage", getMessage);

		return "/guestbook/deleteForm.jsp";
	}

}
