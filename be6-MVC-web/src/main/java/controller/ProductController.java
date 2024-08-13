package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CommentRepository;
import model.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;

import entity.Comment;
import entity.Product;

/**
 * Servlet implementation class ProductController
 */
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String productId = request.getParameter("id");
			
			//get PRODUCT from DB
			ProductRepository productRepo = new ProductRepository();
			//When customer submit form -> comment is added in db and reload the page with customers comment in the end of the page
			CommentRepository commentRepo = new CommentRepository();
			
			Product product = productRepo.getProductById(Integer.parseInt(productId));
			//When customer submit form -> comment is added in db and reload the page with customers comment in the end of the page
			ArrayList<Comment> comments = commentRepo.getByProductId(Integer.parseInt(productId));
			
			//pass product to VIEW
			request.setAttribute("product", product);
			//When customer submit form -> comment is added in db and reload the page with customers comment in the end of the page
			request.setAttribute("comments", comments);
			
			RequestDispatcher rd = request.getRequestDispatcher("productDetail.jsp");
			rd.forward(request, response);
			
		}catch (Exception e) {
			System.out.println("System Error when Showing Product Details with error code 1234, error Message " + e.getMessage());
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
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
