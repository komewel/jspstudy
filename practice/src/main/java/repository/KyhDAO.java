package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class KyhDAO {
	private SqlSessionFactory factory;
	
	// Singleton Pattern
	private static KyhDAO dao = new KyhDAO();
	private KyhDAO() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static KyhDAO getInstance() {
		return dao;
	}
	
	
	// mapper's namespace를 변수 처리 해준다
	private final String NS = "mybatis.mapper.kyh.";
	
	// 1. 목록, sqlSession을 먼저 만든다
	public List<KyhDAO> selectAllBbsList() {
		SqlSession ss = factory.openSession();
		List<KyhDAO> kyhList = ss.selectList(NS + "selectAllBbsList"); 
		ss.close();
		return kyhList;
	}
	
	// 2. 상세
	public KyhDAO selectBbsByNo(int kyhNo) {
		SqlSession ss = factory.openSession();
		KyhDAO kyh = ss.selectOne(NS + "selectBbsByNo", kyhNo);  // parameter 전달이 있음을 주의!
		// List의 관한 메소드는 selectOne(쿼리문으로 파라미터를 적어서 넘겨주고 싶다면 메소드(두번쨰)선택하면 된다 아님 selectList 둘중 하나 씀 
		ss.close();
		return kyh;
	}
	
	// 3. 삽입
	public int insertKyh(KyhDAO kyh) {
		SqlSession ss = factory.openSession(false);
		int insertResult = ss.insert(NS + "insertKyh", kyh);
		if(insertResult == 1) {  
			ss.commit();        
		}
		ss.close();
		return insertResult;
	}
	
	// 4. 수정
	public int updateBbs(KyhDAO bbs) {
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