package action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.GuestBookDAO;
import guestbook.GuestBookDTO;
import logon.jdbcUtil;
import messagebook.ConnectionProvider;

public class GuestBookListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		Connection conn = ConnectionProvider.getConnection();

		List<GuestBookDTO> messageList = new ArrayList<>();
		
		
		String pageNum = request.getParameter("pageNum");
		
		if (pageNum == null || pageNum.isBlank()) {
			pageNum = "1";
		}
		
		final int pageSize = 5;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;

		
		try {
			messageList = GuestBookDAO.getInstance().getGuestBookList(conn, startRow, endRow);

			count = GuestBookDAO.getInstance().getGuestBookCount(conn);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			jdbcUtil.close(conn);
		}
		

		request.setAttribute("messageList", messageList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		request.setAttribute("count", count);
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);

		return "/guestbook/list.jsp";
	}

}
