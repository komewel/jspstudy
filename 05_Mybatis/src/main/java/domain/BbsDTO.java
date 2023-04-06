package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data // ToString, getter, setter를 만들어준다
@AllArgsConstructor
@NoArgsConstructor
public class BbsDTO {
	private int bbsNo;
	private String title;
	private String content;
	private String modifiedDate;
	private String createdDate;
	
	
}
