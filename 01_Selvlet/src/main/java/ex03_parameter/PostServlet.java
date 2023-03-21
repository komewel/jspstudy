package ex03_parameter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PostServlet")

public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String model = request.getParameter("model");
		String strPrice = request.getParameter("price");
		
		// <form> 태그에 포함된 입력 요소들이 name 속성을 가지고 있다면, null 처리를 할 수 없다. 빈 문자열("")로 처리해야 한다. 
		
		int price = 0;
		if(strPrice == null || strPrice.isEmpty() == false){ // 빈 문자열 점검 전용메소드인 isEmpty()가 있다, 혹시 null이 올수도 있으니 조건을 저렇게 주면 완벽하다.
			price = Integer.parseInt(strPrice);
		}
		// 보냈을때 name 속성이 있으면 null이 절대 도착할 수 없는데 값을 따로 입력 안하고 보내면 null보다는 빈문자열로 온다 고로 인식을 제대로 못해주므로 코드를 수정해야한다.
		
		response.getWriter().append("model: " + model).append(",price :" + price);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST 요청이 들어옴");
		doGet(request, response); //POST한테 요청이 들어와도 어짜피 doGet이 일을한다.
	}
	//

}
