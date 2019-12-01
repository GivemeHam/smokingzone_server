package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import Util.Util;

public class SmokingAreaDAO {

	public String Log(String data) {
		System.out.println("data : " + data);
		return data;
	}
	Util util = new Util();

	public String insertSmokingArea(JSONObject smokingArea) throws ClassNotFoundException, ParseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject jsonObject = new JSONObject();
		String rst = "success";
		System.out.println("smokingArea : " + smokingArea);

		try {
			conn = DBConnection.getConnection();

			String sql = "insert INTO smoking_area(smoking_area_name, smoking_area_lat, smoking_area_lng, smoking_area_reg_date, smoking_area_reg_user, smoking_area_point, smoking_area_report, smoking_area_roof, smoking_area_vtl, smoking_area_bench, smoking_area_desc, smoking_area_type, smoking_area_url)"
					+ "values (?, ?, ?, NOW(), ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, (smokingArea.get("smoking_area_name")).toString());
			pstmt.setString(2, (smokingArea.get("smoking_area_lat")).toString());
			pstmt.setString(3, (smokingArea.get("smoking_area_lng")).toString());
			pstmt.setString(4, (smokingArea.get("smoking_area_reg_user")).toString());
			pstmt.setString(5, (smokingArea.get("smoking_area_point")).toString());
			pstmt.setString(6, (smokingArea.get("smoking_area_report")).toString());
			pstmt.setString(7, (smokingArea.get("smoking_area_roof")).toString());
			pstmt.setString(8, (smokingArea.get("smoking_area_vtl")).toString());
			pstmt.setString(9, (smokingArea.get("smoking_area_bench")).toString());
			pstmt.setString(10, (smokingArea.get("smoking_area_desc")).toString());
			pstmt.setString(11, (smokingArea.get("smoking_area_type")).toString());
			pstmt.setString(12, (smokingArea.get("smoking_area_url")).toString());

			pstmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
			rst = "fail";
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				rst = "fail";
			}
		}
		return rst;
	}

	@SuppressWarnings("unchecked")
	public JSONArray selectSmokingAreaList() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray jsonArray = new JSONArray();

		try {
			String sql = "select * from smoking_area";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("no", rs.getString("smoking_area_no"));
				jsonObject.put("name", rs.getString("smoking_area_name"));
				jsonObject.put("lat", rs.getString("smoking_area_lat"));
				jsonObject.put("lng", rs.getString("smoking_area_lng"));
				jsonObject.put("reg_date", rs.getString("smoking_area_reg_date"));
				jsonObject.put("reg_user", rs.getString("smoking_area_reg_user"));
				jsonObject.put("point", rs.getString("smoking_area_point"));
				jsonObject.put("report", rs.getString("smoking_area_report"));
				jsonObject.put("roof", rs.getString("smoking_area_roof"));
				jsonObject.put("vtl", rs.getString("smoking_area_vtl"));
				jsonObject.put("bench", rs.getString("smoking_area_bench"));
				jsonObject.put("desc", rs.getString("smoking_area_desc"));
				jsonObject.put("type", rs.getString("smoking_area_type"));
				jsonObject.put("img_url", rs.getString("smoking_area_url"));
				jsonArray.add(jsonObject);
				jsonObject = null;

			}
		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return jsonArray;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray selectSmokingAreaReivew(String smoking_no) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray jsonArray = new JSONArray();

		try {
			String sql = "select * from smoking_review WHERE smoking_review_smoking_area_no = " + smoking_no;

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("review_no", rs.getString("smoking_review_review_no"));
				String user_id = getUserId(rs.getString("smoking_review_reg_user"));
				jsonObject.put("reg_user", user_id);
				jsonObject.put("reg_date", rs.getString("smoking_review_reg_date"));
				jsonObject.put("ctnt", rs.getString("smoking_review_ctnt"));
				jsonObject.put("point", rs.getString("smoking_review_point"));
				jsonArray.add(jsonObject);
				jsonObject = null;

			}
		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return jsonArray;
	}
	
	@SuppressWarnings("unchecked")
	public String getUserId(String user_no) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "알수없음";

		try {
			String sql = "select user_id from user_info WHERE user_info_no = " + user_no;

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rst = rs.getString("user_id");
			}
		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return rst;
	}



	@SuppressWarnings("unchecked")
	public JSONArray selectSmokingAreaLocation() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray jsonArray = new JSONArray();

		try {
			String sql = "select smoking_area_no, smoking_area_lat, smoking_area_lng from smoking_area";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("smoking_area_no", rs.getString("smoking_area_no"));
				jsonObject.put("smoking_area_lat", rs.getString("smoking_area_lat"));
				jsonObject.put("smoking_area_lng", rs.getString("smoking_area_lng"));
				jsonArray.add(jsonObject);
				jsonObject = null;
			}
		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return jsonArray;
	}

	@SuppressWarnings("unchecked")
	public JSONObject selectMinDistanceLocation(String s_lat, String s_lng) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject jsonObject = new JSONObject();
		double lat = Double.parseDouble(s_lat);
		double lng = Double.parseDouble(s_lng);

		JSONArray smokingAreaLocations = selectSmokingAreaLocation();
		int MinDistanceSmokingAreaNo = MinDistanceLocation(smokingAreaLocations, lat, lng);
		try {
			String sql = "select smoking_area_no, smoking_area_lat, smoking_area_lng from smoking_area WHERE smoking_area_no ="
					+ MinDistanceSmokingAreaNo;

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				jsonObject.put("smoking_area_no", rs.getString("smoking_area_no"));
				jsonObject.put("smoking_area_lat", rs.getString("smoking_area_lat"));
				jsonObject.put("smoking_area_lng", rs.getString("smoking_area_lng"));
			}
		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("test : " + jsonObject);
		return jsonObject;
	}

	public int MinDistanceLocation(JSONArray jsonArray, double lat, double lng) {
		double min = 999999;
		int min_no = 0;

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			
			//System.out.println(jsonObject.get("smoking_area_no").toString() + " : " + jsonObject.get("smoking_area_lat").toString() + " / " + jsonObject.get("smoking_area_lng").toString());

			double distance = util.distance(lat, lng, Double.parseDouble(jsonObject.get("smoking_area_lat").toString()),
					Double.parseDouble(jsonObject.get("smoking_area_lng").toString()), "kilometer");

			if (min > distance) {
				min = distance;
				//System.out.println("distance : " + distance);
				min_no = Integer.parseInt(jsonObject.get("smoking_area_no").toString());
			}
		}
		return min_no;
	}
	public boolean check_overlap_review(String user, String area_no) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from smoking_review where smoking_review_smoking_area_no= "
					+ area_no + " AND smoking_review_reg_user = \"" + user +"\"";
			System.out.println("sql : " + sql);
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				//값 존재
				return true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return false;
	}
	public String insertSmokingReview(JSONObject smokingReview) throws ClassNotFoundException, ParseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "success";

		if(check_overlap_review((smokingReview.get("smoking_review_reg_user")).toString(), (smokingReview.get("smoking_area_no")).toString())){
			return "overlap";	//이미 등록한 리뷰가 있을경우
		}
		try {
			conn = DBConnection.getConnection();

			String sql = "insert INTO smoking_review(smoking_review_smoking_area_no, smoking_review_reg_user, smoking_review_reg_date, smoking_review_ctnt, smoking_review_point)"
					+ "values (?, ?, NOW(), ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, Integer.parseInt(smokingReview.get("smoking_area_no").toString()));
			pstmt.setString(2, (smokingReview.get("smoking_review_reg_user")).toString());
			pstmt.setString(3, (smokingReview.get("smoking_review_ctnt")).toString());
			pstmt.setString(4, (smokingReview.get("smoking_review_point")).toString());
			pstmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
			rst = "fail";
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				rst = "fail";
			}
		}
		updatePoint(smokingReview.get("smoking_area_no").toString());
		return rst;
	}

	public String updatePoint(String smokingArea_no) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "success";

		try {
			conn = DBConnection.getConnection();
			String smokingArea_point = selectSmokingAreaPoint(smokingArea_no);
			String sql = "UPDATE smoking_area SET smoking_area_point = ? WHERE smoking_area_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, smokingArea_point);
			pstmt.setInt(2, Integer.parseInt(smokingArea_no));
			pstmt.executeUpdate();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			rst = "fail";
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				rst = "fail";
			}
		}

		return rst;
	}

	public String selectSmokingAreaPoint(String smokingArea_no) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "";

		try {
			String sql = "select avg(smoking_review_point) as smoking_area_point  from smoking_review where smoking_review_smoking_area_no = "
					+ smokingArea_no;
			System.out.println("sql : " + sql);
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rst = rs.getString("smoking_area_point");

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("rst point : " + rst);
		return rst;
	}

	public int selectReport_check(String smokingArea_no) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "";

		try {
			String sql = "select smoking_area_report from smoking_area where smoking_area_no = "
					+ smokingArea_no;
			System.out.println("sql : " + sql);
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rst = rs.getString("smoking_area_report");

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("rst point : " + rst);
		return Integer.parseInt(rst);
	}
	
	public boolean check_overlap_report(String report_user, String area_no) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from report where report_smoking_area_no = "
					+ area_no + " AND report_user = " + report_user;
			System.out.println("sql : " + sql);
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				//값 존재
				return true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return false;
	}
	
	public String insertSmokingReport(JSONObject smokingReport) throws ClassNotFoundException, ParseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "success";

		if(check_overlap_report((smokingReport.get("report_user")).toString(), (smokingReport.get("report_smoking_area_no")).toString())){
			return "overlap";	//이미 등록한 신고가 있을경우
		}
		try {
			conn = DBConnection.getConnection();

			String sql = "insert INTO report(report_title, report_ctnt, report_user, report_smoking_area_no, report_reg_date)"
					+ "values (?, ?, ?, ?, NOW())";s

			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, (smokingReport.get("report_title")).toString());
			pstmt.setString(2, (smokingReport.get("report_ctnt")).toString());
			pstmt.setString(3, (smokingReport.get("report_user")).toString());
			pstmt.setInt(4, Integer.parseInt((smokingReport.get("report_smoking_area_no")).toString()));
			pstmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
			rst = sqle.getMessage();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				rst = e.getMessage();
			}
		}
		updateReport(smokingReport.get("report_smoking_area_no").toString());
		
		
		//신고수가 10이상이면 타입변경
		if(selectReport_check(smokingReport.get("report_smoking_area_no").toString())>=10) {
			updateReport_type(smokingReport.get("report_smoking_area_no").toString());
			
		}
		return rst;
	}
	
	public String updateReport_type(String smokingArea_no) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "success";

		try {
			conn = DBConnection.getConnection();
			String sql = "UPDATE smoking_area SET smoking_area_type = \"10\" WHERE smoking_area_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, smokingArea_no);
			pstmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
			rst = "fail";
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				rst = "fail";
			}
		}

		return rst;
	}

	public String updateReport(String smokingArea_no) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "success";

		String count = selectSmokingAreaReport(smokingArea_no);
		try {
			conn = DBConnection.getConnection();
			String sql = "UPDATE smoking_area SET smoking_area_report = ? WHERE smoking_area_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, count);
			pstmt.setString(2, smokingArea_no);
			pstmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
			rst = sqle.getMessage();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				rst = "fail";
			}
		}

		return rst;
	}

	public String selectSmokingAreaReport(String smokingArea_no) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "";

		try {
			String sql = "select count(report_smoking_area_no) as smoking_area_cnt  from report where report_smoking_area_no ="
					+ smokingArea_no;
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rst = rs.getString("smoking_area_cnt");
			}
		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return rst;
	}

}
