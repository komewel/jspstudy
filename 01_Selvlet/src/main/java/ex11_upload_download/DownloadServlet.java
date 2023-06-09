package ex11_upload_download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadServlet")

public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청 파라미터
		request.setCharacterEncoding("UTF-8");
		String path = URLDecoder.decode(request.getParameter("path"), "UTF-8");
		
		// 다운로드 해야 할 File 객체
		File file = new File(path);
		
		// 버퍼드 리더는 텍스트 전용이라서 쓸수가 없다 온갖 형식이 들어갈 수 있기 때문에
		// 다운로드 해야 할 파일을 읽어들일 입력 스트림
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		
		// 다운로드용 응답 헤더 작업
		response.setHeader("Content-Disposition", "attachment");
		response.setHeader("Content-Length", file.length() + "");
		
		// 응답 스트림(=출력 스트림), 응답은 바이트스트림만 지원했음
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		
		// 파일 복사 (in에서 1024바이트를 읽은 다음 out으로 보내기), 마지막은 제대로 못 읽어 올수도 있다 그래서 마지막은 읽어들인 만큼
		byte[] b = new byte[1024];  // 입력단위
		int readByte = 0; 			// 실제로 읽은 바이트
		while((readByte = in.read(b)) != -1){
			out.write(b, 0, readByte); // out에 보내기 위한 작업
		} 
		out.close();
		in.close();
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
