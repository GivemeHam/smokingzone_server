<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="DAO.BoardDAO"%>
<%@page import="org.json.simple.JSONArray"%>
<%
try{
	BoardDAO board = new BoardDAO();
	JSONArray list = board.selectBoardList();
	out.println(list.toString());
} catch(Exception e) {
}
%>