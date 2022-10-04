package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.*;

public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num")); // 글번호
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO dbPro = BoardDAO.getInstance();
		BoardDTO article = dbPro.getArticle(num);
		
		/*
		 * request.setAttribute("num", new Integer(num));
		 * request.setAttribute("pageNum", new Integer(pageNum));
		 */
		
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum.valueOf(pageNum));
		request.setAttribute("article", article);
		
		return "/MVC/content.jsp";
		
	}
	
}
