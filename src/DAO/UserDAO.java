package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class UserDAO {
	public String insertLogin(JSONObject login_info) throws ClassNotFoundException, ParseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "success";
		if(existUser((login_info.get("user_info_no")).toString())){
			return "fail";	//이미 토큰값이 등록되어있으면
		}
		try {
			conn = DBConnection.getConnection();

			String sql = "insert INTO user_info(user_info_no, user_info_id)"
					+ "values (?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, (login_info.get("user_info_no")).toString());
			pstmt.setString(2, (login_info.get("user_info_id")).toString());

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
	
	public boolean existUser(String user_info_no) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from user_info WHERE user_info_no =" + user_info_no;

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
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
		return false;
	}
}
