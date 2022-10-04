package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.Map;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import action.NullAction;

public class ControllerUsingURI2 extends HttpServlet{

	private Map commandMap = new HashMap();
	//명령어와 명령어 처리 클래스를 상으로 저장
	
	
	
	//명렁어와 처리클래스가 매핑되어있는 properties 파일을 읽어서 Map 객체인 commandMap 에 저장
	//commandHandlerURI.properties 에 저장되어있음
	public void init(ServletConfig config) throws ServletException{
		String props = config.getInitParameter("configFile2"); // web.xml에서 propertyconfig에 해당하는 init-param의 값을 읽어옴
		Properties pr = new Properties(); //명령어와 처리클래스의 매핑정보를 저장할 properties 객체 생성
		FileInputStream fis = null;
		
		try {
			String configFilePath = config.getServletContext().getRealPath(props);
			fis = new FileInputStream(configFilePath); //프로퍼티스 파일의 내용을 읽어옴 나중에 닫아줌
			pr.load(fis); //fis로 읽어서 pr 객체에 저장
			
		}catch(IOException ex) {
			throw new ServletException (ex);
			
		}finally {
			if(fis != null) try {fis.close();} catch(IOException ex) {}
		}
		
		Iterator keyIter = pr.keySet().iterator(); //프로퍼티스의 키로 구성된 이터레이터 생성
		
		while(keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String className = pr.getProperty(command); //프로퍼티는 맵으로 되어있음 command key에 해당하는 값을 가져옴
			
			try{
				Class commandClass = Class.forName(className);//해당 문자열을 클래스로 만든다.
				Object commandInstance = commandClass.newInstance();//해당 클래스의 객체를 생성
				commandMap.put(command, commandInstance);//Map객체인 commandMap에 객체 저장(프로퍼티스 값=키, 그에 해당하는 객체)
				
			}catch(ClassNotFoundException e) {
				throw new ServletException(e);
			}catch(InstantiationException e) {
				throw new ServletException (e);
			}catch(IllegalAccessException e) {
				throw new ServletException (e);
			}
		}
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		requestPro(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		requestPro(request, response);
	}
	
	
// 사용자의 요청을 분석해서 해당 작업을 처리 > 해당 서비스 실행
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String view = null;
		CommandAction com = null;
		
		try {
			String command = request.getRequestURI(); //프로젝트 + 파일경로 가져옴
			
			if(command.indexOf(request.getContextPath())== 0) {
				command = command.substring(request.getContextPath().length());
			}
			com = (CommandAction)commandMap.get(command);
			if(com == null) {
				com = new NullAction();
			}
			view = com.requestPro(request, response);
			
		}catch(Throwable e) {
			throw new ServletException (e);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
	}
	
	
	
	
}






