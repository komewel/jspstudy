package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardDTO;
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
		// 1. 요청 파라미터
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 2. BoardDTO 객체 생성(map도 가능하다, 모으는 의미의 집중)
		BoardDTO board = new BoardDTO(); // 매개인자 없는거 호출
		board.setTitle(title);
		board.setContent(content);
		
		// 컨트롤러로에서 넘어와서 애드서비스에서변수 설정이 된 다음에 객체화를 거쳐서 DB에 들어가는거다
		
		// 3. 삽입을 위해서 DB로 BoardDTO를 전달(BoardDAO의 insertBoard 메소드)
		int insertResult = BoardDAO.getInstance().insertBoard(board); // 1이 넘어오면 성공 성공인지 아닌지만 판별하면 되므로 
		
		System.out.println(insertResult == 1 ? "삽입성공" : "삽입실패");
		
		// 4. 어디로 and 어떻게 이동
		
		return new ActionForward(request.getContextPath() + "/getAllBoardList.do", true); // insert, update, delete 이후에는 redirect를 해야한다 
	}

}
