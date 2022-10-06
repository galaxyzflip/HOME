package guestbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logon.jdbcUtil;

public class GuestBookDAO {
	
	private static GuestBookDAO instance = new GuestBookDAO();
	private GuestBookDAO() {}
	
	public static GuestBookDAO getInstance() {
		return instance;
	}
	

	public int getGuestBookCount(Connection conn) {
		
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(1) from guestbook_message");
			if(rs.next()) {
				count = rs.getInt(1);
				
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			jdbcUtil.close(stmt);
			jdbcUtil.close(rs);
		}
		
		
		return count;
	}
	
	public List<GuestBookDTO> getGuestBookList(Connection conn, int startRow, int endRow){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GuestBookDTO> messageList = new ArrayList<>();;
		
		try {
			pstmt = conn.prepareStatement("select * "
					+ " from (select message_id, guest_name, message, rownum rnum "
					+ " from (select * from guestbook_message order by message_id desc)) "
					+ " where rnum between ? and ?" );
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GuestBookDTO message = setRs(rs);
				messageList.add(message);
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
		}
		
		return messageList;
	}
	
	public void writeMessage(Connection conn, GuestBookDTO message) {
		
		PreparedStatement pstmt = null;
		int check = 0;
		
		try {
			pstmt = conn.prepareStatement("insert into guestbook_message(message_id, guest_name, password, message) values(message_id_seq.nextval , ?, ? ,?)");
			pstmt.setString(1, message.getGuestName());
			pstmt.setString(2, message.getPassword());
			pstmt.setString(3, message.getMessage());
			
			check = pstmt.executeUpdate();
			
			if(check == 0) {
				new SQLException("insert0");
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			jdbcUtil.close(pstmt);
			
		}
		
	}
	
	public GuestBookDTO setRs(ResultSet rs) {
		GuestBookDTO message = new GuestBookDTO();
		
		
		try {
			message.setMessageId(rs.getInt("message_id"));
			message.setGuestName(rs.getString("guest_name"));
			message.setMessage(rs.getString("message"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return message;
	}

	public GuestBookDTO getMessage(Connection conn, String messageId) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GuestBookDTO message = null;
		
		try {
			pstmt = conn.prepareStatement("select * from guestbook_message where message_id = ?");
			pstmt.setString(1, messageId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				message = setRs(rs);
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
			
		}
		
		return message;
	}
	

	public int modifyMessage(Connection conn, GuestBookDTO message) {
		
		PreparedStatement pstmt = null;
		int check = 0;
		int checkPassword = 0;
		
		try {
			
			conn.setAutoCommit(false);
			
			checkPassword = checkPassword(conn, message);
			if(checkPassword == 1) {
				pstmt = conn.prepareStatement("update guestbook_message set guest_name = ? , message = ? where message_id = ?");
				pstmt.setString(1, message.getGuestName());
				pstmt.setString(2, message.getMessage());
				pstmt.setInt(3, message.getMessageId());
				
				check = pstmt.executeUpdate();
				if(check > 1) {
					jdbcUtil.rollback(conn);
					throw new RuntimeException("업데이트 1개 이상");
				}
			}else {
				check = -1;
			}
			conn.commit();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			jdbcUtil.close(pstmt);
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return check;
	}
	
	
	//x = 1 정상삭제 ; x > 1 사고 ; x = -1 비번 틀림 ; x = 0 오류
	public int deleteMessage(Connection conn, GuestBookDTO message) {
		PreparedStatement pstmt = null;
		int check = 0;
		int checkPassword = 0;

		try {

			conn.setAutoCommit(false);

			checkPassword = checkPassword(conn, message);
			if (checkPassword == 1) {
				pstmt = conn.prepareStatement("delete guestbook_message where message_id = ?");
				pstmt.setInt(1, message.getMessageId());

				check = pstmt.executeUpdate();
				if (check > 1) {
					jdbcUtil.rollback(conn);
					throw new RuntimeException("삭제 1개 이상");
				}
			} else {
				check = -1;
			}
			conn.commit();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(pstmt);
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return check;
	}

	
	
	//비번 확인
	// check = 1 비번맞음, -1비번 틀림, 0 조회 안됨(잘못된 요청)
	public int checkPassword(Connection conn, GuestBookDTO message) {
		
		int check = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPassword = "";
		
		try {
			pstmt = conn.prepareStatement("select password from guestbook_message where message_id = ?");
			pstmt.setInt(1, message.getMessageId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbPassword = rs.getString("password");
			}
			
			if(message.getPassword().equals(dbPassword)) {
				check = 1;
			}else {
				check = -1;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
		}
		
		return check;
	}

	
	
	
}













