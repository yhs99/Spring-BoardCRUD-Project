<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>게시판</title>
  <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
	$(function() {
		getBoardData();
	});

	function getBoardData() {
		$.ajax({ 
			url: "/api/board",
			dataType: "json",
			type:"get",
			beforeSend: function() {
				
			},
			success: function(response) {
				renderTable(response.data);
			},
			error: function(e) {
				console.log(e);
			}
		});
	}
	function renderTable(datas) {
		let output = "";
		if(datas.length > 0) {
			output += `<table class="table table-striped">
							<thead>
								<tr>
									<td>#</td>
									<td>글제목</td>
									<td>작성자</td>
									<td>작성일</td>
									<td>조회수</td>
								</tr>
							</thead>
							<tbody>`;
			$.each(datas, function(index, item) {
				let create_at = new Date(item.postDate);
				output += `<tr>
					<td>\${item.boardNo}</td>
					<td>\${item.title}</td>
					<td>\${item.writer}</td>
					<td>\${create_at.getFullYear()}년 \${create_at.getMonth()+1}월 \${create_at.getDate()}일 \${create_at.getHours()}-\${create_at.getMinutes()}</td>
					<td>\${item.readCount}</td>
				</tr>`;
			})
			output += `		</tbody>
						</table>`;
		}
		$(".table-container").html(output);
	}
</script>
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div class="container">
		<div class="table-container pt-5">
		</div>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>
