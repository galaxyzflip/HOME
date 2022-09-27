package pds.service;

import java.sql.*;
import pds.dao.PdsItemDAO;
import pds.model.AddRequest;
import pds.model.PdsItem;
import logon.jdbcUtil;
import messagebook.ConnectionProvider;


public class UpdatePdsItemService {

	private static UpdatePdsItemService instance = new UpdatePdsItemService();
	private UpdatePdsItemService() {}
	public static UpdatePdsItemService getInstance() {
		return instance;
	}
	
	
	public PdsItem update(AddRequest request, int pdsId) {
		Connection conn = null;
		PdsItem pdsItem = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			pdsItem = request.toPdsItem();
			int check = PdsItemDAO.getInstance().update(conn, pdsItem, pdsId);
			
			if(check == -1) {
				jdbcUtil.rollback(conn);
				throw new RuntimeException("DB 수정 안됨");
			}
			pdsItem.setId(pdsId);
			
			conn.commit();
			
			
		}catch(SQLException ex) {
			jdbcUtil.rollback(conn);
			
		}finally {
			if(conn != null) {
				try {
					conn.setAutoCommit(true);
				}catch(SQLException ex) {
					
				}
				
			}
			jdbcUtil.close(conn);
		}
		return pdsItem;
	}
	
	
}
