<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="DAO.BoardDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
  	String value = request.getParameter("boardReviewValue");
   
   	BoardDAO boardDAO= new BoardDAO();
	
   	out.println(boardDAO.selectBoardReivew(value));	
%>
