package ex07_ajax;

public class AgeHandleException extends MyHandleException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4534766161295571271L;
//	private int errorCode;
	
// static final serialVersionUID 가 필요하다 예외를 따로 생성할려면
// 생성하는 방법은 옆에 전구 표시 누르면 두번째 선택시 자동으로 생성된다 
	
	public AgeHandleException(String message, int errorCode) {// 응답코드(에러코드)

		super(message, errorCode); // 수퍼클래스를 이용하여 불필요한 코드를 줄여준다, 부모의 생성자를 호출한다.
	}

//	public int getErrorCode() {
//		return errorCode;
//	}

//	public void setErrorCode(int errorCode) {
//		this.errorCode = errorCode;
//	}
	}
