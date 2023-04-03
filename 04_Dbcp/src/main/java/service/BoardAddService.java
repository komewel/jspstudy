package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDAO;

public class BoardAddService implements IBoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 메소드 호출하기
		
		// 객체로 호출하기
		// 객체.메소드()
		// 허나 new를 막았기 때문에 이건 사용불가
		
		// 클래스로 호출하기
		// 클래스.메소드()
		// static 붙야놔야 클래스로 호출하기가 가능
		
		BoardDAO dao = BoardDAO.getInstance();
		
		
		return null;
	}

}
