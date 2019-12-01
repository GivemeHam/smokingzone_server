<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ page import = "com.oreilly.servlet.MultipartRequest" %>
<%@ page import = "com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>

<%

String save = application.getRealPath("/img/");
System.out.println(save);
int size = 100*1024*1024;

MultipartRequest multi = new MultipartRequest(request, save ,size, "UTF-8", new DefaultFileRenamePolicy());

String fileName = multi.getFilesystemName((multi.getFileNames()).nextElement().toString());
out.println(fileName);
%>