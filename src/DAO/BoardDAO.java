package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BoardDAO {
	@SuppressWarnings("unchecked")
	public JSONArray selectBoardList() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray jsonArray = new JSONArray();

		try {
			String sql = "select * from board";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("no", rs.getString("board_no"));
				jsonObject.put("title", rs.getString("board_title"));
				jsonObject.put("ctnt", rs.getString("board_ctnt"));
				jsonObject.put("tag", rs.getString("board_tag"));
				jsonObject.put("reg_date", rs.getString("board_reg_date"));
				jsonObject.put("reg_user", rs.getString("board_reg_user"));
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
}
