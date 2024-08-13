package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Comment;
import util.DBUtil;

public class CommentRepository {

	public void save(Comment comment) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			String query = "INSERT INTO comments(content, product_id) VALUES(?,?)";
			
			ps = conn.prepareStatement(query);
			
			ps.setString(1, comment.getContent());
			ps.setInt(2, comment.getProduct_id());
			
			ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			closeSmall(conn, ps);
		}	
	}
	
	//When customer submit form -> comment is added in db and reload the page with customers comment in the end of the page
	public ArrayList<Comment> getByProductId(int productId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Comment> list = null;
		try {
			//Connection conn = DBUtil.getConnection();
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM `comments` WHERE product_id = ?";

			//PreparedStatement ps = conn.prepareStatement(query);
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);

			//ResultSet rs = ps.executeQuery();
			rs = ps.executeQuery();
			list = new ArrayList<Comment>();

			while (rs.next()) {
				Comment commentItem = new Comment(rs.getString("content"), rs.getInt("product_id"));
				list.add(commentItem);
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
	
	private void closeSmall(Connection conn, PreparedStatement ps) {
		try {
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
