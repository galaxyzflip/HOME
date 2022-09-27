package pds.service;

import java.sql.*;

import pds.dao.PdsItemDAO;
import pds.model.AddRequest;
import pds.model.PdsItem;
import logon.jdbcUtil;
import messagebook.ConnectionProvider;



public class AddPdsItemService {

	private static AddPdsItemService instance = new AddPdsItemService();
	private AddPdsItemService() {}
	
	public static AddPdsItemService getInstance() {
		return instance;
	}
	
	
	public PdsItem add(AddRequest request) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			PdsItem pdsItem = request.toPdsItem();
			int id = PdsItemDAO.getInstance().insert(conn, pdsItem);
			
			if(id == -1) {
				jdbcUtil.rollback(conn);
				throw new RuntimeException("DB 삽입 안됨");
			}
			pdsItem.setId(id);
			
			conn.commit();
			return pdsItem;
			
		}catch(SQLException ex) {
			jdbcUtil.rollback(conn);
			throw new RuntimeException(ex);
			
		}finally {
			if(conn != null) {
				try {
					conn.setAutoCommit(true);;
				}catch(SQLException ex) {
					
				}
				
			}
			jdbcUtil.close(conn);
		}
		
	}
	
	
	
}
