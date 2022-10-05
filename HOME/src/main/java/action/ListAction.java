package action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;

public class ListAction implements CommandAction{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		
		String target = request.getParameter("target"); 
		String keyword = request.getParameter("keyword");
		 
		
		if(pageNum == null || pageNum.equals("null")) {
			pageNum = "1";
		}
		
		int pageSize = 5;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0; // 전체 아티클 개수
		int number = 0; // 글 목록에 표시할 글번호
		
		List articleList = null;
		BoardDAO dbPro = BoardDAO.getInstance();
		/* count = dbPro.getArticleCount(); */
		count = dbPro.getArticleCount(target, keyword);
		
		if(count > 0) {
//			articleList = dbPro.getArticles(startRow, endRow);
			articleList = dbPro.getArticles(target, keyword, startRow, endRow);
		}else {
			articleList = Collections.EMPTY_LIST;
		}
		
		number = count - (currentPage - 1) * pageSize;
		
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		
		return "/MVC/list.jsp";
		
	}
	
}
