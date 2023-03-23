package mine1;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Mine1")

public class Mine1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String schDate = request.getParameter("schDate");
		String schDeptCityCode = request.getParameter("schDeptCityCode");
		String schArrvCityCode = request.getParameter("schArrvCityCode");
		
		String serviceKey = "bEQBRPHjt0tZrc7EsL0T8usfsZ1+wT+5jqamBef/ErC/5ZO6N7nYdRmrwR91bh5d3I1AQeY5qdbJOF6Kv0U1CQ==";
	
		String apiURL = "http://openapi.airport.co.kr/service/rest/FlightScheduleList/getIflightScheduleList";		
		apiURL += "?serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8");
		apiURL += "&pageNo=" + URLEncoder.encode("1", "UTF-8");
		apiURL += "?schDate=" + URLEncoder.encode(schDate, "UTF-8");
		apiURL += "?schDeptCityCode=" + URLEncoder.encode(schDeptCityCode, "UTF-8");
		apiURL += "?schArrvCityCode=" + URLEncoder.encode(schArrvCityCode, "UTF-8");
		
		URL url = new URL(apiURL);
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		
		con.setRequestMethod("Get");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
