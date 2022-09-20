package messagebook;
import java.sql.*;

public class UpdateMessageService {

	private static UpdateMessageService instance = new UpdateMessageService();
	
	private UpdateMessageService() {}
	
	public static UpdateMessageService getInstance() {
		return instance;
	}
	
	public void updateMessage(int messageId, String password, String message) throws ServiceException{
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn,
		}
	}
}
