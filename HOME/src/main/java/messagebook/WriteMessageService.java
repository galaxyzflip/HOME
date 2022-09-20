package messagebook;

import java.sql.Connection;
import java.sql.SQLException;
import logon.jdbcUtil;

//import messageDAO;
//import MessageDAoProvider;
//import Message;
//import ConnectionProvider;

public class WriteMessageService {

	private static WriteMessageService instance = new WriteMessageService();
	
	private WriteMessageService() {}
	
	public static WriteMessageService getInstance() {
		return instance;
	}
	
	public void write(MessageDTO message) throws ServiceException{
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			MessageDAO messageDao = MessageDAOProvider.getInstance().getMessageDao();
			messageDao.insert(conn,  message);
			
		}catch(SQLException ex) {
			throw new ServiceException("메시지 등록 실패: " + ex.getMessage(), ex);
		
		}finally {
			jdbcUtil.close(conn);
		}
	}
	
	
}
