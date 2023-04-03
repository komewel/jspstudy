package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

	// ActionForward를 쓰기위한 서비스 구현
public interface IBoardService {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response); // 이 한줄이 모든 서비스의 핵심이다.
}
