<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>

<%@page import="com.oreilly.servlet.MultipartRequest"%>

<%@ page pageEncoding="UTF-8"%>

<%

String dir = application.getRealPath("/SmokingArea/img");



int max = 10*1024*1024;

//최대크기, dir 디렉토리에 파일을 업로드하는 multipartRequest



MultipartRequest mr = new MultipartRequest(request, dir, max, "UTF-8");

String orgFileName = mr.getOriginalFileName("uploaded_file");

String saveFileName = mr.getFilesystemName("uploaded_file");



out.println(orgFileName+"이 저장되었습니다.");

%> 