<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
<style>
	.topHeader {
		background-image: url("../resources/images/astro.png");
        height: 50vh;
        width: 100%;
        background-repeat : no-repeat;
        background-size : cover;
	}
</style>
</head>
<body>
<div class="p-5 bg-primary text-white text-center topHeader">
  <h1>Spring-M Project</h1>
  <p>시작 : 2024.08.30</p> 
</div>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link active" href="/">메인</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/board">게시판</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Disabled</a>
      </li>
    </ul>
  </div>
</nav>
</body>
</html>