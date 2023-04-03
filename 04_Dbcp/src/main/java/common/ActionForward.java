package common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//ActionForward를 쓰기위해 선언한 파일

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActionForward {
	private String Path;         // 이동할 Jsp 경로(경로에 이름도 포함)
	private boolean isRedirect;  // 이동 방식(true이면 redirect, false이면 forward)
}

