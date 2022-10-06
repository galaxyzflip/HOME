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
			message = setRs(rs);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
			
		}
		
		return message;
	}
	
	
}

