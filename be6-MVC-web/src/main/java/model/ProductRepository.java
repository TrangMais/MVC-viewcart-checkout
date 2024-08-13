package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;
import util.DBUtil;

// The TASK of this class: Handling everything realated to Database
// Replace all code line from HomeController
// Repository = Model: Work with Data for example, Connecting with DB server, preparing some functions for taking data, manipulating data
// such as delete, insert, update data
public class ProductRepository {

	public List<Product> getAllProduct() {
		try {
			Connection conn = DBUtil.getConnection();
			String query = "SELECT * FROM `products`";

			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			ArrayList<Product> products = new ArrayList<Product>();

			while (rs.next()) {
				Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
						rs.getString("description"), rs.getInt("category_id"));
				products.add(product);
			}
			return products;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Requirement: After click the button "More", SHOW product details page -> show
	// name, price , description of product
	public ArrayList<Product> getAllProductByCategoryId(int cateId) {
		try {
			Connection conn = DBUtil.getConnection();
			String query = "SELECT * FROM `products` WHERE category_id = ?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, cateId);

			ResultSet rs = ps.executeQuery();

			ArrayList<Product> products = new ArrayList<Product>();

			while (rs.next()) {
				Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
						rs.getString("description"), rs.getInt("category_id"));
				products.add(product);
			}
			return products;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Product getProductById(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product object = null;

		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM `products` WHERE id = ?";

			ps = conn.prepareStatement(query);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				object = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
						rs.getString("description"), rs.getInt("category_id"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(conn, ps, rs);
			
		}
			return object;
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
