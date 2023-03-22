package ex07_ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/*
  Dynamic web project에서 외부 라이브러리(*.jar)를 사용하는 방법
  
  방법1. CATALINA_HOME\lib 디렉터리에 사용할 라이브러리를 추가한다.
  
  방법2. 컨텍스트(프로젝트)에 사용할 라이브러리를 추가한다.(수업에서 사용할 방법)
  		 src/main/webapp/WEB-INF/lib 디렉터리에 외부 라이브러리 추가 
  		 커서로 끌어다가 집어 넣어서 copy files 하면된다.
  		 이클립스 lib 파일에다가 jar로 넣어도되고 
  		 직접 톰캣 lib파일에다가 추가해도 되고 웬만하면 이클립스 lib에다가 넣는게 편하다.
  		
  
  
  
 */

@WebServlet("/JSONServlet")

public class JSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 파라미터
		String name = request.getParameter("name");
		String strAge = request.getParameter("age");
		try {
		// 요청 인코딩
		request.setCharacterEncoding("UTF-8");
		int age = 0;
		if(strAge != null && strAge.isEmpty() == false) {
			age = Integer.parseInt(strAge);
		}
		
		// 응답할 JSON 데이터
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("age", age);
		
		//System.out.println(obj.toString());//{"name" : "마돈나", "age" : 50}
		
		// 응답 데이터 타입
		response.setContentType("application/json; charset=UTF-8");
		
		// 출력 스트림 생성 
		PrintWriter out = response.getWriter();
		
		// 출력
		if(age > 100 || age < 0) {
			throw new RuntimeException(age + "살은 잘못된 나이입니다.");
		}else {
			
		}
		if(name.length() > 6 || name.length() < 2) {
			throw new RuntimeException(name + "은 잘못된 이름입니다.");
		}else {
			
		}
		out.println(obj.toString()); // 텍스트 형식으로 된 JSON 데이터를 응답한다, 실제로 응답이 이루어질 때 문자열을 뜻하는 텍스트로 이루어졌지만 
		out.flush();				 // 텍스트로 이루어졌지만, 반환타입이 json이라고 명시되면 json화(''가 제거 되면서 객체의 역할을 수행한다)를 시켜서 동작한다
		out.close();				 // 오브젝트를 그냥 사용하면 라이브러리가 좀만 변경되면 오류가 발생한다 표준형식으로 맞춰서 사용해야한다.
									 // 원래는 JSON.parse(resData) 처럼 제이슨화를 시켜야지 사용할수 있는데 여기선 생략 가능한 이유는 라이브러리가 알아서 기능을 해준다.
		}catch(RuntimeException e){
			response.setContentType("application/json; charset=UTF-8"); 
			PrintWriter out = response.getWriter();
			if(e.getMessage().equals(name + "은 잘못된 이름입니다.")) {
				response.setStatus(600);
				out.println(e.getMessage());
			}else{
				response.setStatus(601);
				out.println(e.getMessage());
			}
		out.flush();
		out.close();
	}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}