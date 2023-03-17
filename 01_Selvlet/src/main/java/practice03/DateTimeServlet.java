package practice03;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/datetime") // 이름을 안 바꿔줘도 여기서 설정 가능하다.

public class DateTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 파라미터
		String type = request.getParameter("type");
		
		String result = null;
		switch(type) {
		case "1" : // 현재 날짜 요청
			result = LocalDate.now().toString();
			break;
		case "2" : // 현재 시간 요청
			result = LocalTime.now().toString();
			result = result.substring(0, result.indexOf("."));
			break;
		}
		
		// 응답 타입 및 인코딩
		response.setContentType("text/html; charset=UTF-8");
		
		// 출력 스트림 생성
		PrintWriter out = response.getWriter();
		
		// 응답 만들기
		out.print("<script>");
		out.print("alert('요청 결과는 " + result + " 입니다.')");
		out.print("</script>");
		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}