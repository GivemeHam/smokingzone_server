<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="DAO.SmokingAreaDAO"%>
<%
	Class.forName("com.mysql.jdbc.Driver");

	request.setCharacterEncoding("UTF-8");
	String get_param = request.getParameter("currentlocation");
	JSONParser parser = new JSONParser();
	Object obj = parser.parse(get_param);
	JSONObject jsonObj = (JSONObject) obj;

	SmokingAreaDAO smokingDAO = new SmokingAreaDAO();
	String NearSmokingArea_info = smokingDAO.selectMinDistanceLocation(jsonObj.get("lat").toString(), jsonObj.get("lng").toString()).toString();
	out.println(NearSmokingArea_info);
	//더미 값 일단 출력
	//	out.println("{\"lat\":37.551293,\"lng\":127.072949}");
%>
