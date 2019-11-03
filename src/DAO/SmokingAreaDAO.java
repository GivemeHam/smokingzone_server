package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class SmokingAreaDAO {
	
	public String insertSmokingArea(JSONObject smokingArea) throws ClassNotFoundException, ParseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject jsonObject = new JSONObject();
		String rst = "success";

		try {
			conn = DBConnection.getConnection();

				String sql = "insert INTO smoking_area(smoking_area_name, smoking_area_lat, smoking_area_lng, smoking_area_reg_date, smoking_area_reg_user, smoking_area_point, smoking_area_report, smoking_area_img_src, smoking_area_roof, smoking_area_vtl, smoking_area_bench, smoking_area_desc, smoking_area_type)"
						+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, (smokingArea.get("smoking_area_name")).toString());
				pstmt.setString(2, (smokingArea.get("smoking_area_lat")).toString());
				pstmt.setString(3, (smokingArea.get("smoking_area_lng")).toString());
				pstmt.setString(4, (smokingArea.get("smoking_area_reg_date")).toString());
				pstmt.setString(5, (smokingArea.get("smoking_area_reg_user")).toString());
				pstmt.setString(6, (smokingArea.get("smoking_area_point")).toString());
				pstmt.setString(7, (smokingArea.get("smoking_area_report")).toString());
				pstmt.setString(8, (smokingArea.get("smoking_area_roof")).toString());
				pstmt.setString(9, (smokingArea.get("smoking_area_vtl")).toString());
				pstmt.setString(10, (smokingArea.get("smoking_area_bench")).toString());
				pstmt.setString(11, (smokingArea.get("smoking_area_desc")).toString());
				pstmt.setString(12, (smokingArea.get("smoking_area_type")).toString());

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
				jsonObject.put("img_src", rs.getString("smoking_area_img_src"));
				jsonObject.put("roof", rs.getString("smoking_area_roof"));
				jsonObject.put("vtl", rs.getString("smoking_area_vtl"));
				jsonObject.put("bench", rs.getString("smoking_area_bench"));
				jsonObject.put("desc", rs.getString("smoking_area_desc"));
				jsonObject.put("type", rs.getString("smoking_area_type"));
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
				rs.getString("smoking_area_no");
				rs.getString("smoking_area_lat");
				rs.getString("smoking_area_lng");
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

}
