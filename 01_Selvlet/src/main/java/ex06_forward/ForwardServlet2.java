package ex06_forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ForwardServlet2")

public class ForwardServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 포워드 이후(두 번째 요청) 파라미터 확인
		String model = request.getParameter("model");
		System.out.println("ForwardServlet2 : " + model);
		// forward는 client에서 실행하면 주소창에 변화가 없다 결과값은 나오지만 이유는 서버내에서 처리되는거라 가시적으로는 우리한테 안보인다, 고로 우리는 값만 주고 주소창으로 분석은 불가하다 결과값으로 확인 해볼뿐, 제대로 작성안되면 결과도 안나온다 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
