<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="DAO.SmokingAreaDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
  	String json = request.getParameter("currentlocation");
   
  	
  	System.out.println("aaa : " + json);
   	JSONParser parser = new JSONParser();
   	Object obj = parser.parse(json);
   	JSONObject jsonObj = (JSONObject)obj;
   	
   
%>
