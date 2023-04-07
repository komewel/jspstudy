package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDAO;

public class MemberRemoveService implements IMemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
		Member member = MemberDAO.getInstance().selectMemberByNo(memberNo);
		
		response.setContentType("application.json; charset=UTF-8");
		
		JSONObject obj = new JSONObject(member);
		obj.put(null, false)
		
		
		
	}

}
