<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="DAO.SmokingAreaDAO"%>
<%@page import="org.json.simple.JSONArray"%>
<%
try{
	Class.forName("com.mysql.jdbc.Driver");

 	SmokingAreaDAO smokingArea = new SmokingAreaDAO();
	JSONArray list = smokingArea.selectSmokingAreaList();
	out.println(list.toString());
} catch (ClassNotFoundException e) {
} catch(Exception e) {
}
%>


