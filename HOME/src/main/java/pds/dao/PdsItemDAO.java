package pds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logon.jdbcUtil;
import messagebook.ConnectionProvider;
import pds.model.PdsItem;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;


public class PdsItemDAO {

	private PdsItemDAO () {}
	private static PdsItemDAO instance = new PdsItemDAO();
	public static PdsItemDAO getInstance() {
		return instance;
	}
	
	public int selectCount(Connection conn){
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(1) from pds_item");
			rs.next();
			count =  rs.getInt(1);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(stmt);
		}
		return count;
	}
	
	
	public int insert(Connection conn, PdsItem item) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = conn.prepareStatement("insert into pds_item(pds_item_id, filename, realpath, filesize, downloadcount, description) values(pds_item_id_seq.nextval, ?,?,?,0,?)");
			pstmt.setString(1, item.getFileName());
			pstmt.setString(2, item.getRealPath());
			pstmt.setLong(3, item.getFileSize());
			pstmt.setString(4, item.getDescription());
			
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select pds_item_id_seq.CURRVAL from dual");
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
			return -1;
			
		}finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(stmt);
			jdbcUtil.close(pstmt);
		}
		
	}
	
	
	public int update(Connection conn, PdsItem item, int pdsId) {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		int check = 0;
		
		try {
			
			pstmt = conn.prepareStatement("update pds_item set filename=?, realpath=?, fileSize=?, "
					+ " downloadcount = 0, description= ? where pds_item_id = ?");
			// update 로 수정해야함
			
			pstmt.setString(1, item.getFileName());
			pstmt.setString(2, item.getRealPath());
			pstmt.setLong(3, item.getFileSize());
			pstmt.setString(4, item.getDescription());
			pstmt.setInt(5, pdsId);
			
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				check =  1;
			} else {
				check = -1;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(stmt);
			jdbcUtil.close(pstmt);
		}
		return check;
	}
	
	public PdsItem selectByid(Connection conn, int itemId) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from pds_item where pds_item_id = ?");
			pstmt.setInt(1, itemId);
			rs = pstmt.executeQuery();
			if(!rs.next()){
				return null;
			}
			PdsItem item = makeItemFromResultSet(rs);
			return item;
			
		}finally {
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
		}

	}

	private PdsItem makeItemFromResultSet(ResultSet rs) throws SQLException{

		PdsItem item = new PdsItem();
		item.setId(rs.getInt("pds_item_id"));
		item.setFileName(rs.getString("filename"));
		item.setFileSize(rs.getLong("filesize"));
		item.setRealPath(rs.getString("realpath"));
		item.setDownloadCount(rs.getInt("downloadcount"));
		item.setDescription(rs.getString("description"));
		return item;

	}
	
	public int increaseCount(Connection conn, int id) throws SQLException{
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("update pds_item set downloadcount = downloadcount + 1 where pds_item_id = ?");
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
			
		}finally {
			jdbcUtil.close(pstmt);
		}
		
	}
	
	public List<PdsItem> select(Connection conn, int firstRow, int endrow, String searchKeyword) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		
		try {
			
			if(searchKeyword == null || searchKeyword.isBlank()) {
				sql = "select * from (select rownum rnum, pds_item_id, filename, realpath, filesize, downloadcount, description "
						+ "	from (select * from pds_item m  order by m.pds_item_id desc) where rownum <= ?) "
						+ "	where rnum >=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, endrow);
				pstmt.setInt(2, firstRow);
				
			} else {
				sql = "select * from (select rownum rnum, pds_item_id, filename, realpath, filesize, downloadcount, description "
						+ "	from (select * from pds_item m where filename like '%'||?||'%' order by m.pds_item_id desc) where rownum <= ?) "
						+ "	where rnum >=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,  searchKeyword);
				pstmt.setInt(2, endrow);
				pstmt.setInt(3, firstRow);
			}
			
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return Collections.emptyList();
			}
			
			List<PdsItem> itemLists = new ArrayList<>();
			do {
				PdsItem item = makeItemFromResultSet(rs);
				itemLists.add(item);
			}while(rs.next());
			return itemLists;
			
		
		}finally {
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
		}
		
	}

	
	
	
	
	
	
	
	
	
	
}
