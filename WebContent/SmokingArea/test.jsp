<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>File Form Page</title>
</head>
<body>
<h1>File Form Page</h1>
<hr>
<!-- 파일 업로드를 위해서는 form에 속성을 enctype="multipart/form-data" 라고 부여해야지만 가능하다.  -->
<form action="insertSmokingAreaImg.jsp" method="post" enctype="multipart/form-data">
    이름 : <input type="text" name="name"><br>
	파일 : <input type="file" name="file"><br>
	<input type="submit" name="업로드"><br>
</form>
</body>
</html>
