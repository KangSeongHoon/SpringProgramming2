<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/resources/js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function btnLogin() {
		if ($("#mid").val() == "")
			return false;
		if ($("#mpassword").val() == "")
			return false;
		return true;
	}

	function btnLogout() {
		$("#loginForm").show();
		$("#logoutDiv").hide();
	}
	function btnLogout() {
		location.href = ("logout");
	}
	function jsonDownload1() {
		$.ajax({
			url : "jsonDownload1",
			success : function(data) {
				var html = "";
				html +="<tr>";
				html +="<td>"+data.bno +"</td>";
				html +="<td>"+data.btitle +"</td>";
				html +="<td>"+data.bcontent +"</td>";
				html +="<td>"+data.writer +"</td>";
				html +="<td>"+data.date +"</td>";
				html +="<td>"+data.hitcount +"</td>";
				html +="</tr>";
				
				$("tbody").append(html);
			}
		});

	}
	function jsonDownload2() {
		$.ajax({
			url : "jsonDownload2",
			success : function(data) {
				var html = "";
				html +="<tr>";
				html +="<td>"+data.bno +"</td>";
				html +="<td>"+data.btitle +"</td>";
				html +="<td>"+data.bcontent +"</td>";
				html +="<td>"+data.writer +"</td>";
				html +="<td>"+data.date +"</td>";
				html +="<td>"+data.hitcount +"</td>";
				html +="</tr>";
				
				$("tbody").append(html);
			}
		});

	}
</script>
</head>
<body>


	<c:if test="${loginResult != 'success'}">
		<div>

			<form id="loginForm" method="post" action="login">
				<div class="form-group">
					<label for="mid">아이디</label> <input type="text"
						class="form-control" id="mid" name="mid"
						aria-describedby="emailHelp">
					<c:if test="${loginResult == 'wrongMid'}">
						<span style="color: red">로그인 아이디가 없습니다</span>
					</c:if>
				</div>
				<div class="form-group">
					<label for="mpassword">패스워드</label> <input type="password"
						class="form-control" id="mpassword" name="mpassword">
				</div>
				<input onclick="return btnLogin()" type="submit"
					class="btn btn-primary" value="로그인" /><br />
				<c:if test="${loginResult == 'wrongMid'}">
					<span style="color: red">비밀번호가 없습니다</span>
				</c:if>
			</form>
		</div>
	</c:if>
	<c:if test="${loginResult == 'success'}">
		<div id="logoutDiv">
			<button onclick="btnLogout()" class="btn-danger">로그아웃</button>
	</c:if>
	</div>
	<h5>[OutputStream을 이용해서 로그인 구현]</h5>
	<div>
		<img src="<%=application.getContextPath()%>/resources/image/Koala.jpg"
			width="100" /> <br /> <img src="fileDownload?fname=Koala.jpg"
			width="100" /> <br /> <a href="fileDownload?fname=Koala.jpg">파일
			다운로드</a> <br />
	</div>
	</br>
	<h5>[JSON 데이터 다운로드 ]</h5>
	<a href="javascript:jsonDownload1()">JSON에서 생성</a>
	</br>

	<a href="javascript:jsonDownload2()">Controller에서 생성</a>
	</br>
	<div>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">내용</th>
					<th scope="col">글쓴이</th>
					<th scope="col">날짜</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
		<tbody>
		
		</tbody>
		</table>
	</div>

</body>
</html>