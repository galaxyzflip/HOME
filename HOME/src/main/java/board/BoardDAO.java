package board;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import logon.jdbcUtil;
import java.util.ArrayList;



public class BoardDAO {

	
		private static BoardDAO instance = new BoardDAO();
		
		public static BoardDAO getInstance() {
			return instance;
		}
		
		private BoardDAO() {
			
		}
		
		
		private Connection getConnection() throws Exception {
			String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
			return DriverManager.getConnection(jdbcDriver);
		}
		
		
		
		@SuppressWarnings("resource")
		public void insertArticle(BoardDTO article) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int num = article.getNum();
			int ref = article.getRef();
			int re_step = article.getRe_step();
			int re_level = article.getRe_level();
			int number = 0;
			
			String query = "";
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select max(num) from board");
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					number = rs.getInt(1) + 1;
				}else
					number = 1;
				
				if(num != 0) {
					query = "update board set re_step = re_step+1 where ref=? and re_step > ?";
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, ref);
					pstmt.setInt(2, re_step);
					pstmt.executeUpdate();
					re_step +=1;
					re_level +=1;
					
				}else {
					ref = number;
					re_step = 0;
					re_level = 0;
				}
				
				
				query = "insert into board(num, writer, email, subject, passwd, reg_date, "
						+ "ref, re_step, re_level, content, ip) "
						+ "values(board_num.nextval, ?,?,?,?,?,?,?,?,?,? )";
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, article.getWriter());
				pstmt.setString(2, article.getEmail());
				pstmt.setString(3, article.getSubject());
				pstmt.setString(4, article.getPasswd());
				pstmt.setTimestamp(5, article.getReg_date());
				pstmt.setInt(6, ref);
				pstmt.setInt(7, re_step);
				pstmt.setInt(8, re_level);
				pstmt.setString(9, article.getContent());
				pstmt.setString(10, article.getIp());
				
				pstmt.executeUpdate();
				
			}catch(Exception ex) {
				ex.printStackTrace();
				
			}finally {
				jdbcUtil.close(conn);
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
				
			}
			
			
		}// end
		
		
		
		public int getArticleCount() throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int x = 0;
			
			try {
				conn = getConnection();
				
				pstmt = conn.prepareStatement("select count(1) from board");
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					x = rs.getInt(1);
				}
			
			}catch(Exception ex) {
				ex.printStackTrace();
			
			}finally {
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
				jdbcUtil.close(conn);
			}
			
			
			
			return x;
		}
		
		public List<BoardDTO> getArticles(int start, int end){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<BoardDTO> articleList = null;
			BoardDTO article = null;
			
			String sql = "select num,writer,email,subject,passwd,reg_date,ref,re_step,re_level,content,ip,readcount, r  "
					+ " from (select num,writer,email,subject,passwd,reg_date,ref,re_step,re_level,content,ip,readcount,rownum r  "
					+ " from (select num,writer,email,subject,passwd,reg_date,ref,re_step,re_level,content,ip,readcount "
					+ " from board order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ?";
			
			
			try {
				
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					articleList = new ArrayList<BoardDTO>(end);
					do {
						article = new BoardDTO();
						article.setNum(rs.getInt("num"));
						article.setWriter(rs.getString("writer"));
						article.setSubject(rs.getString("subject"));
						article.setPasswd(rs.getString("passwd"));
						article.setReg_date(rs.getTimestamp("reg_date"));
						article.setReadcount(rs.getInt("readcount"));
						article.setRef(rs.getInt("ref"));
						article.setRe_step(rs.getInt("re_step"));
						article.setRe_level(rs.getInt("re_level"));
						article.setContent(rs.getString("content"));
						article.setIp(rs.getString("ip"));
						
						articleList.add(article);
						
						
					}while(rs.next());
					
				}
				
				
			}catch(Exception ex) {
				ex.printStackTrace();
			
			}finally {
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
				jdbcUtil.close(conn);
				
			}
			
			return articleList;
		}
		
		
		public BoardDTO getArticle(int num) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			BoardDTO article = null;
			
			try {
				
				conn = getConnection();
				pstmt = conn.prepareStatement("update board set readcount = readcount+1 where num = ?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement("select * from board where num = ?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					article = new BoardDTO();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setRe_step(rs.getInt("re_step"));
					article.setRe_level(rs.getInt("re_level"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					
				}
				
				
			}catch(Exception ex) {
				ex.printStackTrace();

			}finally {
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
				jdbcUtil.close(conn);
			}
			
			return article;
		}
		
		
		public BoardDTO updateGetArticle(int num) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BoardDTO article = null;
			
			try {
				conn= getConnection();
				pstmt = conn.prepareStatement("select * from board where num=?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					article = new BoardDTO();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setRe_step(rs.getInt("re_step"));
					article.setRe_level(rs.getInt("re_level"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					
				}
				
			}catch(Exception ex) {
				ex.printStackTrace();
				
			}finally{
				jdbcUtil.close(conn);
				jdbcUtil.close(pstmt);
				jdbcUtil.close(rs);
			}
			
			return article;
		}
		
		
		public int updateArticle(BoardDTO article) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String dbPasswd="";
			String sql = "";
			int x = -1;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select passwd from board where num=?");
				pstmt.setInt(1, article.getNum());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dbPasswd = rs.getString("passwd");
					pstmt = conn.prepareStatement("update baord set writer=?, email=?, subject=, passwd=?, content = ? where num=?");
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getEmail());
					pstmt.setString(3, article.getSubject());
					pstmt.setString(4, article.getPasswd());
					pstmt.setString(5, article.getContent());
					pstmt.setInt(6, article.getNum());
					
					pstmt.executeUpdate();
					x = 1;
					
				}else
					x = 0;
				
				
			}catch(Exception ex) {
				ex.printStackTrace();
				
			}finally {
				jdbcUtil.close(conn);
				jdbcUtil.close(pstmt);
				jdbcUtil.close(rs);
			}
			
			return x;
		}
		
		
		public int deleteArticle(int num, String passwd) {
			int x = -1;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String dbPasswd = "";
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select passwd from board where num=?");
				pstmt.setInt(1, num);
				
				if(rs.next()) {
					dbPasswd = rs.getString("passwd");
					if(dbPasswd.equals(passwd)) {
						pstmt = conn.prepareStatement("delete from board where num = ?");
						pstmt.setInt(1, num);
						pstmt.executeUpdate();
						x = 1; 
					}
					
				}else
					x = 0;
				
			}catch(Exception ex) {
				ex.printStackTrace();
				
			}finally {
				jdbcUtil.close(conn);
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
			}

			return x;
		}
		
		
}






















