package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDAO;

public class BoardRemoveService implements IBoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			if(request.getMethod().equalsIgnoreCase("get")) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 요청입니다')");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				out.close();
				return null;
				// 컨트롤러로 null값을 반환하면 컨트롤러는 이동(redirect 또는 forward)을 하지 않는다
				// return null, 아무것도 하지말라고 null을 반환하는거다, 이동하지말라고 내가 응답을 통해서 *직접* 이동을 시킨상태이므로  
				// controller로 null값을 반환하므로 ActionForward가 동작하지말라고 null값을 반환하는 것이다. 
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// 1. 요청 파라미터
		String strBoard_no = request.getParameter("board_no");
		int board_no = Integer.parseInt(strBoard_no.isEmpty() ? "0" : strBoard_no); // 공백이면 0으로 판별하겠다
		
		// 2. BoardDAO의 deleteBoard 메소드 호출
		int deleteResult = BoardDAO.getInstance().deleteBoard(board_no);
		System.out.println(deleteResult == 1 ? "삭제성공" : "삭제실패");
		
		// 3. 어디로 and 어떻게 이동
		return new ActionForward(request.getContextPath() + "/getAllBoardList.do" , true);
		
	}

}
