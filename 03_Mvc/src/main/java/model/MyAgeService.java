
package model;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class MyAgeService implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String strBirthyear = request.getParameter("birthyear");
		int birthyear = Integer.parseInt(strBirthyear);
		int nowyear = Calendar.getInstance().get(Calendar.YEAR);
		
		request.setAttribute("age", nowyear - birthyear);
		
		// 어디로 어떻게 갈 것인가?
		ActionForward actionForward = new ActionForward();
		actionForward.setPath("view/output.jsp");
		actionForward.setRedirect(false);  // forward
		return actionForward;
	}
	
	
	

}

// interface 를 상속 할때는 파일 만들때 이미 만들어진 부모룰 add로 상속하면된다 
// interface 를 쓰는 이유는 같은 이름의 메소드로 본문만 바꿔서 쓸수있다.
// 하나의 코드 한줄이 myService.execute(request, response); 여러가지 기능을 할 수 있다.
