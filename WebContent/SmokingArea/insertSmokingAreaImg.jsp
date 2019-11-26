<%@page import="DAO.SmokingAreaDAO"%>
<%@page import="Util.Util"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<style></style>
		<script type="text/javascript"></script>
	</head>

	<body>
	
<%
	SmokingAreaDAO sm = new SmokingAreaDAO();
	String saveDir = application.getRealPath("/img/");

	sm.Log(saveDir);
	out.write(saveDir);
	int maxSize = 1024*1024*100;
	String encType = "UTF-8";

	MultipartRequest multipartRequest
	= new MultipartRequest(request, saveDir, maxSize, encType, new DefaultFileRenamePolicy());
	
	String fileName = multipartRequest.getFilesystemName((multipartRequest.getFileNames()).nextElement().toString());
	sm.Log(fileName);
	
	
    //File file = multipartRequest.getFile("file");
%>

	</body>
</html>