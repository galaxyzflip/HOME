package pds.service;


import java.sql.Connection;
import java.sql.SQLException;

import pds.dao.PdsItemDAO;
import pds.model.PdsItem;
import logon.jdbcUtil;
import messagebook.ConnectionProvider;


public class GetPdsItemService {

	private static GetPdsItemService instance = new GetPdsItemService();
	private GetPdsItemService() {}
	public static GetPdsItemService getInstance() {
		return instance;
	}
	
	public PdsItem getPdsItem(int id) throws PdsItemNotFoundException{
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			PdsItem pdsItem = PdsItemDAO.getInstance().selectByid(conn, id);
			
			if(pdsItem == null) {
				throw new PdsItemNotFoundException("존재하지 않습니다 :" + id);
			}
			return pdsItem;
		}catch(SQLException ex) {
			throw new RuntimeException("DB 처리 에러 발생:" + ex.getMessage(), ex);
		}finally {
			jdbcUtil.close(conn);
		}
		
		
	}
}
