package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

	// 데이터베이스와의 통신을 위한 파일

@NoArgsConstructor  // 롬복식 생성자 만들기
@AllArgsConstructor // 롬복식 필드작업
@Getter				// 롬복식 Getter
@Setter				// 롬복식 Setter

public class BoardDTO {
	private int board_no;
	private String title;
	private String content;
	private Date modified_date;
	private Date created_date;
	
	
	
}
