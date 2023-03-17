package ex06_forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ForwardServlet1")

public class ForwardServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 포워드 이전(첫 번째 요청) 파라미터 확인
		// 넘어온 파라미터값을 한번 낚아채고 정삭적으로 넘어 왔는지 시스템 출력으로 확인 한번 하고
		// 내부에서 처리하기 위해 포워드로 처리하기 위해 다른 Servlet에다가 보내주기 위한 코드를 작성
		String model = request.getParameter("model");
		System.out.println("ForwardServlet1 : " + model);
		
		// 포워드(전달), 포워드는 리퀘스트가 한다 리다이렉트랑 달리 
		request.getRequestDispatcher("/ForwardServlet2").forward(request, response); // urlmapping만 적고.뒤에 메소드를 적는데 request랑 response모두 그대로 전달하겠다는 의미이다.
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
