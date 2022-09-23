package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import article.model.ArticleDTO;
import article.model.SearchArticleModel;
import logon.jdbcUtil;

public class ArticleDAO {

	private static ArticleDAO instance = new ArticleDAO();
	private ArticleDAO() {}
	
	public static ArticleDAO getInstance() {
		return instance;
	}


	public int selectCount(Connection conn, String target, String value) throws SQLException{
		PreparedStatement  pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		
		if(target == null || target.isBlank()) {
			
			sql = "select count(1) from article";	
		}else
			sql = "select count(1) from article where " + target + " like '%" + value + "%'";

		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			rs.next();
			
			return rs.getInt(1);
			
		}finally {
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
		}
		
	}
	
	
	//검색대상 Integer로 받아오기 오버로딩
	public int selectCount(Connection conn, Integer target, String value) throws SQLException{
		PreparedStatement  pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		ArrayList<String> list = SearchArticleModel.getTargetList(target);
		
		
		
		
		
		if(target == null) {
			
			sql = "select count(1) from article";	
		}else if(target == ){
			sql = "select count(1) from article where " + target + " like '%" + value + "%'";
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			rs.next();
			
			return rs.getInt(1);
			
		}finally {
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
		}
		
	}
	
	
	
	
	public List<ArticleDTO> select(Connection conn, int firstRow, int endRow, String target, String value) throws SQLException{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		if(target == null || target.isBlank()) {
			sql = "select article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title "
					+ "from(select rownum rnum, article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title "
					+ "from (select * from article m  order by m.sequence_no desc) where rownum <= ? ) where rnum >=?";
		}
		else {
			sql = "select article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title "
					+ "from(select rownum rnum, article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title "
					+ "from (select * from article m where " + target + " like '%" + value + "%'  order by m.sequence_no desc) where rownum <= ? ) where rnum >=?";
		}
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, firstRow);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				return Collections.emptyList();
			}
			List<ArticleDTO> articleList = new ArrayList<ArticleDTO>();
			
			do {
				ArticleDTO article = makeArticleFromResultSet(rs, false);
				articleList.add(article);
				
			}while(rs.next());
			return articleList;
			
		}finally {
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
		}
	}
	
	
	
	//검색대상 Integer로 받아오기 오버로딩
	public List<ArticleDTO> select(Connection conn, int firstRow, int endRow, Integer target, String value) throws SQLException{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		if(target == null) {
			sql = "select article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title "
					+ "from(select rownum rnum, article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title "
					+ "from (select * from article m  order by m.sequence_no desc) where rownum <= ? ) where rnum >=?";
		}
		else {
			sql = "select article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title "
					+ "from(select rownum rnum, article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title "
					+ "from (select * from article m where " + target + " like '%" + value + "%'  order by m.sequence_no desc) where rownum <= ? ) where rnum >=?";
		}
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, firstRow);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				return Collections.emptyList();
			}
			List<ArticleDTO> articleList = new ArrayList<ArticleDTO>();
			
			do {
				ArticleDTO article = makeArticleFromResultSet(rs, false);
				articleList.add(article);
				
			}while(rs.next());
			return articleList;
			
		}finally {
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
		}
	}

	
	private ArticleDTO makeArticleFromResultSet(ResultSet rs, boolean readContent) throws SQLException{
		ArticleDTO article = new ArticleDTO();
		
		article.setId(rs.getInt("article_id"));
		article.setGroupId(rs.getInt("group_id"));
		article.setSequenceNumber(rs.getString("sequence_no"));
		article.setPostingDate(rs.getTimestamp("posting_date"));
		article.setReadCount(rs.getInt("read_count"));
		article.setWriterName(rs.getString("writer_name"));
		article.setPassword(rs.getString("password"));
		article.setTitle(rs.getString("title"));
		
		if(readContent) {
			article.setContent(rs.getString("content"));
		}
		return article ;
	}
	
	
	public int insert(Connection conn, ArticleDTO article) throws SQLException{
		
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("insert into article(article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title, content) values(article_id_seq.nextval, ?,?,?,0,?,?,?,?)");
			pstmt.setInt(1, article.getGroupId());
			pstmt.setString(2, article.getSequenceNumber());
			pstmt.setTimestamp(3, new Timestamp(article.getPostingDate().getTime()));
			pstmt.setString(4, article.getWriterName());
			pstmt.setString(5, article.getPassword());
			pstmt.setString(6, article.getTitle());
			pstmt.setString(7, article.getContent());
			
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select article_id_seq.CURRVAL from dual"); //현재 시퀀스 번호(값 증가 없음)
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
			return -1;
			
		}finally {
			jdbcUtil.close(pstmt);
			jdbcUtil.close(stmt);
			jdbcUtil.close(rs);
		}
	}
	
	
	
	public ArticleDTO selectById(Connection conn, int articleId) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt=conn.prepareStatement("select * from article where article_id=?");
			pstmt.setInt(1, articleId);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return null;
			}
			ArticleDTO article = makeArticleFromResultSet(rs, true);
			return article;
			
		}finally {
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
		}
		
	}
	
	
	public void increaseReadCount(Connection conn, int articleId) throws SQLException{
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("update article set read_count = read_count+1 where article_id=?");
			pstmt.setInt(1, articleId);
			pstmt.executeUpdate();
			
		}finally {
			jdbcUtil.close(pstmt);
		}
	}
	
	
	public String selectLastSequenceNumber(Connection conn, String searchMaxSeqNum, String searchMinSeqNum) throws SQLException{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select min(sequence_no) from article where sequence_no < ? and sequence_no >=?");
			pstmt.setString(1, searchMaxSeqNum);
			pstmt.setString(2, searchMinSeqNum);
			
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return null;
				
			}
			
			return rs.getString(1);
			
		}finally {
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
		}
	}
	
	
	public int update(Connection conn, ArticleDTO article) throws SQLException{
		PreparedStatement pstmt = null;
				
		try {
			pstmt = conn.prepareStatement("update article set title=?, content=? where article_id=?");
			
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setInt(3, article.getId());
			
			return pstmt.executeUpdate();
		
		}finally {
			jdbcUtil.close(pstmt);
		}
		
	}
	
	
	public void delete(Connection conn, int articleId) throws SQLException{
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("delete from article where article_id=?");
			pstmt.setInt(1, articleId);
			pstmt.executeUpdate();
			
		}finally {
			jdbcUtil.close(pstmt);
			
		}
		
	}
	
	
}
