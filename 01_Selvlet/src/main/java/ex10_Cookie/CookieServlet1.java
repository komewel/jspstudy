package ex10_Cookie;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieServlet1")

public class CookieServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 	Cookie
		 	
		 	1. 서버가 만들어서 클라이언트가 저장한다.
		 	2. 보안에 취약하다.
		 	3. 경로 설정에 따라 볼수있는 쿠키가 달라진다(정확히는 서블렛 파일마다 저장된 쿠키가 다르다 서로 열수있는 브라우저 창이 다르니까)
		 	4. 데이터 저장소로서 클라이언트(브라우저)한테 저장되는것이다 
		 */
		
		// 쿠키 만들기
		Cookie cookie1 = new Cookie("name", "민경태"); // 쿠키값과 쿠키이름 모두 string으로 주게 되어 있다.
		Cookie cookie2 = new Cookie("address", URLEncoder.encode("서울시 금천구 가산동", "UTF-8"));
		// 유효하지 않은 문자(대표적으로 공백) [32] 가 있다 여기서 [32]는 띄어쓰기이다, UTF-8로 인코딩해서 저장한다.
		Cookie cookie3 = new Cookie("job", URLEncoder.encode("요양보호사", "UTF-8"));
		
		// 쿠키가 저장될 경로 설정하기 (생략하면 컨텍스트 패스에 저장된다)
		cookie1.setPath("/01_Servlet"); 				// 컨텍스트 패스 (/01_Servlet), 파일이름 컨텍스트 패스 : request.getcontextpath()
		cookie2.setPath("/01_Servlet/CookieServlet1");  // 전체경로(서블릿 경로)  (/01_Servlet/CookieServlet1)
														// cookie3은 경로 설정을 생략했으므로 컨텍스트패스에 저장된다.
		
		// 쿠키 유효시간 설정하기 (생략하면 세션쿠키가 된다. : 브라우저를 닫을때 까지 보관된다.)
		cookie1.setMaxAge(60 * 60 * 24 * 7); // 단위는 초이다, 7일단 보관되는 쿠키
		cookie2.setMaxAge(60 * 60);  		 // 1시간 동안 보관되는 쿠키, 삭제하고 싶으면 유효시간은 0으로 주면 삭제하겠다는 뜻
		
		//쿠키 저장하기(응답으로 처리해야 한다.)
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3); // 따로 저장 될 경로 설정을 안해서 컨텍스트패스에 저장됨

		// CookieServlet2으로 redirect 이동
		response.sendRedirect("/01_Servlet/CookieServlet2");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
