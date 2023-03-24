package ex10_Cookie;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieServlet2")

public class CookieServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTf-8");
		
		// 전체 쿠키 가져오기, 확인 할 수 있는 모든 쿠키를 다 가져온다.
		Cookie[] cookies = request.getCookies();
		
		// 전체 쿠키 확인하기
		if(cookies != null) {
			for(int i = 0; i < cookies.length; i++){
				System.out.println("쿠키이름 : " + cookies[i].getName() + ", 쿠키값 : " + URLDecoder.decode(cookies[i].getValue(), "UTF-8"));
			}
		}
		
		// 쿠키 삭제하기 (Max-Age가 0인 쿠키로 덮어쓰기)
		Cookie cookie1 = new Cookie("name", "");
		Cookie cookie2 = new Cookie("job", "");
		cookie1.setMaxAge(0);
		cookie2.setMaxAge(0);
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		// CookieServlet3으로 redirect
		response.sendRedirect("/01_Servlet/CookieServlet3");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
