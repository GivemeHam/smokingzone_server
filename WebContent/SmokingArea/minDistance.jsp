<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="DAO.SmokingAreaDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	String get_param = request.getParameter("currentlocation");
  	
  	
  	//System.out.println("currentlocation : " + currentlocation);
   	JSONParser parser = new JSONParser();
   	Object obj = parser.parse(get_param);
   	JSONObject jsonObj = (JSONObject)obj;

   	jsonObj.get("lat").toString();
   	jsonObj.get("lng").toString();
   	
   	
   	//더미 값 일단 출력
   	out.println("{\"lat\":37.551293,\"lng\":127.072949}");
   
%>
