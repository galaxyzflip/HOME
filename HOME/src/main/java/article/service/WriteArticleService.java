package article.service;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.Date;

import article.dao.ArticleDAO;
import article.model.ArticleDTO;
import article.model.WritingRequest;

import logon.jdbcUtil;
import messagebook.ConnectionProvider;


public class WriteArticleService {

	private static WriteArticleService instance = new WriteArticleService();
	
	public static WriteArticleService getInstance() {
		return instance;
	}
	
	private WriteArticleService() {}
	
	public ArticleDTO write(WritingRequest writingRequest) throws IdGenerationFailedException{
		
		int groupId = IdGenerator.getInstance().generateNextId("article");
		
		ArticleDTO article = writingRequest.toArticle();
		
		article.setGroupId(groupId);
		article.setPostingDate(new Date());
		DecimalFormat decimalFormat = new DecimalFormat("0000000000");
		article.setSequenceNumber(decimalFormat.format(groupId) + "999999");
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int articleId = ArticleDAO.getInstance().insert(conn, article);
			
			if(articleId == -1) {
				jdbcUtil.rollback(conn);
				throw new RuntimeException("DB 삽입 안됨:" + articleId);
			}
			conn.commit();
			
			article.setId(articleId);
			return article;
			
		} catch (SQLException e) {
			jdbcUtil.rollback(conn);
			throw new RuntimeException("DB에러: " + e.getMessage(), e);
			
		} finally {
			if(conn != null) {
				try {
					conn.setAutoCommit(true);
				}catch(SQLException e) {
				}
				
			}
			jdbcUtil.close(conn);
		}
		
	}
	
	
}
	

