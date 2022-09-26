package article.comment.service;

import java.sql.Connection;
import java.util.List;

import article.comment.DAO.CommentDAO;
import article.comment.model.CommentDTO;
import article.comment.model.CommentListModel;
import logon.jdbcUtil;
import messagebook.ConnectionProvider;

public class CommentListService {

	private static CommentListService instance = new CommentListService();
	private CommentListService () {}
	
	public static CommentListService getInstance() {
		return instance;
	}
	
	public static final int COUNT_PER_PAGE = 4;
	
	
	public CommentListModel getCommentLists(int articleNumber, int requestPageNumber){
		Connection conn = null;
		CommentDAO commentDao = CommentDAO.getInstance();
		
		try {
			conn = ConnectionProvider.getConnection();
			int totalCommentCount = commentDao.getCommentCount(conn, articleNumber);
			
			if(totalCommentCount == 0) {
				return new CommentListModel();
			}
			
			int totalPageCount = calculateTotlaPageCount(totalCommentCount);
			
			int firstRow = (requestPageNumber - 1) * COUNT_PER_PAGE + 1;
			int endRow = firstRow + COUNT_PER_PAGE - 1;
			
			if(endRow > totalCommentCount) {
				endRow = totalCommentCount;
			}
			
			List<CommentDTO> commentLists = commentDao.getCommentLists(conn, articleNumber, firstRow, endRow);
			CommentListModel commentListModel = new CommentListModel(commentLists, requestPageNumber, totalPageCount, firstRow, endRow);
			
			
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			jdbcUtil.close(conn);
		}
		
		
		return null;

	}

	private int calculateTotlaPageCount(int totalCommentCount) {
		if(totalCommentCount == 0) {
			return 0;
		}
		
		int pageCount = totalCommentCount / COUNT_PER_PAGE;
		if(totalCommentCount % COUNT_PER_PAGE > 0) {
			pageCount++;
		}
		
		return pageCount;
	}
	
	
}
