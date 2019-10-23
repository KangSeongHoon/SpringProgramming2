<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	
</script>

</head>
<body>
	<h5>게시물 내용</h5>
	<form>
		<div class="form-group">
			<label for="bno">TITLE</label> <input id="bno" type="text"
				class="form-control" value="${board.bno}" readonly="readonly"/>
		</div>
		<div class="form-group">
			<label for="btitle">TITLE</label> <input id="btitle" type="text"
				class="form-control" value="${board.btitle}" readonly="readonly" />
		</div>
		<div class="form-group">
			<label for="bwriter">writer</label> <input id="bwriter" type="text"
				class="form-control" value="${board.bwriter} " readonly="readonly"/>
			<div class="form-group">
				<label for="bhitcount">hitcount</label> <input id="bhitcount"
					type="text" class="form-control" value="${board.bhitcount} " readonly="readonly"/>
			</div>
			<div class="form-group">
				<label for="bdate">date</label> <input id="bdate" type="text"
					class="form-control" value='<fmt:formatDate value="${board.bdate }" pattern="yyyy년  MM월 dd월"/>' readonly="readonly"/>
			</div>
			<div class="form-group">
				<label for="bcontent">CONTENT</label>
				<textarea id="bcontent" name="bcontent" class="form-control"
					rows="3" readonly="readonly">${board.bcontent}</textarea>
			</div>
			
	</form>
	
	<a href="boardList?pageNo=${pageNo }" class="btn btn-info">목록</a>
	
	<c:if test="${board.bwriter == mid }">
	<a href="updateBoard?bno=${board.bno }" class="btn btn-info">수정</a>
	<a href="deleteBoard?bno=${board.bno }" class="btn btn-danger">삭제</a>
	</c:if>
	

</body>
</html>