package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IMemberService {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;  // 반환 할 필요가 없다 컨트롤러에 어디로갈지 알려줄 필요가 없다, 예외처리는 throws 처리한다
																									 // 응답이 끝나면 이동할 필요도 없기 때문에 redirect forward 같은건 더 이상 필요없다 응답이 끝났기 때문에
}																									 // HttpServletResponse response를 통해 모든 응답이 처리된다.
