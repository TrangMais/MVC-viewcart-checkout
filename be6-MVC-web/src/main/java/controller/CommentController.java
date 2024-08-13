package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CommentRepository;

import java.io.IOException;

import entity.Comment;

/**
 * Servlet implementation class CommentController
 */
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String productId = request.getParameter("productId");
			String content = request.getParameter("content");
			
			Comment comment = new Comment(content, Integer.parseInt(productId));
			CommentRepository commentRepo = new CommentRepository();
			
			commentRepo.save(comment);
			
			//After comment, we have to see the page of this Product which we have commented. PART after "?" is PARAMETER
			response.sendRedirect("Product?id=" + productId);
			
		}catch (Exception e) {
		// Exception is everything including catch and uncatch
		// catch (SQLException | NumberFormatException e) {
		// System.out.println("Error happened " + e.getMessage());
			System.out.println("LoginController doPost Eror happened " + e.getMessage());
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
	}
}
