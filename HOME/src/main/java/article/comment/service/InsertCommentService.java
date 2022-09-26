package article.comment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import article.comment.DAO.CommentDAO;
import article.comment.model.CommentDTO;
import article.comment.model.InsertCommentModel;
import logon.jdbcUtil;
import messagebook.ConnectionProvider;

public class InsertCommentService {
	
	private InsertCommentService() {}
	private static InsertCommentService instance = new InsertCommentService();
	public static InsertCommentService getInstance() {
		return instance;
	}
	

	
	//댓글 입력 DAO 메소드 호출
	public CommentDTO insertComment(InsertCommentModel model, int articleNumber) {
		Connection conn = null;
		CommentDTO commentDto = new CommentDTO();
		CommentDAO commentDao = CommentDAO.getInstance();
		int check = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int CommentNumber = commentDao.getCommentNumber(conn, articleNumber);
			
			commentDto = model.toComment();
			commentDto.setRegDate(new Timestamp(System.currentTimeMillis()));
			commentDto.setArticleNumber(articleNumber);
			commentDto.setCommentNumber(CommentNumber+1);
			
			commentDao.insertComment(conn, commentDto);
			
			if(check == 1) {
				return commentDto;
			} 
			/*
			 * else throw new SQLException("DB 삽입 안됨 : " + articleNumber);
			 */

			
		} catch(SQLException ex) {
			throw new RuntimeException("DB에러: " + ex.getMessage(), ex);
			
		}finally {
			if(conn != null) {
				try {
					conn.setAutoCommit(true);
				}catch(SQLException e) {
					
				}
				jdbcUtil.close(conn);;
			}
		}
		return commentDto;
	}
	
	
	
	//댓글번호 생성
	public int createCommentNumber(int ArticleNumber) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			CommentDAO commentDao = CommentDAO.getInstance();
			int commentNumber = commentDao.getCommentNumber(conn, ArticleNumber);
			return commentNumber;
			
			
		}catch(Exception ex) {
			
		}finally {
			jdbcUtil.close(conn);
		}
		
		
		
		return 0;
	}
	
}
