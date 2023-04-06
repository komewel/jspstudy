package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 알아서 getter setter Tostring을 만들어줌
@AllArgsConstructor
@NoArgsConstructor
public class KyhDTO {
	private int kyhNo;
	private String title;
	private String content;
	private String modifiedDate;
	private String createdDate;
}
