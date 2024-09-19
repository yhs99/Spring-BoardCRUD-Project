<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<title>회원가입</title>
</head>
<body>
	<jsp:include page="../header.jsp"/>
		<div class="container">
			<form action="/member" method="POST">
				<div class="mt-3 mb-3">
					<label for="userId" class="form-label">아이디:</label>
					<input type="text" class="form-control" id="userId" placeholder="아이디를 입력하세요" name="userId">
				</div>
				<div class="mt-3 mb-3">
					<label for="userPwd" class="form-label">비밀번호:</label>
					<input type="password" class="form-control" id="userPwd" placeholder="비밀번호를 입력하세요" name="userPwd">
				</div>
				<div class="mt-3 mb-3">
					<label for="userPwdConf" class="form-label">비밀번호 확인:</label>
					<input type="password" class="form-control" id="userPwdConf" placeholder="비밀번호를 다시 입력하세요" name="userPwdConf">
				</div>
				<div class="mt-3 mb-3">
					<label for="userName" class="form-label">이름:</label>
					<input type="text" class="form-control" id="userName" placeholder="이름을 입력하세요" name="userName">
				</div>
				<span class="genderSpan">
					성별:
				</span>
				<div class="form-check">
					<input type="radio" class="form-check-input" id="female" name="gender" value="F" checked>
					<label class="form-check-label" for="female">여성</label>
				</div>
				<div class="form-check">
					<input type="radio" class="form-check-input" id="male" name="gender" value="M">
					<label class="form-check-label" for="male">남성</label>
				</div>
				<div class="mt-3 mb-3">
					<label for="mobile" class="form-label">전화번호:</label>
					<input type="text" class="form-control" id="mobile" placeholder="전화번호를 입력하세요" name="mobile">
				</div>
				<div class="mt-3 mb-3">
					<label for="email" class="form-label">이메일:</label>
					<input type="text" class="form-control" id="email" placeholder="이메일을 입력하세요" name="email">
				</div>
				<input type="submit" value="전송">
			</form>
		</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>
