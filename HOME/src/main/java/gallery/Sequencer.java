package gallery;
import java.sql.*;

import logon.jdbcUtil;
import oracle.jdbc.proxy.annotation.Pre;

public class Sequencer {

	public synchronized static int nextId(Connection conn, String tableName) throws SQLException{
		
		PreparedStatement pstmtSelect = null;
		ResultSet rsSelect = null;
		
		PreparedStatement pstmtUpdate = null;
		
		try {
			
			pstmtSelect = conn.prepareStatement("select message_id from id_sequences where table_name=?");
			pstmtSelect.setString(1, tableName);
			
			rsSelect = pstmtSelect.executeQuery();
			
			if(rsSelect.next()) {
				int id = rsSelect.getInt(1);
				id++;
				
				pstmtUpdate = conn.prepareStatement("update id_sequences set message_id = ? where table_name=?");
				pstmtUpdate.setInt(1, id);
				pstmtUpdate.setString(2, tableName);
				pstmtUpdate.executeUpdate();
				
				return id;
			}else {
				pstmtUpdate = conn.prepareStatement("insert into id_sequences(table_name, message_id) values(? ,?)");
				pstmtUpdate.setString(1, tableName);
				pstmtUpdate.setInt(2, 1);
				pstmtUpdate.executeUpdate();
				
				return 1;
				
			}
			
			
			
		}finally {
			jdbcUtil.close(pstmtSelect);
			jdbcUtil.close(pstmtUpdate);
			jdbcUtil.close(rsSelect);
		}
		
	}
}
