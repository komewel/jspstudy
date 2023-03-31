package model;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class MyTodayService implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("today", new Date(System.currentTimeMillis()));
		// 어디로 어떻게 갈 것인가?
		ActionForward actionForward = new ActionForward();	// ActionForward를 쓰기 위한 객체화
		actionForward.setPath("view/output.jsp"); // 이건 보내는 경로
		actionForward.setRedirect(false);  // true면 redirect false는 forward
		return actionForward;
		
	}

}