<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
	
		String item = request.getParameter("item");
		int itemCount = Integer.parseInt(request.getParameter("item_count"));
		
		// 제품명 + 구매수량을 하나의 Map으로 저장한다.
		Map<String, Object> map = new HashMap<>();
		map.put("item", item);
		map.put("itemCount", itemCount);
		
		// session에 저장된 cart 속성이 있는지 확인한 뒤 없다면 새로운 cart를 만들어서 session에 저장한다.
		List<Map<String, Object>> cart = (List<Map<String, Object>>)session.getAttribute("cart"); 
		if(cart == null){
			cart = new ArrayList<>();
			session.setAttribute("cart", cart);
		}
		// cart 값은 세션에 있는 정보를 메모리에 있는 참조(주소)값으로 저장하고 다시 세션값으로 참조값이
		// 저장되는거라서 List 메소드인 add 써서 저장할 수 있다 결론적으로 주소를 공유한다고 생각하면 된다
		// 데이터를 저장한다는 개념이 아닌 주소값을 저장한다고 보면 된다
		
		
		// session의 cart에 Map 저장하기, 이부분이 이해하기 힘든 부분
		cart.add(map);
		
		// 참고
		/*
			class Product {
				String item;
				int itemCount;
				Product(String item, int itemCount){
					this.item = item;
					this.itemCount = itemCount;
				}
			}
			Product product = new Product(item, itemCount);		
		*/
		
	%>
	
	<script>
		if(confirm('<%=item%>을 장바구니에 추가했습니다.\n장바구니를 확인하려면 "확인", 계속 쇼핑하려면 "취소"버튼을 누르세요')){
			location.href = '03_cart_list.jsp';    // 원래 전에는 redirect할때는 파라미터값을 따로 넘겨줬는데 세션이 있으면 파라미터값을 따로 넘길 필요가 없다
		}else {
			location.href = '01_form.jsp';
		}
	</script>
	<%-- 
		자바 스크립트에서는 자바스크립트객체로 저장되지만 자바에서는 map으로 저장된다 
		자바 스크립트에서 제이슨으로 자바에서는 map으로 저장한다
	--%>
	
</body>
</html>