package pds.service;

import java.sql.*;
import messagebook.ConnectionProvider;
import pds.dao.PdsItemDAO;

public class DeletePdsItemService {

	private static DeletePdsItemService instance = new DeletePdsItemService();
	private DeletePdsItemService() {}
	
	public static DeletePdsItemService getInstance() {
		return instance;
	}
	
	
	public int deletePdsItem(int pdsId) {
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			int check = PdsItemDAO.getInstance().delete(conn, pdsId);
					
					
					
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			
		}
		
		
		
		return 0;
	}
	
	
	
	
}
