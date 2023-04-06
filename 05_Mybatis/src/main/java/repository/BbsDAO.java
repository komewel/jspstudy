package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.BbsDTO;

public class BbsDAO {

	// field, mybatis를 사용할때는 다른 필드 필요없이 하나만 있으면 된다
	private SqlSessionFactory factory;
	
	// Singleton Pattern
	private static BbsDAO dao = new BbsDAO();
	private BbsDAO() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static BbsDAO getInstance() {
		return dao;
	}
	
	/* 메소드명과 쿼리문의 id를 맞추자.(우리끼리의 약속이에욥욥욥) */
	
	// mapper's namespace를 변수 처리 해준다
	private final String NS = "mybatis.mapper.bbs.";
	
	// 1. 목록, sqlSession을 먼저 만든다
	public List<BbsDTO> selectAllBbsList() {
		SqlSession ss = factory.openSession();
		List<BbsDTO> bbsList = ss.selectList(NS + "selectAllBbsList"); 
		// mapper namespace값의 .을 붙여서 실행해줘야하는 쿼리문의 id
		ss.close();
		return bbsList;
	}
	
	// 2. 상세
	public BbsDTO selectBbsByNo(int bbsNo) {
		SqlSession ss = factory.openSession();
		BbsDTO bbs = ss.selectOne(NS + "selectBbsByNo", bbsNo);  // parameter 전달이 있음을 주의!
		// List의 관한 메소드는 selectOne(쿼리문으로 파라미터를 적어서 넘겨주고 싶다면 메소드(두번쨰)선택하면 된다 아님 selectList 둘중 하나 씀 
		ss.close();
		return bbs;
	}
	
	// 3. 삽입
	public int insertBbs(BbsDTO bbs) {
		SqlSession ss = factory.openSession(false);  // autoCommit을 하지 않고, 직접 commit 할거다 고로 false
		int insertResult = ss.insert(NS + "insertBbs", bbs);
		if(insertResult == 1) {  // 삽입 성공 시
			ss.commit();         // commit 하시오.
		}
		ss.close();
		return insertResult;
	}
	
	// 4. 수정
	public int updateBbs(BbsDTO bbs) {
		SqlSession ss = factory.openSession(false);
		int updateResult = ss.update(NS + "updateBbs", bbs);
		if(updateResult == 1) {
			ss.commit();
		}
		ss.close();
		return updateResult;
	}
	
	// 5. 삭제
	public int deleteBbs(int bbsNo) {
		SqlSession ss = factory.openSession(false);
		int deleteResult = ss.delete(NS + "deleteBbs", bbsNo);
		if(deleteResult == 1) {
			ss.commit();
		}
		ss.close();
		return deleteResult;
	}
	
}