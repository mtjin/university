<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="login.LogonDBBean"%>

<%
	request.setCharacterEncoding("euc-kr");
%>

<%
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");

	LogonDBBean logon = LogonDBBean.getInstance();
	int check = logon.userCheck(id, passwd);

	if (check == 1) {
		session.setAttribute("id", id);
		response.sendRedirect("sessionMain.jsp");
	} else if (check == 0) {
%>
<script>
	alert("비밀번호가 맞지 않습니다.");
	history.go(-1);
</script>
<%
	} else {
%>
<script>
	alert("아이디가 맞지 않습니다.");
	history.go(-1);
</script>
<%
	}
%>
