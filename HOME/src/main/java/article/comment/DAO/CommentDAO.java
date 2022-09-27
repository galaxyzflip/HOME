package article.comment.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import article.comment.model.CommentDTO;
import article.comment.model.DeleteCommentModel;
import article.comment.model.UpdateCommentModel;
import logon.jdbcUtil;

public class CommentDAO {

	private CommentDAO() {}
	private static  CommentDAO instance = new CommentDAO();
	
	public static CommentDAO getInstance() {
		return instance;
	}
	
	public int insertComment(Connection conn, CommentDTO comment) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = 0;
		
		try {
		pstmt = conn.prepareStatement("insert into article_comment"
				+ "(article_num, comment_num, commenter, comment_content, password, reg_date, ip) "
				+ "values(?,?,?,?,?,?,?)");
		
		pstmt.setInt(1, comment.getArticleNumber());
		pstmt.setInt(2,comment.getCommentNumber() );
		pstmt.setString(3, comment.getCommenter());
		pstmt.setString(4, comment.getCommentContent());
		pstmt.setString(5, comment.getPassword());
		pstmt.setTimestamp(6, comment.getRegDate());
		pstmt.setString(7, comment.getIp());
		
		check = pstmt.executeUpdate();
		
		if(check > 1) {
			return 1; //insert 성공
		} else
			return 0; // 실패
			
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
		}
		
	}
	
	public CommentDTO updateComment(Connection conn, UpdateCommentModel model) {
		
		return null;
	}
	
	public int deleteComment(Connection conn, DeleteCommentModel model) {
		return 0;
	}
	
	
	
	
	
	
	public List<CommentDTO> getCommentLists(Connection conn, int articleNumber, int firstRow, int endRow){
		ArrayList<CommentDTO> commentLists = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			sql="select  article_num, comment_num, commenter, comment_content, reg_date, ip , rownum "
					+  " from (select * from article_comment where article_num =21 order by rownum desc) "
					+ " where rownum between ? and ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, firstRow);
			
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return Collections.emptyList();
			}
			
				do {
					CommentDTO commentDto = makeCommentFromResultSet(rs);
					commentLists.add(commentDto);
				}while(rs.next());
			
			
			
		}catch(SQLException ex) {
			
		}finally {
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
		}
		
		
		return commentLists;
	}
	
	private CommentDTO makeCommentFromResultSet(ResultSet rs) {
		CommentDTO comment = new CommentDTO();
		
		try {
			comment.setArticleNumber(rs.getInt("article_num"));
			comment.setCommentNumber(rs.getInt("comment_num"));
			comment.setCommenter(rs.getString("commenter"));
			comment.setCommentContent(rs.getString("comment_content"));
			comment.setIp(rs.getString("ip"));
			comment.setRegDate(rs.getTimestamp("reg_date"));
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comment;
	}
	
	
	
	
	
	
	//해당 아티클의 댓글수 구하는 메소드
	public int getCommentCount(Connection conn, int articleNumber) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement("select count(1) from article_comment where article_num = ?");
			pstmt.setInt(1, articleNumber);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count =  rs.getInt(1);
			}
		
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		finally {
		}
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
		
		return count;
	}
	
	//해당 아티클의 가장 큰 댓글번호 구하기 // 다음 commentNumber 값 사용하기 위해
	public int getCommentNumber(Connection conn, int articleNumber) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int commentNumber = 0;
		
		try {
			pstmt = conn.prepareStatement("select max(comment_num) from article_comment where article_num = ?");
			pstmt.setInt(1, articleNumber);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				commentNumber =  rs.getInt(1);
			}
		
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		finally {
		}
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
		
		return commentNumber;
	}
	
	
}
