<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>

<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="DAO.SmokingAreaDAO"%>

<%

SmokingAreaDAO sm = new SmokingAreaDAO();
sm.Log("dd");


	String json = request.getParameter("file1");
	sm.Log(json);
String dir = application.getRealPath("/");

int max = 10*1024*1024;
sm.Log("dd");

//최대크기, dir 디렉토리에 파일을 업로드하는 multipartRequest



MultipartRequest mr = new MultipartRequest(request, dir, max, "UTF-8");
sm.Log("dd");

String orgFileName = mr.getOriginalFileName("uploaded_file");
sm.Log("dd");

String saveFileName = mr.getFilesystemName("uploaded_file");
sm.Log("dd");



out.println(orgFileName+"이 저장되었습니다.");

%> 