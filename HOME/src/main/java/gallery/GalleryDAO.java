package gallery;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import logon.jdbcUtil;
import messagebook.ConnectionProvider;



public class GalleryDAO {

	private static GalleryDAO instance = new GalleryDAO();
	private GalleryDAO() {}
	public static GalleryDAO getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception{
		
		return ConnectionProvider.getConnection();
	}

	
	public void insert(Theme theme) throws Exception{
		Connection conn = null;
		Statement stmtGroup = null; // 새로운 글의 그룹 번호를 구할때 사용
		ResultSet rsGroup = null;
		
		
		//특정 글의 답글에 대한 출력 순서를 구할때 사용
		PreparedStatement pstmtOrder = null;
		ResultSet rsOrder = null;
		PreparedStatement pstmtOrderUpdate= null;
		
		//글을 삽입할때 사용
		PreparedStatement pstmtInsertMessage = null;
		PreparedStatement pstmtInsertContent = null;
		
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			if(theme.getParentId() == 0) {
				//답글이 아닌 경우 그룹번호를 새롭게 구한다.
				stmtGroup = conn.createStatement();
				rsGroup = stmtGroup.executeQuery("select max(group_id) from theme_message");
				int maxGroupId = 0;
				if(rsGroup.next()) {
					maxGroupId = rsGroup.getInt(1);
				}
				maxGroupId++;
				
				theme.setGroupId(maxGroupId);
				theme.setOrderNo(0);
				
			}else {
				//특정 글의 답글인 경우
				//같은 그룹 번호 내에서의 출력 순서를 구한다.
				
				pstmtOrder = conn.prepareStatement("select max(order_no) from theme_message where parent_id = ? or theme_message_id = ?");
				pstmtOrder.setInt(1, theme.getParentId());
				pstmtOrder.setInt(2, theme.getParentId());
				rsOrder = pstmtOrder.executeQuery();
				int maxOrder = 0;
				
				if(rsOrder.next()) {
					maxOrder = rsOrder.getInt(1);
				}
				maxOrder++;
				theme.setOrderNo(maxOrder);
			}
			
				//특정 글의 다변 글인 경우 같은 그룹 내에서 순서 번호를 변경한다.
			
			if(theme.getOrderNo() > 0) {
				pstmtOrderUpdate = conn.prepareStatement("update theme_message set order_no = order_no + 1 where group_id = ? and order_no >= ?");
				pstmtOrderUpdate.setInt(1, theme.getGroupId());
				pstmtOrderUpdate.setInt(2, theme.getOrderNo());
				pstmtOrderUpdate.executeUpdate();
			}
			
			//새로운 글의 번호를 구하낟.
			theme.setId(Sequencer.nextId(conn, "THEME_MESSAGE"));
			
			//글을 삽입한다.
			pstmtInsertMessage = conn.prepareStatement("insert into theme_message values(?,?,?,?,?,?,?,?,?,?,?)");
			pstmtInsertMessage.setInt(1, theme.getId());
			pstmtInsertMessage.setInt(2, theme.getGroupId());
			pstmtInsertMessage.setInt(3, theme.getOrderNo());
			pstmtInsertMessage.setInt(4, theme.getLevels());
			pstmtInsertMessage.setInt(5, theme.getParentId());
			pstmtInsertMessage.setTimestamp(6, theme.getRegister());
			pstmtInsertMessage.setString(7, theme.getName());
			pstmtInsertMessage.setString(8, theme.getEmail());
			pstmtInsertMessage.setString(9, theme.getImage());
			pstmtInsertMessage.setString(10, theme.getPassword());
			pstmtInsertMessage.setString(11, theme.getTitle());
			pstmtInsertMessage.executeUpdate();
			
			pstmtInsertContent = conn.prepareStatement("insert into theme_content(THEME_MESSAGE_ID, CONTENT) values(?, ?)");
			pstmtInsertContent.setInt(1, theme.getId());
			pstmtInsertContent.setCharacterStream(2, new StringReader(theme.getContent()), theme.getContent().length());
			pstmtInsertContent.executeUpdate();
			
			conn.commit();
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException ex1) {}
			
		}finally {
			jdbcUtil.close(pstmtInsertContent);
			jdbcUtil.close(stmtGroup);
			jdbcUtil.close(rsGroup);
			jdbcUtil.close(pstmtOrder);
			jdbcUtil.close(rsOrder);
			jdbcUtil.close(pstmtOrderUpdate);
			jdbcUtil.close(pstmtInsertMessage);
			jdbcUtil.close(pstmtInsertContent);
			if(conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				}catch(SQLException ex) {}
			}
			
		}
	}

	
	public void update(Theme theme) throws Exception{
		Connection conn = null;
		PreparedStatement pstmtUpdateMessage = null;
		PreparedStatement pstmtUpdateContent = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			pstmtUpdateMessage = conn.prepareStatement("update theme_message set name = ?, email = ?, title=?, image = ? where theme_message_id = ?");
			pstmtUpdateMessage.setString(1, theme.getName());
			pstmtUpdateMessage.setString(2, theme.getEmail());
			pstmtUpdateMessage.setString(3, theme.getTitle());
			pstmtUpdateMessage.setString(3, theme.getImage());
			pstmtUpdateMessage.setInt(4, theme.getId());
			pstmtUpdateMessage.executeUpdate();
			
			pstmtUpdateContent = conn.prepareStatement("update theme_content set content = ? where theme_messageId = ?");
			pstmtUpdateContent.setCharacterStream(1, new StringReader(theme.getContent()), theme.getContent().length());
			pstmtUpdateContent.executeUpdate();
			
			conn.commit();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException ex1) {}
		}finally {
			jdbcUtil.close(pstmtUpdateContent);
			jdbcUtil.close(pstmtUpdateMessage);
			if(conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				}catch(SQLException ex) {}
			}
		}
		
	}
	
	//등록된 글의 개수를 구한다.
	
	public int count(List whereCond, Map valueMap) throws Exception{
		
		if(valueMap == null) {
			valueMap = Collections.EMPTY_MAP;
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			StringBuffer query  = new StringBuffer(200);
			query.append("select count(*) from theme_message ");
			
			if(whereCond != null && whereCond.size() > 0) {
				query.append("where ");
				
				for(int i=0;i<whereCond.size(); i++) {
					query.append(whereCond.get(i));
					if(i < whereCond.size() -1 ) {
						query.append(" or ");
					}
				}
			}
			
			pstmt = conn.prepareStatement(query.toString());
			
			Iterator keyIter = valueMap.keySet().iterator();
			
			while(keyIter.hasNext()) {
				Integer key = (Integer)keyIter.next();
				Object obj = valueMap.get(key);
				
				if(obj instanceof String) {
					pstmt.setString(key.intValue(), (String)obj);
				}else if(obj instanceof Integer) {
					pstmt.setInt(key.intValue(), ((Integer)obj).intValue());
				}else if(obj instanceof Timestamp) {
					pstmt.setTimestamp(key.intValue(), (Timestamp)obj);
				}
			}
			
			rs = pstmt.executeQuery();
			int count = 0;
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			return count;
			
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new Exception("count", ex);
		}finally {
			jdbcUtil.close(conn);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
		}
	}

	
	//a목록을 읽어온다.
	
	public List selectList(List whereCond, Map valueMap, int startRow, int endRow) throws Exception{
		
		if(valueMap == null) {
			valueMap = Collections.EMPTY_MAP;
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		try {
			StringBuffer sql = new StringBuffer(200);
			
			sql.append(" select * from (select theme_message_id, group_id, order_no, levels, "
					+ " parent_id, register, name, email,image,password, title, rownum rnum "
					+ " from(select theme_message_id, group_id, order_no, levels, parent_id, register, "
					+ " name, email, image,password, title from theme_message ");
			if(whereCond != null && whereCond.size() > 0) {
				sql.append(" where ");
				for(int i=0; i< whereCond.size();i++) {
					sql.append(whereCond.get(i));
					if(i < whereCond.size() - 1) {
						sql.append(" or ");
					}
				}
			}
			sql.append(" order by group_id desc, order_no asc )where rownum <= ? ) where rnum >= ? ");
			
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			
			Iterator keyIter = valueMap.keySet().iterator();
			while(keyIter.hasNext()) {
				Integer key = (Integer)keyIter.next();
				Object obj = valueMap.get(key);
				
				if(obj instanceof String) {
					pstmt.setString(key.intValue(), (String)obj);
					
				}else if(obj instanceof Integer) {
					pstmt.setInt(key.intValue(), ((Integer)obj).intValue());
				
				}else if(obj instanceof Timestamp) {
					pstmt.setTimestamp(key.intValue(), (Timestamp)obj);
				}
			}
			
			
			pstmt.setInt(valueMap.size() + 1, endRow + 1);
			pstmt.setInt(valueMap.size() + 2, startRow + 1);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				List list = new ArrayList(endRow - startRow + 1);
				
				do {
					Theme theme = readRs(rs);
					list.add(theme);
				}while(rs.next());
				
				return list;
			}else {
				return Collections.EMPTY_LIST;
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new Exception("selectList", ex);
		}finally {
			jdbcUtil.close(conn);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(rs);
		}
		
	}
	
	public Theme readRs(ResultSet rs) throws SQLException {
		Theme theme = new Theme();
		theme.setId(rs.getInt("theme_message_id"));
		theme.setGroupId(rs.getInt("group_id"));
		theme.setOrderNo(rs.getInt("order_no"));
		theme.setLevels(rs.getInt("levels"));
		theme.setParentId(rs.getInt("parentid"));
		theme.setRegister(rs.getTimestamp("register"));
		theme.setName(rs.getString("name"));
		theme.setEmail(rs.getString("email"));
		theme.setImage(rs.getString("image"));
		theme.setPassword(rs.getString("password"));
		theme.setTitle(rs.getString("title"));
		
		return theme;
	}
	
	public Theme select(int id) throws Exception{
		Connection conn = null;
		
		PreparedStatement pstmtMessage = null;
		ResultSet rsMessage = null;
		
		PreparedStatement pstmtContent = null;
		ResultSet rsContent = null;
		
		try {
			Theme theme = null;
			
			conn = getConnection();
			pstmtMessage = conn.prepareStatement("select * from theme_message where theme_message_id = ?");
			pstmtMessage.setInt(1, id);
			rsMessage = pstmtMessage.executeQuery();
			
			if(rsMessage.next()) {
				theme = readRs(rsMessage);
				
				pstmtContent = conn.prepareStatement("select content from theme_content where theme_message_id = ?");
				pstmtContent.setInt(1, id);
				
				rsContent= pstmtContent.executeQuery();
				
				if(rsContent.next()) {
					Reader reader = null;
					try {
						reader = rsContent.getCharacterStream("content");
						char[] buff = new char[512];
						int len = -1;
						StringBuffer buffer = new StringBuffer(512);
						while((len = reader.read(buff)) != -1){
							buffer.append(buff, 0, len);
						}
						theme.setContent(buffer.toString());
						
						
					}catch(IOException iex) {
						throw new Exception("select", iex);
						
					}finally {
						if(reader != null) {
							try {
								reader.close();
							}catch(IOException iex) {}
						}
					}
				} else {
					return null;
				}
				return theme;
			}else {
				return null;
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new Exception("select", ex);
		}finally {
			jdbcUtil.close(conn);
			jdbcUtil.close(pstmtContent);
			jdbcUtil.close(pstmtMessage);
			jdbcUtil.close(rsContent);
			jdbcUtil.close(rsMessage);
		}
		
		
	}
	
	public void delete(int id) throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmtMessage = null;
		PreparedStatement pstmtContent = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			pstmtMessage = conn.prepareStatement("delete from theme_message where theme_message_id = ?");
			pstmtMessage.setInt(1, id);
			int updateCount1 = pstmtMessage.executeUpdate();
			
			pstmtContent = conn.prepareStatement("delete from theme_content where theme_message_id = ?");
			pstmtContent.setInt(1, id);
			int updateCount2 = pstmtContent.executeUpdate();
			
			if(updateCount1 + updateCount2 == 2) {
				conn.commit();
			}else {
				conn.rollback();
				throw new Exception("(delete)invalid id:" + id);
				
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException ex1) {}
			throw new Exception("delete", ex);
		}finally {
			jdbcUtil.close(pstmtContent);
			jdbcUtil.close(pstmtMessage);
			if(conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				}catch(SQLException ex) {}
			}
				
		}
		
	}
	
}












