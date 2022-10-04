package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//요청 파라미터로 명령어를 전달하는 방시의 ㅅ퍼 인터페이스
//이를 구현한 클래스는 requestPro를 구현해야함...
public interface CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}
