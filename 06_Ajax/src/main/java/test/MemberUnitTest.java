package test;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Member;
import repository.MemberDAO;

public class MemberUnitTest {

	@BeforeClass // MemberunitTest 클래스(테스트 파일) 실행 이전에 한 번 먼저 실행된다. (static 처리가 되어 있어야 한다.) 	
	// 테스트전에 삽입이 되는지 보겠다 조회도 해보고 열어보고 수정도 해볼거다 테스트 해보고 다되면 삭제까지 한번에 자기가 다 알아서 해주는 종합 선물 세트
	public static void 삽입테스트() {
		Member member = new Member(0, "admin", "관리자", "M", "seoul"); // 1이란 번호는 시퀀스가 집어넣으므로 굳이 필요하지 않다
		assertEquals(1, MemberDAO.getInstance().insertMember(member));
	}
	
	@Test
	public void 목록테스트() {
		assertEquals(1, MemberDAO.getInstance().selectAllMembers().size());
	}
	
	@Test
	public void 상세테스트() {
		Member member = new Member(1, "admin", "관리자", "M", "seoul");
		assertEquals(member, MemberDAO.getInstance().selectMemberByNo(1));
	}  // 가지고온 멤버와 동일한지 검사

	@Test
	public void 수정테스트() {
		Member member = new Member(1, null, "new관리자", "F", "newseoul");
		assertEquals(1, MemberDAO.getInstance().updateMember(member));
	}
	
	@AfterClass // MemberunitTest 클래스(테스트 파일) 실행 이후에 한 번 먼저 실행된다. (static 처리가 되어 있어야 한다.) 
	public static void 삭제테스트() {
		assertEquals(1, MemberDAO.getInstance().deleteMember(1)); // 회원번호1번을 삭제하고자 테스트
		
	}
	// 만약 실패시 sql 파일 한번 전체 돌리기

}
