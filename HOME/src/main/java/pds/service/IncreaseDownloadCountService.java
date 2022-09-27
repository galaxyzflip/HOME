package pds.service;

import java.sql.Connection;
import java.sql.SQLException;

import pds.dao.PdsItemDAO;
import logon.jdbcUtil;
import messagebook.ConnectionProvider;


public class IncreaseDownloadCountService {
	private static IncreaseDownloadCountService instance = new IncreaseDownloadCountService();
	private IncreaseDownloadCountService() {}
	
	public static IncreaseDownloadCountService getInstance() {
		return instance;
	}
	
	public boolean increaseCount(int id) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			int updateCount = PdsItemDAO.getInstance().increaseCount(conn, id);
			return updateCount == 0 ? false : true;
			
		}catch(SQLException ex) {
			throw new RuntimeException("DB 처리 에러 발셍: " + ex.getMessage(), ex);
		}finally {
			jdbcUtil.close(conn);
		}
	}
	
	
}
