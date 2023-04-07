package service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import domain.Member;
import repository.MemberDAO;

public class MemberListService implements IMemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 하나의 서비스는 여러 개의 DAO 메소드를 호출할 수 있다.
		MemberDAO dao = MemberDAO.getInstance();
		List<Member> memberList = dao.selectAllMembers();
		int MemberCount = dao.getMemberCount();
		
		// 응답할 JSON 데이터 만들기
		/*
			{
				"memberCount": 2,
				"memberList": [
					{
						"memberNo": "회원번호",
						"id": "회원아이디",
						"name": "회원명",
						"gender": "회원성별",
						"address": "회원주소"
					},
					{
						"memberNo": 1,
						"id": "회원아이디",
						"name": "회원명",
						"gender": "회원성별",
						"address": "회원주소"
					}
				]
			}
		 */
		JSONObject obj = new JSONObject();
		obj.put("memberCount", MemberCount);
		obj.put("memberList", memberList); //JSON 라이브러리가 Java의 ArrayList를 배열([])로 바꾸고, Java의 Member 객체를 Javascript의 객체로 바꾼다
		
		// 응답 (요청한 곳으로 그대로 응답된다. 즉 ajax() 메소르로 응답 처리된다.
		response.setContentType("application/json; charset=UTF-8"); // 데이터 자체를 넘기는 것이므로 text/html이 아니다
		PrintWriter out = response.getWriter();
		out.println(obj.toString());  // JSON 데이터를 텍스트 형식으로 변경해서 반환하기
		out.flush();
		out.close();
		// return null; 컨트롤러가 ActionForward가 사라지고 더이상 이동을 안하기 때문에 굳이 이작업을 할 필요가 없다.
		
		
		
	}

}
