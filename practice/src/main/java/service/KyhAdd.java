package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.KyhDTO;
import repository.KyhDAO;

public class KyhAdd implements IKyhService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		KyhDTO kyh = new KyhDTO();
		kyh.setName(request.getParameter("name"));
		kyh.setKor(request.getParameter("kor"));
		kyh.setEng(request.getParameter("eng"));
		kyh.setMath(request.getParameter("math"));
		
		int insertResult = KyhDAO.getInstance().insertKyh(kyh);
		
		try {
			PrintWriter out = response.getWriter();
			if(insertResult == 1) {
				out.println("<script>");
				out.println("alert('학생이 등록되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/manage.do'");
				out.println("</script>");
				out.flush();
				out.close();
				return null; 
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		return null;
	}

}
