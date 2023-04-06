package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BbsDTO;
import repository.BbsDAO;

public class BbsAddService implements IBbsService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		BbsDTO bbs = new BbsDTO();
		bbs.setTitle(request.getParameter("title"));
		bbs.setContent(request.getParameter("content"));
		
		int insertResult = BbsDAO.getInstance().insertBbs(bbs);
		
		try {
			PrintWriter out = response.getWriter();
			if(insertResult == 1) {
				out.println("<script>");
				out.println("alert('BBS가 등록되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/list.do'");
				out.println("</script>");
				out.flush();
				out.close();
				return null; 
				// 성공했을땐 null값을 반환해준다.
				// Bbscontroller를 통한 이동을 방지, 
				// 이미 응답이 완료 되었기 때문에 controller로 null값이 넘어가므로 '
			}else if(insertResult != 1) {
				out.println("<script>");
				out.println("alert('BBS가 등록되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/list.do'");
				out.println("</script>");
				out.flush();
				out.close();
				return null; 
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
			// BBS 등록 실패
			return new ActionForward(request.getContextPath() + "/list.do", true);
			// 실패했을시 여기에서 처리된다.
	}

}
