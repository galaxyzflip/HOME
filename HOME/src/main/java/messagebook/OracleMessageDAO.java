//abstract class 인 MessageDAO 클래스의 insert, selectList 메소드를 오라클db에 맡게 구현
//

package messagebook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import logon.jdbcUtil;
//import MessagDAO;
//import Message;

public class OracleMessageDAO extends MessageDAO{

	
	//방명록 입력
	//오라클에 맞게 되어있다... 시퀀스 처리
	public int insert(Connection conn, MessageDTO message) throws SQLException{
		
		PreparedStatement pstmt= null;
		
		try {
			pstmt = conn.prepareStatement("insert into guestbook_message(message_id, guest_name, password, message) values(message_id_seq.nextval, ?, ?, ?)");
			pstmt.setString(1, message.getGuestName());
			pstmt.setString(2, message.getPassword());
			pstmt.setString(3, message.getMessage());
			return pstmt.executeUpdate();
		
		
		}finally {
			jdbcUtil.close(pstmt);
		}
		
	}
	
	//셀렉트 쿼리문이 dbms에 따라 다르므로 여기서 구현하였다.
	public List<MessageDTO> selectList(Connection conn, int firstRow, int endRow) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select message_id, guest_name, password, message from(select rownum rnum, message_id, guest_name, password, message from(select * from guestbook_message m order by m.message_id desc) where rownum <= ?) where rnum >= ?");
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, firstRow);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				List<MessageDTO> messageList = new ArrayList<>();
				do {
					messageList.add(super.makeMessageFromResultSet(rs));
				}while(rs.next());
				return messageList;
				
			}else {
				return Collections.emptyList();
			}
			
			
			
		}finally {
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
		}
		
	}
	
	
	
	
}

