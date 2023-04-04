package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.BoardAddService;
import service.BoardDetailService;
import service.BoardListService;
import service.BoardModifyService;
import service.BoardRemoveService;
import service.IBoardService;
@WebServlet("*.do") // 이름은 정해진건 없다 많이쓰는 .do일뿐, getAllBoardList.do getBoardByNo.do writeBoard.do addBoard.do modifyBoard.do(수정하겠다) removeBoard.do 
	
	// 다른 파일에서 설정해놓은 서비스를 어떤 방식으로 구현할지 설정해준 파일

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청과 응답의 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html, charset=UTF-8");
		
		// URLMapping 확인
		String requestURI = request.getRequestURI(); 				      /*    /04_Dbcp/getAllBoardList.do    */
		String contextPath = request.getContextPath(); 					  /*    /04_Dbcp                       */
		String urlMapping = requestURI.substring(contextPath.length());   /*    /getAllBoardList.do            */
		
		// 모든 서비스의 공통 타입 선언(모든걸 IBoardService로 선언 가능)
		IBoardService service = null;
		
		// ActionForward
		ActionForward af = null;
		
		// URLMapping에 따른 서비스 생성, 이런요청이 들어오면 service에 이 경로(서비스)로 인도하겠다, 고르기만 함
		switch(urlMapping) {
		case "/getAllBoardList.do" :
			service = new BoardListService();
			break;
		case "/getBoardByNo.do" :
			service = new BoardDetailService();
			break;
		case "/addBoard.do" :
			service = new BoardAddService();
			break;
		case "/modifyBoard.do" :
			service = new BoardModifyService();
			break;
		case "/removeBoard.do" :
			service = new BoardRemoveService();
			break;
		case "/writeBoard.do" :
			af = new ActionForward("board/write.jsp", false); // board 폴더 아래 write.jsp로 forward한다. (단순 이동의 경우 forward한다.)
			break;
		}
	
		// 서비스 실행, null이아니면 실행되는것이므로,   
		if(service != null) {
			af = service.execute(request, response); // 콘트롤러가 request를 받은 후에 여기서 다시 서비스로 보내준다 
			}
		
		// 응답View로 이동, nullpointerException을 받는다.
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getPath());
			} else {
				request.getRequestDispatcher(af.getPath()).forward(request, response);
			}
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
