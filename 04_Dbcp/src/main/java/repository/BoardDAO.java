package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.BoardDTO;

public class BoardDAO {
	
	// 서비스 윤곽, 모든 메소드가 사용할 공통 필드, 데이터를 통신하기 위한 DAO
	private Connection con; // 필드값은 기본적으로 null값을 가지므로 초기화 필요 x
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;

	// Connection 관리를 위한 DataSource 필드
	// Datasource가 가지고 있는 connection 8개를 가지고 있는데 getConnection()으로 각 서비스한테 기능을 빌려준다.
	// 추가로 반납메소드도 있다.
	private DataSource dataSource;
	
	// DAO(동시성) dao를 두번 호출해서 사용하면 한 객체는 board객체의 insert
	// 한객체는 select하러가고 동시 접근할 우려가 있다 DAO는 언제나 이런 위험땜에 
	// 추가객체 생성을 막아야한다 singleton pattern(하나만 만들어둘수 있다 
	// 객체를 하나만 만들어 놓고 쓰겠다))
	// Singleton Pattern으로 DAO 생성하기
	private static BoardDAO dao = new BoardDAO();
	private BoardDAO() {   // private로 막았음 객체화 금지, 생성자를 못부르게(동시성문제)
		// context.xml에서 <Resource name="jdbc/GDJ61" />인 Resource를 읽어서 DataSource 객체 생성하기(name을 이용한 JNDI방식으로도 부름)
		try {
			Context context = new InitialContext();
			Context envContext = (Context)context.lookup("java:comp/env"); // 읽어들이는 Context 톰캣 환경을적은거
			dataSource = (DataSource)envContext.lookup("jdbc/GDJ61"); // 실제이름을 적은거(context.xml 에서 name이랑 같은 값이다)
			
			/*
			 위 세줄이랑 아예 같은 코드
			 Context context = new InitialContext();
			 dataSource = envContext.lookup("java:comp/env/jdbc/GDJ61")
			 			 
			 */
		}catch(NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static BoardDAO getInstance() { 
		return dao; // 아예 못쓰게 할 순 없어서 빌려주는 의미
					// static 필드만 사용가능 고로 dao가 static 처리가 되야함
	}
	
	
	// 자원(Connection, PreparedStatement, ResultSet) 반납하기
	private void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시글 목록 반환하기
	public List<BoardDTO> selectBoardList() {
		
		// 1. ArrayList 생성
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		try {
			
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문
			sql = "SELECT BOARD_NO, TITLE, CONTENT, MODIFIED_DATE, CREATED_DATE FROM BOARD ORDER BY BOARD_NO DESC"; // 내림차순 정렬
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. PreparedStatement 객체를 이용해 쿼리문 실행(SELECT문 실행은 executeQuery 메소드로 한다.
			rs = ps.executeQuery(); 
			
			// 6. ResultSet 객체(결과 집합)를 이용해서 ArrayList로 만듦, rs.next()(값이 true일때만) 1번 행의 rs값이 얹어진다 rs.getint("BOARD_NO"),
			// rs.getString("TITLE"), rs.getString("Content") 등등 칼럼값을 가져올수 있는데 이값을 BoardDTO board화 시켜서 가져올 수 있다. (ArrayList.add로 저장시킨다)
			while(rs.next()) {
				// step1. board 테이블의 결과 행(ROW)을 읽는다.
				int board_no = rs.getInt("BOARD_NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Date modified_date = rs.getDate("MODIFIED_DATE");
				Date created_date = rs.getDate("CREATED_DATE");
				
				// step2. 읽은 정보를 이용해서 BoardDTO 객체를 만든다.
				BoardDTO board = new BoardDTO(board_no, title, content, modified_date, created_date);
				
				// step3. BoardDTO 객체를 ArrayList에 추가한다.
				boardList.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//예외 발생 여부와 상관 없이 항상 자원의 반납을 해야 한다. 
			close(); // 자원의 반납을 언제나 해준다.
		}
		// 7. ArrayList 반환
		return boardList;
	}
	
	// 게시글 반환하기
	public BoardDTO selectBoardByNo(int board_no) {
		
		// 1. 반환할 BoardDTO board 선언
		BoardDTO board = null;
		
		try {
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문
			sql = "SELECT BOARD_NO, TITLE, CONTENT, MODIFIED_DATE, CREATED_DATE FROM BOARD WHERE BOARD_NO = ?"; 
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. 쿼리문에 변수 값 전달하기
			ps.setInt(1, board_no);
			
			// 6. PreparedStatement 객체를 이용해 쿼리문 실행(SELECT문 실행은 executeQuery 메소드로 한다.
			rs = ps.executeQuery(); 
			
			// 7. ResultSet 객체(결과 집합)를 이용해서 BoardDTO만듦
			if(rs.next()) {
				
				// step1. board 테이블의 결과 행(ROW)을 읽는다.
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Date modified_date = rs.getDate("MODIFIED_DATE");
				Date created_date = rs.getDate("CREATED_DATE");
				
				// step2. 읽은 정보를 이용해서 BoardDTO 객체를 만든다.
				board = new BoardDTO(board_no, title, content, modified_date, created_date);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(); 
		}
			
		// 8. 조회된 게시글 BoardDTO board 반환
		return board;
	}
	
	// 게시글 삽입하기
	public int insertBoard(BoardDTO board) {
		
		 // 1. 삽입 결과 변수 선언
		int insertResult = 0;
		
		try {
			
			con = dataSource.getConnection();
			
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, NULL, SYSDATE)"; // ? 처리는 변수 처리 sql과 자바에서 변수 처리를 위한 
					
			ps = con.prepareStatement(sql);	
			
			// 쿼리문에 변수 값 전달하기
			ps.setString(1, board.getTitle());   // 1번째 물음표(?)(=변수)에 title 전달하기
			ps.setString(2, board.getContent()); // 1번째 물음표(?)(=변수)에 content 전달하기
			
			// SELECT와달리 INSERT는 executeUpdate() 라는 메소드가 처리해준다
			
			insertResult = ps.executeUpdate(); // 0아니면1이 나온다
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
		 close();
		}
		return insertResult; // 0 아니면 1을 반환한다
	}
	
	// 게시글 수정하기
	public int updateBoard(BoardDTO board) {
		
		// 1. 수정 결과 변수 선언
		int updateResult = 0;
		
		try {
			
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFIED_DATE = SYSDATE WHERE BOARD_NO = ?";
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. 쿼리문에 변수 값 전달하기
			ps.setString(1, board.getTitle());   // 1번째 물음표(?)에 title 전달하기
			ps.setString(2, board.getContent()); // 2번째 물음표(?)에 content 전달하기
			ps.setInt(3, board.getBoard_no());   // 3번째 물음표(?)에 board_no 전달하기
			
			// 6. PreparedStatement 객체를 이용해 쿼리문 실행(UPDATE문 실행은 executeUpdate 메소드로 한다.)
			updateResult = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 예외 발생 여부와 상관 없이 항상 자원의 반납을 해야 한다.
			close();
		}
		
		// 7. 수정 결과 반환
		return updateResult;
		
	}
	
	// 게시글 삭제하기
	public int deleteBoard(int board_no) {
		
		int deleteResult = 0;
		
		try {
			
			con = dataSource.getConnection();
			
			sql = "DELETE FROM BOARD WHERE BOARD_NO = ?"; // ? 처리는 변수 처리 sql과 자바에서 변수 처리를 위한 
					
			ps = con.prepareStatement(sql);	
			
			// 쿼리문에 변수 값 전달하기
			ps.setInt(1, board_no);   // 1번째 물음표(?)(=변수)에 board_no 전달하기
			
			// SELECT와달리 INSERT는 executeUpdate() 라는 메소드가 처리해준다
			deleteResult = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
		 close();
		}
		return deleteResult;
		
	}
}
