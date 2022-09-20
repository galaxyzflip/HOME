package messagebook;

import java.sql.Connection;
import java.sql.SQLException;
import logon.jdbcUtil;

//import MessageDAO;
//import MessageDAOProvider;
//import Message;
//import ConnectionProvider;

public class DeleteMessageService {

	private static DeleteMessageService instance = new DeleteMessageService();
	
	private DeleteMessageService() {}
	
	public static DeleteMessageService getInstance() {
		return instance;
	}
	
	public void deleteMessage(int messageId, String password) throws ServiceException, InvalidMessagePasswordException, MessageNotFoundException{
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			MessageDAO messageDao= MessageDAOProvider.getInstance().getMessageDao();
			MessageDTO message = messageDao.select(conn,  messageId);
			
			if(message == null) {
				throw new MessageNotFoundException("메시지가 없습니다.: " + messageId);
				
			}
			if(!message.hasPassword()) {
				throw new InvalidMessagePasswordException();
			}
			if(!message.getPassword().equals(password)) {
				throw new InvalidMessagePasswordException();
			}
			messageDao.delete(conn,  messageId);
			
			conn.commit();
			
		
		} catch(SQLException ex) {
			jdbcUtil.rollback(conn);
			throw new ServiceException("삭제 처리 중 에러가 발생했습니다. : " + ex.getMessage(), ex);
			
		} catch(InvalidMessagePasswordException ex) {
			jdbcUtil.rollback(conn);
			throw ex;
		} catch(MessageNotFoundException ex) {
			jdbcUtil.rollback(conn);
			throw ex;
		
		}finally {
			if(conn != null) {
				try {
					conn.setAutoCommit(false);
				}catch(SQLException e) {
					
				}
				jdbcUtil.close(conn);
			}
		}
		
	}
	
	
	
	
	
}
