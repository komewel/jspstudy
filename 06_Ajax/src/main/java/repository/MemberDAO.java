package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Member;

public class MemberDAO {
	
	// SqlSession은 메소드
	// SqlSessionFactory는 필드 모두가 쓸수 있게 만들어 놓는다
	private SqlSessionFactory factory;
	
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO(){
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static MemberDAO getInstance() {
		return dao;
	}
	// mapper's namespace (어떤 mapper인지 인식하기 위함)
	private final String NS = "mybatis.mapper.member.";
	
	// 메소드명은 쿼리의 id와 동일한 이름을 사용하자(약속)
	
	// 목록 
	public List<Member> selectAllMembers() {
		SqlSession ss = factory.openSession();
		List<Member> members = ss.selectList(NS + "selectAllMembers"); // mybatis.mapper.member.selectAllMembers, selectAllMembers라는 아이디를 가진 쿼리문 호출을 위한 
		ss.close();
		return members;
	}
	// 리스트에 저장되는 데이터인 member타입으로 저장된다
	
	// 전체 회원 수
	public int getMemberCount() {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(NS + "getMemberCount"); // 결과물이 하나일때 쿼리문 호출을 위한
		ss.close();
		return count;
	}
	
	// 상세보기, 셀렉트문은 result타입을 설정해줘야한다.
	public Member selectMemberByNo(int memberNo) {
		SqlSession ss = factory.openSession();
		Member member = ss.selectOne(NS + "selectMemberByNo", memberNo); // 여기서 하나 라는건 행이 한줄이라는 뜻 두번째 인수가 쿼리문의 전달되는 내용(파라미터), 전달되는 내용이 그대로 이용된다
		ss.close();
		return member;
	}
	
	// 삽입
	public int insertMember(Member member) { // 멤버를 한명받아와서 저장한다
		SqlSession ss = factory.openSession(false);
		int insertResult = ss.insert(NS + "insertMember", member);
		if(insertResult == 1) {
			ss.commit();
		}
		ss.close();
		return insertResult;
	}
		
	// 수정
	public int updateMember(Member member) {
		SqlSession ss = factory.openSession(false);
		int updateResult = ss.update(NS + "updateMember", member);
		if(updateResult == 1) {
			ss.commit();
		}
		ss.close();
		return updateResult;
	}
	
	// 삭제
	public int deleteMember(int memberNo) {
		SqlSession ss = factory.openSession(false);
		int deleteResult = ss.delete(NS + "deleteMember", memberNo);
		if(deleteResult == 1) {
			ss.commit();
		}
		ss.close();
		return deleteResult;
	}
	

	
}

// ajax는 페이지이동(한페이지에서 다 처리를 완료한 상태)이 없기 때문에 속도가 빠르다 (ex,관리자기능), 하나의 페이지에서 화면만 계속 바꾸려면 자바스크립트 밖에 없다.