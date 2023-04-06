package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BbsDAO;

public class BbsListService implements IBbsService {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("bbsList", BbsDAO.getInstance().selectAllBbsList()); // 알아서 Bbs목록이 넘어온다
		return new ActionForward("bbs/list.jsp", false);
		// 트랜잭션이 아니므로 forward
	}
}	
