<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@page import="DAO.SmokingAreaDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
  	String json = request.getParameter("json_smokingReportValue");	
   
   	JSONParser parser = new JSONParser();
   	Object obj = parser.parse(json);
   	JSONObject jsonObj = (JSONObject)obj;
   	
   	SmokingAreaDAO smokingArea = new SmokingAreaDAO();
	
   	String rst = smokingArea.insertSmokingReport(jsonObj);
    out.println(rst);	
%>
