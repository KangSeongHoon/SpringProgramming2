<%@ page contentType="text/html; charset=UTF-8"%>

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
</head>
<body>
	<form method="post" action="fileUpload" enctype="multipart/form-data">
		<!-- 문자 파트  -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">제목</span>
			</div>
			<input name="title" type="text" class="form-control"
				placeholder="첨부 파일 설명 ">
		</div>
		
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">설명 </span>
			</div>
			<input name="description" type="text" class="form-control"
				placeholder="첨부 파일 설명 ">
		</div>

		<!-- 파일 파트  -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="inputGroupFileAddon01">첨부하기2</span>
			</div>
			<div class="custom-file">
				<input name="attach1" type="file" class="custom-file-input"
					id="inputGroupFile01"> <label class="custom-file-label"
					for="inputGroupFile01">ㅎㅎ</label>
			</div>
		</div>
		
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="inputGroupFileAddon01">첨부하기2</span>
			</div>
			<div class="custom-file">
				<input name="attach2" type="file" class="custom-file-input"
					id="inputGroupFile01"> <label class="custom-file-label"
					for="inputGroupFile01">ㅎㅎ</label>
			</div>
		</div>
		<input type="submit" class="btn btn-info" value="파일 업로드 "/>
	</form>
</body>
</html>