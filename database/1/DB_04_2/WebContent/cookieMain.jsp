<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
	String id = "";
	try {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("id")) {
					id = cookies[i].getValue();
				}
			}
			if (id.equals("")) {
				response.sendRedirect("loginForm.jsp");
			}
		} else {
			response.sendRedirect("loginForm.jsp");
		}
	} catch (Exception e) {
	}
%>
<html>
<head>
<meta charset="EUC-KR">
<title>쿠키를 사용한 간단한 회원 인증</title>
</head>
<body>
	<b><%=id%></b>님이 로그인 하셨습니다.
	<form method="post" action="cookieLogout.jsp">
		<input type="submit" value="로그아웃">
	</form>
</body>
</html>