package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import util.DBUtil;

public class CategoryRepository {
	public List<Category> showCategories () throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Category> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();

			String query = "SELECT * FROM `categories`";

			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {
				Category category = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("priority"));
				list.add(category);
			}
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	return null;
	}
	
	public ArrayList<Category> getAll(boolean isShow) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Category> list = null;
		try {
			//Connection conn = DBUtil.getConnection();
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM `categories` WHERE is_show = ?";

			//PreparedStatement ps = conn.prepareStatement(query);
			ps = conn.prepareStatement(query);
			ps.setBoolean(1, isShow);

			//ResultSet rs = ps.executeQuery();
			rs = ps.executeQuery();
			list = new ArrayList<Category>();

			while (rs.next()) {
				Category category = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("priority"));
				list.add(category);
			}
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, ps, rs);
		}
		
	return null;
	}
	
	private void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}

}
