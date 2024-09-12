<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <title>게시판 목록</title>
    <style type="text/css">
    .table-hover{
    	text-align: center;
    }
    .replyArrows{
    	text-align: left;
    	
    }
    </style>
    <script type="text/javascript">
    	$(function() {
    		timediffPostDate();
    		
    		showModal();
    		
    		
    		$(".modalCloseBtn").click(function () {
    			$("#myModal").hide(); // 모달창 닫기
    		});
    		
    		$("#pagingSize").val(${pagingSize}).prop("selected", true);

    		$("#pagingSize").change(function() {
    			location.href="/board?pageNo=${pagingInfo.pageNo}&pagingSize="+$(this).val();
    		});
    		
    	});
    	
    	function showModal() {
    		let status = '${param.status}'; // url 주소창에서 status쿼리스트링의 값을 가져와 변수 저장
    		console.log(status);
    		
    		if (status == 'success') {
    			// 글 저장성공 모달창
    			$(".modal-body").html('<h5>글 저장에 성공했습니다</h5>');
    			$("#myModal").show();
    		} else if (status == 'fail') {
    			$(".modal-body").html('<h5>글 저장에 실패했습니다</h5>');
    			$("#myModal").show();
    		} else if (status == "removesuccess") {
    			$(".modal-body").html('<h5>글 삭제에 성공했습니다</h5>');
    			$("#myModal").show();
    		} else if (status == "removefail") {
    			$(".modal-body").html('<h5>글 삭제에 실패했습니다</h5>');
    			$("#myModal").show();
    		}
    		
    		// 게시글을 불러올 때 예외가 발생한 경우
    		let except = '${exception}';
    		console.log(except)
    		if (except == 'error') {
	   			$(".modal-body").html('<h3>에러발생</h3>')
				$("#myModal").show();
    		}
    	}
    	
    	// 게시글의 글 작성일을 얻어와서 2시간 이내에 작성한 글이면 new.png 이미지를 제목 옆에 출력한다.
    	function timediffPostDate() {
    		
    		$(".postDate").each(function(i, e) {
    			console.log(i + "번째 : " + $(e).html());
    			
    			let postDate = new Date($(e).html()); // 글 작성일 저장 (Date객체로 변환)
    			let curDate = new Date(); // 현재 날짜 시간 객체 생성
    			console.log(curDate - postDate); // 밀리초
    			
    			let diff = (curDate - postDate) / 1000 / 60 / 60; // 시간단위
    			console.log(diff);
    			
    			let title = $(e).prev().prev().html();
    			console.log(title);
    			
    			if (diff < 2) {
    				let output = "<span class='badge bg-warning text-dark'>NEW</span>";
    				
    				$(e).prev().prev().html(title + output);
    			}
    		}) ;
    	}
    	
    </script>

</head>
<body>

    <!-- ./../ -> 현재위치에 한 단계 더 위로 올라가 -->
<!--     헤더 -->
    <jsp:include page="./../header.jsp"></jsp:include>
    <div class="container">
        <h1>여기는 계층형 게시판 전체 목록</h1>
<%--         <div>${boardList }</div> --%>

    <!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ테이블로 출력ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ-->
    <div>
    	<select class="form-select" id="pagingSize">
    		<option value="10">10개씩 보기</option>
    		<option value="20">20개씩 보기</option>
    		<option value="30">30개씩 보기</option>
    		<option value="80">80개씩 보기</option>
    	</select>
    </div>
    <table class="table table-striped table-hover">
        <thead>
            <tr>
                <th>#</th>
                <th>글제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회수</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="b" items="${boardList}">
				<c:choose>
					<c:when test="${b.isDelete == 'N' }">
		                <tr onclick="location.href='/board/${b.boardNo}'">
		                    <td>${b.boardNo }</td>
		                    <td class="replyArrows">
		                    <c:if test="${b.step > 0}">
		                    	<c:forEach var="i" begin="1" end="${b.step }" varStatus="status" >
		                    		<c:if test="${status.last}">
				                		<img src='/resources/images/arrow.png' width='25px'  style="margin-left: calc(20px * ${i})"/>
		                    		</c:if>
		                    	</c:forEach>
		           	 		</c:if>
		                    ${b.title }</td>
		                    <td>${b.writer }</td>
		                    <td class="postDate">${b.postDate }</td>
		                    <td>${b.readCount }</td>
		                </tr>
					</c:when>
					
					<c:when test="${b.isDelete == 'Y' }">
						<tr>
							<td>${b.boardNo }</td>
							<td colspan="7" style="color : gray;">삭제된 게시물입니다.</td>
						</tr>
					</c:when>
				</c:choose>
            </c:forEach>
        </tbody>
    </table>
    
    <div> <button type="button" class="btn btn-success" onclick="location.href='/hboard/saveBoard';">글쓰기</button> </div>
<!--  페이지네이션 -->
	<div class="paging d-flex justify-content-center">	
		  <ul class="pagination">
		  	<c:if test="${pagingInfo.pageNo > 1 }">
		  		<li class="page-item"><a class="page-link" href="/board?pageNo=${pagingInfo.pageNo - 1 }&pagingSize=${pagingSize}">Previous</a></li>
		  	</c:if>
			  <c:forEach var="i" begin="${pagingInfo.startPageNoCurBlock}" end="${pagingInfo.endPageNoCurBlock < pagingInfo.totalPageCnt ? pagingInfo.endPageNoCurBlock : pagingInfo.totalPageCnt}">
			  	<li class="page-item">
			  		<c:if test="${pagingInfo.pageNo == i}">
			  			<a class="page-link active" href="/board?pageNo=${i}&pagingSize=${pagingSize}">${i}</a>
			  		</c:if>
			  		<c:if test="${pagingInfo.pageNo != i}">
			  			<a class="page-link" href="/board?pageNo=${i}&pagingSize=${pagingSize}">${i}</a>
			  		</c:if>
			  		</li>
			  </c:forEach>
			  <c:if test="${pagingInfo.pageNo < pagingInfo.totalPageCnt}">
			  	<li class="page-item"><a class="page-link" href="/board?pageNo=${pagingInfo.pageNo + 1}&pagingSize=${pagingSize}">Next</a></li>
			  </c:if>
		</ul>
	</div>
    
			<!-- The Modal -->
		<div class="modal" id="myModal" style="display:none;">
		  <div class="modal-dialog">
		    <div class="modal-content">
		
		      <!-- Modal Header -->
		      <div class="modal-header">
		        <h4 class="modal-title">심각 ! 심각 ! 람각 !</h4>
		        <button type="button" class="btn-close modalCloseBtn" data-bs-dismiss="modal"></button>
		      </div>
		
		      <!-- Modal body -->
		      <div class="modal-body">
					
		      </div>
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
		        <button type="button" class="btn btn-danger modalCloseBtn" data-bs-dismiss="modal">Close</button>
		      </div>
		
		    </div>
		  </div>
		</div>    
    </div>
    <jsp:include page="./../footer.jsp"></jsp:include>

</body>
</html>