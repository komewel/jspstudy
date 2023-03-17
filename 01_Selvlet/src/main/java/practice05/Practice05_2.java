package practice05;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthToggleButtonUI;

@WebServlet("/Practice05_2")

public class Practice05_2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 냉장고 sysout 으로 출력
		request.setCharacterEncoding("UTF-8");
		String model = request.getParameter("model");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("Practice05_2 : " + model);
		System.out.println(request.getServletContext().getRealPath("practice05")); // 이폴더의 실제경로를 알려달라, 실제경로는 다른곳에 있다 이것으로 알 수 있다.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
