package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleDAO;
import article.model.ArticleDTO;
import article.model.UpdateRequest;
import logon.jdbcUtil;
import messagebook.ConnectionProvider;


public class UpdateArticleService {
	
	private static UpdateArticleService instance = new UpdateArticleService();
	private UpdateArticleService() {}
	
	public static UpdateArticleService getInstance() {
		return instance;
	}
	
	public ArticleDTO update(UpdateRequest updateRequest) throws ArticleNotFoundException, InvalidPasswordException{
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			ArticleCheckHelper checkHelper = new ArticleCheckHelper();
			checkHelper.checkExistsAndPassword(conn, updateRequest.getArticleId(), updateRequest.getPassword());
			
			ArticleDTO updateArticle = new ArticleDTO();
			updateArticle.setId(updateRequest.getArticleId());
			updateArticle.setTitle(updateRequest.getTitle());
			updateArticle.setContent(updateRequest.getContent());
			
			
			ArticleDAO articleDao = ArticleDAO.getInstance();
			
			int updateCount = articleDao.update(conn, updateArticle);
			if(updateCount == 0) {
				throw new ArticleNotFoundException("게시글이 존재하지 않습니다 :" + updateRequest.getArticleId());
			}
			
			ArticleDTO article = articleDao.selectById(conn, updateRequest.getArticleId());
			
			conn.commit();
			
			return article;
		
		
		}catch(SQLException ex) {
			jdbcUtil.rollback(conn);
			throw new RuntimeException("DB 에러 발생 : " + ex.getMessage(), ex);
			
		}catch(ArticleNotFoundException ex) {
			jdbcUtil.rollback(conn);
			throw ex;
			
		}catch(InvalidPasswordException ex) {
			jdbcUtil.rollback(conn);
			throw ex;
			
		}finally {
			if(conn != null) {
				try {
					conn.setAutoCommit(true);
				}catch(SQLException ex) {
					
				}
				jdbcUtil.close(conn);
			}
		}
		
	}
	
	
}
