package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleDAO;
import article.model.DeleteRequest;
import logon.jdbcUtil;
import messagebook.ConnectionProvider;


public class DeleteArticleService {

	private static DeleteArticleService instance = new DeleteArticleService();
	private DeleteArticleService() {}
	
	public static DeleteArticleService getInstanc() {
		return instance;
	}
	
	public void deleteArticle(DeleteRequest deleteRequest) throws ArticleNotFoundException, InvalidPasswordException{
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			ArticleCheckHelper checkHelper = new ArticleCheckHelper();
			checkHelper.checkExistsAndPassword(conn, deleteRequest.getArticleId(), deleteRequest.getPassword());
			
			ArticleDAO articleDao = ArticleDAO.getInstance();
			articleDao.delete(conn, deleteRequest.getArticleId());
			
			conn.commit();
		
		}catch(SQLException ex) {
			jdbcUtil.rollback(conn);
			throw new RuntimeException(ex);
			
		}catch(ArticleNotFoundException ex) {
			jdbcUtil.rollback(conn);
			throw ex;
			
		}catch(InvalidPasswordException ex) {
			jdbcUtil.rollback(conn);
			throw ex;
		
		}finally {
			if( conn != null) {
				try {
					conn.setAutoCommit(true);
				}catch(SQLException e) {
					
				}
			}
			jdbcUtil.close(conn);
		}
		
		
		
	}
	
}
