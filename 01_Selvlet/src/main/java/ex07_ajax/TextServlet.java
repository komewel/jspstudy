package ex07_ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TextServlet")

public class TextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("UTF-8");
			String model = request.getParameter("model");
			String strPrice = request.getParameter("price");
			int price = 0;
			if(strPrice != null && strPrice.isEmpty() == false) {
				price = Integer.parseInt(strPrice);
			// 가격을 100,000,000 이런식으로 요청을 했을때 NumberFormatException가 발생한다.
			}
			// 응답 데이터 타입
			response.setContentType("text/plain; charset=UTF-8"); //text/plain은 datatype: text이랑 같은 말로 보면 된다.
			
			// 출력 스트림 생성
			PrintWriter out = response.getWriter();
			
			// 출력
			if(price < 0) {
				throw new RuntimeException(price + "원은 입력이 불가능한 금액입니다."); // 이걸로 예외를 던지면 정말 편리하다
			}else {
				String resData = "모델은 " + model + "이고, 가격은 " + price + "원입니다."; 	
				out.println(resData);														
				out.flush();
				out.close();
			}
			// 자바에서 잡지 못하는 예외는 개발자가 직접 코드를 짜서 잡아야 한다.
			}catch(NumberFormatException e) {
				// 예외 상황에 따른 응답 만들기
				// 응답코드 : 600 (임의로 작성)
				// 응답 메시지 : 가격을 확인하세요
				
				// 응답메시지 타입
				response.setContentType("text/plain; charset=UTF-8");
				
				// 응답코드(임의로)
				response.setStatus(600);
				
				// 응답메시지
				PrintWriter out = response.getWriter();
				out.println("가격을 확인하세요");
				out.flush();
				out.close();
			}catch(RuntimeException e) {
				// 예외 상황에 따른 응답 만들기
				// 응답코드 : 601 (임의로 작성)
				// 응답 메시지 : 예외 객체 e에 저장된 message 필드 값
				
				response.setStatus(601);
				response.setContentType("text/plain; charset=UTF-8"); 
				PrintWriter out = response.getWriter();
				out.println(e.getMessage());
				out.flush();
				out.close();
			}
		
		
	} 
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
