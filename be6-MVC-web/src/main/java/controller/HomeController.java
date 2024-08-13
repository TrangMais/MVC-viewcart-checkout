package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CategoryRepository;
import model.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import entity.Product;

/**
 * Servlet implementation class HomeController
 */
// TASK of Controller: Handle Data and Forward Data
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// hardcode when we don't have DB of Products
		//ArrayList<Product> productList = new ArrayList<Product>();
		//productList.add(new Product(1, "Coffee", 5, "made in Vietnam"));
		//productList.add(new Product(2, "Pho", 10, "made in Vietnam"));
		//productList.add(new Product(3, "Che", 3, "made in Vietnam"));
		//ArrayList<Product> products = new ArrayList<Product>();
		//products.add(new Product(1, "Coffee", 5, "made in Vietnam"));
		//products.add(new Product(2, "Pho", 10, "made in Vietnam"));
		//products.add(new Product(3, "Che", 3, "made in Vietnam"));
			
		//HttpSession session = request.getSession();
		//session.setAttribute("productList", productList);

		//response.sendRedirect("index.jsp");
				
		//request.setAttribute("productList", products);
		//RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		//rd.forward(request, response);
    	
		
    		// Belongs to ProductRepository
			/*
			 * Connection conn = DBUtil.getConnection(); String query =
			 * "SELECT * FROM `products`"; PreparedStatement ps =
			 * conn.prepareStatement(query); ResultSet rs = ps.executeQuery();
			 * 
			 * // Create an empty ArrayList firstly for inputting data from DB later
			 * ArrayList<Product> products = new ArrayList<Product>();
			 * 
			 * while(rs.next()) { Product product = new Product(rs.getInt("id"),
			 * rs.getString("name"), rs.getDouble("price"), rs.getString("description"));
			 * products.add(product); }
			 */
    	String categoryId = request.getParameter("categoryId");
    	
    	ProductRepository productRepo = new ProductRepository();
		CategoryRepository categoryRepo = new CategoryRepository();
    	
		try {
    		//ArrayList<Product> products = productRepo.getAllProduct();
    		List<Product> products = null;
    		if(categoryId != null) {
    			products = productRepo.getAllProductByCategoryId(Integer.parseInt(categoryId));
    		}else {
    			products = productRepo.getAllProduct();
    		}
    		//If we want only show category with is_show = 1
    		ArrayList<Category> categories = categoryRepo.getAll(true);
    		//ArrayList<Category> categories = categoryRepo.getAll();
    		
			request.setAttribute("categoryList", categories);
			request.setAttribute("productList", products);
			
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
