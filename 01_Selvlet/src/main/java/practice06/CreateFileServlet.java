package practice06;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateFileServlet")

public class CreateFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// IOException은 여기서 처리해준다 따로 try catch문을 안해주는 이유다
		
		/*
		  1. 파일 만들기
		  	1) 파일명 : yyyy-mm-dd-작성자-제목.txt
		  	2) 파일경로 : RealPath에 storage 디렉터리를 만들어서 저장
		  	3) 파일내용 : 내용 그대로 추가	
		*/
		
		/*
		  2. FileResponseServlet으로 리다이렉트
		     파일명 전달
		 */
		File dir = new File("C:" + File.separator + "storage");  
		
		if(dir.exists() == false) {
			dir.mkdirs();
		} else {
			dir.delete(); 
		}
		
		
		request.setCharacterEncoding("UTF-8");
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		response.setContentType("text/html; charset=UTF-8");
		response.sendRedirect("/01_Servlet/FileResponseServlet?title=" + title);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
