package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

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
	
	public String insertBoard(JSONObject board) throws ClassNotFoundException, ParseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "success";

		try {
			conn = DBConnection.getConnection();

				String sql = "insert INTO board(board_title, board_ctnt, board_tag, board_reg_date, board_reg_user)"
						+ "values (?, ?, ?, NOW(), ?)";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, (board.get("title")).toString());
				pstmt.setString(2, (board.get("ctnt")).toString());
				pstmt.setString(3, (board.get("tag")).toString());
				//pstmt.setString(4, (board.get("reg_date")).toString());
				pstmt.setString(4, (board.get("reg_user")).toString());
		
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
	
	@SuppressWarnings("unchecked")
	public JSONArray selectBoardReivew(String board_no) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray jsonArray = new JSONArray();

		try {
			String sql = "select * from board_review WHERE board_review_board_no = " + board_no;

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("review_no", rs.getString("board_review_review_no"));
				jsonObject.put("reg_user", rs.getString("board_review_reg_user"));
				jsonObject.put("reg_date", rs.getString("board_review_reg_date"));
				jsonObject.put("ctnt", rs.getString("board_review_ctnt"));
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
	
	public String insertBoardReview(JSONObject boardReview) throws ClassNotFoundException, ParseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "success";

		System.out.println(boardReview);
		try {
			conn = DBConnection.getConnection();

			String sql = "insert INTO board_review(board_review_board_no, board_review_reg_user, board_review_reg_date, board_review_ctnt)"
					+ "values (?, ?, NOW(), ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, Integer.parseInt((boardReview.get("board_area_no")).toString()));
			pstmt.setString(2, (boardReview.get("board_review_reg_user")).toString());
			pstmt.setString(3, (boardReview.get("board_review_ctnt")).toString());
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


}
