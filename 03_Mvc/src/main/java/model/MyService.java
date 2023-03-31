package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public interface MyService {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response);
	// execute의 반환값을 String말고 ActionForward로, ActionForward를 쓰기위해선
	// ActionForward를 쓰기위한 반환값이 ActionForward인 execute인 추상메소드
}
