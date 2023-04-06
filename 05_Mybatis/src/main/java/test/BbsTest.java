package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import domain.BbsDTO;
import repository.BbsDAO;

public class BbsTest {

	// BbsDAO의 메소드 단위로 테스트를 진행한다.
	private BbsDAO dao = BbsDAO.getInstance();
	
	@Test  // 테스트를 수행하는 메소드, 내 맘대로 한글로 적어도 된다, 다른사람들이랑 같이 쓰는게 아니기 때문에, 테스트 결과 문제가 없으면 주석 처리하자 문제없이
	public void 목록테스트() {
		assertEquals(2, dao.selectAllBbsList().size());   // 1번째인수는 기댓값, 2번째인수는 dao한테 실제로 테스트 해본 값 맞으면 true 틀리면 false
	}

	@Test
	public void 상세테스트() {
		assertNotNull(dao.selectBbsByNo(1));
	}
	
	@Test
	public void 삽입테스트() {
		BbsDTO bbs = new BbsDTO();
		bbs.setTitle("테스트제목");
		bbs.setContent("테스트내용");
		assertEquals(1, dao.insertBbs(bbs));
	}
}
