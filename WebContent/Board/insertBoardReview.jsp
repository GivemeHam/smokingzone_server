<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@page import="DAO.BoardDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
  	String json = request.getParameter("json_boardReviewValue");	
   
   	JSONParser parser = new JSONParser();
   	Object obj = parser.parse(json);
   	JSONObject jsonObj = (JSONObject)obj;
   	
   	BoardDAO boardDAO = new BoardDAO();
	
   	String rst = boardDAO.insertBoardReview(jsonObj);
    out.println(rst);	
%>
