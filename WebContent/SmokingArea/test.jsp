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
<!-- ���� ���ε带 ���ؼ��� form�� �Ӽ��� enctype="multipart/form-data" ��� �ο��ؾ����� �����ϴ�.  -->
<form action="insertSmokingAreaImg.jsp" method="post" enctype="multipart/form-data">
    �̸� : <input type="text" name="name"><br>
	���� : <input type="file" name="file"><br>
	<input type="submit" name="���ε�"><br>
</form>
</body>
</html>