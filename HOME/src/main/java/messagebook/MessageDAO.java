//추상 메소드
//dbms에 맞는 클래스를 작성하고 web.xml 파일에 서블렛 추가하여 사용
//web.xml 서블렛에 따라 dbms에 맞는 쿼리를 사용한다. 


package messagebook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import logon.jdbcUtil;
//import Message;


public abstract class MessageDAO {

	//dbms에 따라 쿼리가 달라지기 때문에 상속클래스에서 구현한다.
	//시퀀스 부분이 다름...
		public abstract int insert(Connection conn, MessageDTO message)
		throws SQLException;
		
		
		//방문록 1레코드 자바빈에 담아서 리턴... 어디서 쓰더라
		public MessageDTO select(Connection conn, int messageId) throws SQLException{
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				pstmt = conn.prepareStatement("select * from guestbook_message where message_id = ?");
				pstmt.setInt(1, messageId);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					return makeMessageFromResultSet(rs); //rs 커서의 레코드 빈즈객체에 담아줌
				} else {
					return null;
				}
				
				
			}finally {
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
			}
			
			
		}
		
		//rs.next()가 있을때(조회결과 있을때) MessageDTO(빈즈) 객체에 담아주는 역할...
		protected MessageDTO makeMessageFromResultSet(ResultSet rs) throws SQLException{
			
			MessageDTO message = new MessageDTO();
			message.setId(rs.getInt("message_id"));
			message.setGuestName(rs.getString("guest_name"));
			message.setPassword(rs.getString("password"));
			message.setMessage(rs.getString("message"));
			
			return message;
		}
		
		//방명록 총 개수... 
		
		public int selectCount(Connection conn) throws SQLException{
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select count(1) from guestbook_message");
				rs.next();
				return rs.getInt(1);
				
			
			}finally {
				jdbcUtil.close(stmt);
				jdbcUtil.close(rs);
			}
			
		}
		
		//상속받은 클래스에서 메소드 구현(dbms에 따른 쿼리 차이)
		public abstract List<MessageDTO> selectList(Connection conn, int firstRow, int endRow) throws SQLException;
		
		
		//방명록 1개 삭제하는 기능
		//바로 쓰지않고 DeleteMessageService.java에서 비번 확인 후 실행
		public int delete(Connection conn, int messageId) throws SQLException{
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement("delete from guestbook_message where message_id=?");
				pstmt.setInt(1, messageId);
				return pstmt.executeUpdate();
			
			}finally {
				jdbcUtil.close(pstmt);
				jdbcUtil.close(rs);
			}
			
		}
		
		public int update(Connection conn, int messageId, String guestName,  String message) throws SQLException{
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				pstmt = conn.prepareStatement("update guestbook_message set guest_name=?,  message =? where message_id=?");
				pstmt.setString(1, guestName);
				pstmt.setString(2, message);
				pstmt.setInt(3, messageId);
				return pstmt.executeUpdate();
			
			}finally {
				jdbcUtil.close(pstmt);
				jdbcUtil.close(rs);
			}
			
		}
		
		
		
		
}
