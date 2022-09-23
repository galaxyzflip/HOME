package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import article.dao.ArticleDAO;
import article.model.ArticleDTO;
import logon.jdbcUtil;
import messagebook.ConnectionProvider;

public class ReadArticleService {
	private static ReadArticleService instance = new ReadArticleService();
	
	private ReadArticleService() {}
	
	public static  ReadArticleService getInstance() { 
		return instance;
	}
	
	public ArticleDTO readArticle(int articleId) throws ArticleNotFoundException{
		return selectArticle(articleId, true);
	}
	
	public ArticleDTO selectArticle(int articleId, boolean increaseCount) throws ArticleNotFoundException{
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			ArticleDAO articleDao = ArticleDAO.getInstance();
			ArticleDTO article = articleDao.selectById(conn, articleId);
			
			if(article == null) {
				throw new ArticleNotFoundException("게시글이 존재하지 않습니다 :" + articleId);
			}
			if(increaseCount) {
				articleDao.increaseReadCount(conn,  articleId);;
				article.setReadCount(article.getReadCount() + 1);
			}
			return article;
			
			
			
		}catch(SQLException ex) {
			throw new RuntimeException("DB에러 발생: " + ex.getMessage(), ex);
		
		}finally {
			jdbcUtil.close(conn);
		}
		
	}
	
	
	
	public ArticleDTO getArticle(int articleId) throws ArticleNotFoundException{
		return selectArticle(articleId, false);
	}
	
}
