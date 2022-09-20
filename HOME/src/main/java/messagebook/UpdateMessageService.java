package messagebook;
import java.sql.*;

import logon.jdbcUtil;

public class UpdateMessageService {

	private static UpdateMessageService instance = new UpdateMessageService();
	
	private UpdateMessageService() {}
	
	public static UpdateMessageService getInstance() {
		return instance;
	}
	
	public MessageDTO getMessageDto(int messageId) throws MessageNotFoundException {
		
		Connection conn = null;
		MessageDTO messageDto = null;
		
		
		try {
			conn = ConnectionProvider.getConnection();
			
			MessageDAO messageDao = MessageDAOProvider.getInstance().getMessageDao();
			messageDto = messageDao.select(conn, messageId);
			
			if(messageDto == null) {
				throw new MessageNotFoundException("메시지가 없습니다 : " + messageId);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.close(conn);
		}
		return messageDto;
	}
	
	
	public void updateMessage(int messageId, String password, String guestName, String message) 
			throws ServiceException,  InvalidMessagePasswordException, MessageNotFoundException{
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			MessageDAO messageDao = MessageDAOProvider.getInstance().getMessageDao();
			MessageDTO messageDto = messageDao.select(conn, messageId);
			
			if(messageDto == null) {
				throw new MessageNotFoundException("메시지가 없습니다. : " + messageId);
			}
			
			if(!messageDto.hasPassword()) {
				throw new InvalidMessagePasswordException();
			}
			
			if(!messageDto.getPassword().equals(password)) {
				throw new InvalidMessagePasswordException();
			}
			
			messageDao.update(conn, messageId, guestName, message);
			
			
		}catch(SQLException ex) {
			throw new ServiceException("삭제 처리 중 에러가 발생했습니다 : " + ex.getMessage(), ex);
	
		}catch(InvalidMessagePasswordException ex) {
			throw ex;
		
		}catch(MessageNotFoundException ex) {
			throw ex;
			
		}finally {
			jdbcUtil.close(conn);
		}
	}
}
