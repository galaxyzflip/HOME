package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import article.model.ArticleDTO;
import article.dao.ArticleDAO;
import article.model.ArticleListRequest;

import logon.jdbcUtil;
import messagebook.ConnectionProvider;


//import ArticleDAO;
//import ArticleDTO
//import ArticleListModel

public class ListArticleService {

	private static ListArticleService instance = new ListArticleService();
	private ListArticleService() {}
	
	public static ListArticleService getInstance() {
		return instance;
	}
	
	public static final int COUNT_PER_PAGE = 4;
	

	
	/*
	 * public ArticleListRequest getArticleList(int requestPageNumber, String
	 * target, String searchValue) { if (requestPageNumber < 0) { throw new
	 * IllegalArgumentException("page number < 0:" + requestPageNumber); }
	 * 
	 * ArticleDAO articleDao = ArticleDAO.getInstance(); Connection conn = null;
	 * 
	 * try { conn = ConnectionProvider.getConnection(); int totalArticleCount =
	 * articleDao.selectCount(conn, target, searchValue);
	 * 
	 * if (totalArticleCount == 0) { return new ArticleListRequest(); }
	 * 
	 * int totalPageCount = calculateTotalPageCount(totalArticleCount);
	 * 
	 * int firstRow = (requestPageNumber - 1) * COUNT_PER_PAGE + 1; int endRow =
	 * firstRow + COUNT_PER_PAGE - 1;
	 * 
	 * if (endRow > totalArticleCount) { endRow = totalArticleCount; }
	 * 
	 * List<ArticleDTO> articleList = articleDao.select(conn, firstRow, endRow,
	 * target, searchValue);
	 * 
	 * ArticleListRequest articleListView = new ArticleListRequest(articleList,
	 * requestPageNumber, totalPageCount, firstRow, endRow); return articleListView;
	 * 
	 * } catch (SQLException ex) { throw new RuntimeException("DB 에러 발생 : " +
	 * ex.getMessage(), ex);
	 * 
	 * } finally { jdbcUtil.close(conn);
	 * 
	 * }
	 * 
	 * }
	 */
	 
	
	
	//검색대상 Integer로 가져오기 오버로딩
	public ArticleListRequest getArticleList(int requestPageNumber, int target, String searchValue) {
		if(requestPageNumber <0) {
			throw new IllegalArgumentException("page number < 0:" + requestPageNumber);
		}
		
		ArticleDAO articleDao = ArticleDAO.getInstance();
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			int totalArticleCount = articleDao.selectCount(conn, target, searchValue);
			
			if(totalArticleCount == 0) {
				return new ArticleListRequest();
			}
			
			int totalPageCount = calculateTotalPageCount(totalArticleCount);
			
			int firstRow = (requestPageNumber - 1) * COUNT_PER_PAGE + 1;
			int endRow = firstRow + COUNT_PER_PAGE - 1;
			
			
			if(endRow > totalArticleCount) {
				endRow = totalArticleCount;
			}
			
			List<ArticleDTO> articleList = articleDao.select(conn, firstRow, endRow, target, searchValue);
			
			ArticleListRequest articleListView = new ArticleListRequest(articleList, requestPageNumber, totalPageCount, firstRow, endRow);
			return articleListView;
			
			
		}catch(SQLException ex) {
			throw new RuntimeException("DB 에러 발생 : " + ex.getMessage(), ex);
			
		}finally {
			jdbcUtil.close(conn);
			
		}
		
	}
	
	
	private int calculateTotalPageCount(int totalArticleCount) {
		if(totalArticleCount ==0) {
			return 0;
		}
		
		int pageCount = totalArticleCount / COUNT_PER_PAGE;
		if(totalArticleCount % COUNT_PER_PAGE > 0) {
			pageCount++;
		}
		
		return pageCount;
	}
	
	
}


