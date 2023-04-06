package repository;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class KyhDAO {

	private SqlSessionFactory factory;
	// sqlSession은 sql과 Mybatis연결을 긴밀하게 해주는 클래스
	
	// 싱글톤패턴을 위해 static설정, 데이터 베이스와 연결 해서 할때
	private static KyhDAO dao = new KyhDAO();
	private KyhDAO() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			// 바이트 정보가 넘어오는 스트림
			// Resources : 클래스 로더를 통해 리소스에 대한 액세스를 단순화하는 클래스입니다.
			// 단순화해서 재조합 한뒤에 리소스 파일의 경로르 쉽게 찾게해주는 기능 
			// 지 알아서 경로 찾겠다는 뜻 스트림으로 뚫어서 
			// 
			InputStream inputstream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputstream);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static KyhDAO getInstance() {
		return dao;
		// dao는 클래스 내부에 같이 있기 때문에 여기선 호출 가능
	}
}
