package action;

import java.util.Collections;
import java.util.List;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.CommentDAO;
import board.CommentDTO;

public class CommentProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		//commenter
		//cPassword
		//contentNum
		//commentt
		
		request.setCharacterEncoding("utf-8");
		
		String commenter = request.getParameter("commenter");
		String cPassword = request.getParameter("cPassword");
		String commentt = request.getParameter("commentt");
		int contentNum = Integer.parseInt(request.getParameter("contentNum"));
		int comment_num = 0;
		
		CommentDTO comment = new CommentDTO();
		
		CommentDAO dbPro = CommentDAO.getInstance();
		comment_num = dbPro.getCommentCount(contentNum);
		
		
		comment.setCommenter(commenter);
		comment.setPasswd(cPassword);
		comment.setCommentt(commentt);
		comment.setContent_num(contentNum);
		comment.setReg_date(new Timestamp(System.currentTimeMillis()));
		comment.setIp(request.getRemoteAddr());
		comment.setComment_num(comment_num + 1);
		
		
		dbPro.insertComment(comment);
		
		
		request.setAttribute("contentNum", comment.getContent_num());
		
		return "/MVC/commentPro.jsp";
		
	}

	
}
