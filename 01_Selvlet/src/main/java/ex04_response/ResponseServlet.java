package ex04_response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ResponseServlet")

public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 파라미터 처리하기
		request.setCharacterEncoding("UTF-8");
		
		String model = request.getParameter("model");
		String strPrice = request.getParameter("price");
		
		int price = 0;
		if(strPrice != null && strPrice.isEmpty() == false) {
			price = Integer.parseInt(strPrice);
		}
		
		/*
		 	응답 
		 	
		 	1. 서버 -> 클라이언트로 보내는 것이 응답(Response)이다
		 	2. HttpServletResponse 클래스가 응답을 처리한다.
		 	3. 어떤 MIME(html, json... 등등 응답을 뭘로 해야하는지) 타입으로 응답 할 것인지 결정해야한다. 반환타입이랑 비슷함
		 		1) HTML : text/html			(태그를 만들어서 반환하는 경우)
		 		2) XML 	: application/xml   (ajax 응답이 XML인 경우)
		 		3) JSON : application/json	(ajax 응답이 JSON인 경우)
		 */
		
		// 응답 만들기
	
		// 1. 응답할 데이터의 MIME 타입과 UTF-8 인코딩
		response.setContentType("text/html; charset=UTF-8"); // 너가 보낼려는 MIME타입이 뭐냐 MIME타입과 UTF-8도 같이 하는법이다.
	
		// 2. 응답 스트림 생성(IOException 처리가 필요하다 -> 이미 처리되어 있다.) 오류가 안나는 이유가 이미 처리되어 있어서 그렇다 위에서 doGet 메소드에서 이미 처리해서 던지고 있다. try catch를 따로 안해줘도 된다 이미 처리가 되어 있으므로 
		PrintWriter out = response.getWriter(); // PrintWriter의 응답 메소드 : append(), write(), print(), println() 등 이런 출력메소드를 통해 출력 할 수 있다.
		
		// 3. 응답 만들기
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"ko\">");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>나의 첫 응답</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>모델명: " + model + "</h1>");
		out.println("<h1>가격: " + price + "</h1>");
		out.println("</body>");
		out.println("</html>");
		out.flush();  // (혹시) 출력 스트림에 남아 있는 데이터를 모두 내보내기
		out.close();  // 출력 스트림에서는 close를 꼭해줘야 한다 , 위코드처럼 힘든 코드를 안쓸려고 만들어진 jsp는 html에서 자바를 실행이 가능하게 해준다.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
