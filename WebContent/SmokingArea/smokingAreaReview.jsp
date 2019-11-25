<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="DAO.SmokingAreaDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
  	String value = request.getParameter("smokingAreaReviewValue");
   
   	SmokingAreaDAO smokingArea = new SmokingAreaDAO();
	
   	out.println(smokingArea.selectSmokingAreaReivew(value));	
%>
