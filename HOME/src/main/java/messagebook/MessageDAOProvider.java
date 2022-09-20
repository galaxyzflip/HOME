package messagebook;

//import OracleMessageDAO; // 동일 패키지 



// 사용하는 dbms에 맞는 (web.xml에 설정에 따른) //초기화서블렛
public class MessageDAOProvider {
	
	private static MessageDAOProvider instance = new MessageDAOProvider();
	
	public static MessageDAOProvider getInstance() {
		return instance;
	}
	
	private MessageDAOProvider() {}
	
	private OracleMessageDAO oracleDao = new OracleMessageDAO();
	//private MySQLMessageDAO mysqlDAO = new MySQLMessageDAO();
	
	private String dbms;
	
	void setDbms(String dbms) {
		this.dbms= dbms;
	}
	
	//MessageDAOProvider,getInstance(),getMessageDAO()
	public MessageDAO getMessageDao() {
		if("oracle".equals(dbms)) {
			return oracleDao;
		}
		return null;
	}
	

}
