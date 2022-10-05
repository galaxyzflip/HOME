package action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.*;

public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		
		int num = Integer.parseInt(request.getParameter("num")); // 글번호
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO dbPro = BoardDAO.getInstance();
		BoardDTO article = dbPro.getArticle(num);
		
		/*
		 * request.setAttribute("num", new Integer(num));
		 * request.setAttribute("pageNum", new Integer(pageNum));
		 */
		
		request.setAttribute("num", num);
		request.setAttribute("pageNum", Integer.parseInt(pageNum));
		request.setAttribute("article", article);
		
		
		//댓글작업
		
		String contentPageNum = request.getParameter("contentPageNum");
//		int contentNum = Integer.parseInt(request.getParameter("num"));
		int contentNum = num;
		
		if(contentPageNum == null || contentPageNum.equals("null")) {
			contentPageNum = "1";
		}
		
		int pageSize = 5;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0; // 전체 아티클 개수
		int number = 0;
		
		List commentList = null;
		CommentDAO commentPro = CommentDAO.getInstance();
		
		count = commentPro.getCommentCount(contentNum);
		
		if(count > 0) {
			commentList = commentPro.getComments(contentNum, startRow, endRow);
		}else {
			commentList = Collections.EMPTY_LIST;
		}
		
		number = count - (currentPage - 1) * pageSize;
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("number", number);
		request.setAttribute("commentList", commentList);
		
		request.setAttribute("pageSize", pageSize);
		
		
		
		return "/MVC/content.jsp";
		
	}
	
}
