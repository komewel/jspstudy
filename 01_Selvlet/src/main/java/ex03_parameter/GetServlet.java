package ex03_parameter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetServlet")

public class GetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 요청 
		 
		 1. 클라이언트 -> 서버로 보내는 것이 요청(Request)이다.
		 2. HttpServletRequest 클래스가 요청을 처리한다.
		 3. 요청에 포함된 파라미터(Parameter)는 String 타입으로 처리한다.
		*/
		
		// 요청 정보를 UTF-8로 인코딩한다.
 		request.setCharacterEncoding("UTF-8"); 
		
		// 요청 파라미터를 꺼낸다
		String model = request.getParameter("model");
		String strPrice = request.getParameter("price");
		
		// 요청 파라미터에 null 처리를 한다.
		int Price = 0;
		if(strPrice != null) {  // null이 아닌 경우에만 string으로 받은걸 int 처리해주는 과정이 필요하다.
			Price = Integer.parseInt(strPrice);  // "200"-> 200
		}
		
		System.out.println(model);
		
		response.getWriter().append("model: " + model).append(", price: " + Price);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
