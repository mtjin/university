<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<head>
<title>로그인</title>
</head>
<body>
	<h2>로그인 폼</h2>
	<form method="post" action="sessionLoginPro.jsp">
		아이디: <input type="text" name="id" maxlength="12"> <br />
		패스워드: <input type="password" name="passwd" maxlength="12"><br />
		<input type="submit" value="로그인"> <input type="button"
			value="회원가입" onclick="location.href='insertMemberForm.jsp'">

	</form>
</body>
</html>