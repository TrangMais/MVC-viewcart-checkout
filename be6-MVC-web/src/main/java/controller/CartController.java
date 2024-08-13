package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CategoryRepository;
import model.ProductRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import entity.Cart;
import entity.Category;
import entity.Product;
import entity.ProductInCart;

/**
 * Servlet implementation class CartController
 */
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String command = request.getParameter("command");
			int productId = 0;
			if(command != null && command.equals("ADD_TO_CART")) {
				productId = Integer.parseInt(request.getParameter("productId"));
				ProductRepository productRepo = new ProductRepository();
				Product product = productRepo.getProductById(productId);
				
				HttpSession session = request.getSession();
				Map<Integer, Product> cart = (Map<Integer, Product>) session.getAttribute("cart");
				
				if(cart == null) {
					cart = new HashMap<Integer, Product>();
				}
				cart.put(product.getId(), product);
				session.setAttribute("cart", cart);
				request.setAttribute("product", product);
				
				//go back to previous page
				response.sendRedirect("Home?command=DETAILS&productId=" + productId);
				
			}else if (command != null && command.equals("VIEW_CART")) {
				response.sendRedirect("cart.jsp");
				
			}else if (command != null && command.equals("DELETE")) {
				productId = Integer.parseInt(request.getParameter("productId"));
				HttpSession session = request.getSession();
				Map<Integer, Product> cart = (Map<Integer, Product>) session.getAttribute("cart");
				cart.remove(productId);
				response.sendRedirect("cart.jsp");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		ProductRepository productRepo = new ProductRepository();
//		CategoryRepository categoryRepo = new CategoryRepository();
//		List<Category> categories;
//		try {
//			String command = request.getParameter("command");
//			if (command == null) {
//				command = "DEFAULT";
//			}
//			switch (command) {
//			
//			case "ADD_TO_CART": {
//				addToCart(request, response);
//				break;
//			}
//			case "VIEW_CART": {
//				System.out.println("Comand received: " + command);
//
//				categories = categoryRepo.showCategories();
//				System.out.println("Number of categories: " + categories.size());
//				RequestDispatcher rd = request.getRequestDispatcher("/view-cart.jsp");
//				
//				request.setAttribute("categories", categories);
//				rd.forward(request, response);
//				break;
//			}
//			case "REMOVE": {
//				removeCartItems(request, response);
//				break;
//			}			
//			default:
//				break;
//			}
//		} catch (NumberFormatException | IOException | SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private void removeCartItems(HttpServletRequest request, HttpServletResponse response) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	private void addToCart(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, NumberFormatException, SQLException {
//		ProductRepository productRepo = new ProductRepository();
//		CategoryRepository categoryRepo = new CategoryRepository();
//		List<Category> categories;
//		
//		String productId = request.getParameter("productId");
//		String quantity = request.getParameter("quantity");
//
//		Product product = productRepo.getProductById(productId);
//		ProductInCart productInCart = new ProductInCart(product.getId(), product.getName(),
//							product.getPrice(), 0, Integer.parseInt(quantity));
//		Cart cart;
//
//		HttpSession session = request.getSession();
//
//		if (session.getAttribute("cart") == null) {
//			cart = new Cart();
//			cart.setItems(new HashSet<ProductInCart>());
//
//		} else {
//			cart = (Cart) session.getAttribute("cart");
//		}
//
//		if (cart.getItems().contains(productInCart)) {
//			for (Category item : cart.getItems()) {
//				if (item.getId() == productInCart.getId()) {
//					int currentQuantity = item.getQuantity();
//					productInCart.setQuantity(currentQuantity + Integer.parseInt(quantity));
//					productInCart.setSubTotal(productInCart.getPrice() * productInCart.getQuantity());
//					System.out.println("SubTotal: " + productInCart.getSubTotal());
//				}
//
//			}
//
//			cart.getItems().remove(productInCart);
//
//			cart.getItems().add(productInCart);
//
//		} else {
//			productInCart.setSubTotal(productInCart.getPrice() * productInCart.getQuantity());
//			cart.getItems().add(productInCart);
//		}
//		
//		double totalPrice = 0;
//		// calculate total
//		for (ProductInCart i : cart.getItems()) {
//			totalPrice += Math.round(i.getPrice() * i.getQuantity() * 100) / 100;
//			System.out.println("total: " + totalPrice);
//			cart.setTotal(totalPrice);
//		}
//		cart.setTax(Math.ceil(totalPrice * 10.0) / 100.0);
//		
//		cart.setTotalWithTax(cart.getTotal() + cart.getTax());
//
//		session.setAttribute("cart", cart);
//
//		response.sendRedirect("ProductDetail?productId=" + productId);
//
//	}
//		
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Click add to cart, the REQUEST will come to this Controller. Then system will take the productId which customer want to "ADD TO CART"
			String productId = request.getParameter("productId");
			// Upgrade 1: If we want to show the number of Product TYPE, not Product AMOUNT
			ProductRepository productRepo = new ProductRepository();
			Product product = productRepo.getProductById(Integer.parseInt(productId));
			
			// Go to session for check if session has CART (cabin) or not
			HttpSession session = request.getSession();
			// Upgrade 1: If we want to show the number of Product TYPE, not Product AMOUNT => compare the productId with the productId which is already in CART
			//ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
			// Upgrade 2: Same requirement, but change Arraylist => HashMap
			Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
			
			
			//We have to compare if the cart.get(Integer.parseInt(productId)) and the quantity which is already in cart
//			if(cart.get(Integer.parseInt(productId)) == null) {
//				quantity = 0;
//			}else {
//				quantity = cart.get(Integer.parseInt(productId)) + 1;
//			}
				
			cart.put(Integer.parseInt(productId), 1);

			// If the cart don't have in session, then create a new CART
//			if (cart == null) {
//				cart = new ArrayList<Product>();
//			}
			
			// To achieve the Upgrade 1: We have to compare the productId with the productId which is already in CART
//			boolean exist = false;
//			for (Product nextProduct : cart) {
//				if(nextProduct.getId() == Integer.parseInt(productId)) {
//					exist = true;
//					break;
//				}
//			}
			
			//Having the productId, then go to SB and take the product out => move up because of UPGRADE 1
			//ProductRepository productRepo = new ProductRepository();
			//Product product = productRepo.getById(Integer.parseInt(productId));
			
			//Input the product into cart => move up because of UPGRADE 1 for comparing
			//cart.add(product); 
//			if(exist == false) {
//				cart.add(product);
//			}
			
			//Update the cart in session with the updated number of product
			session.setAttribute("cart", cart);
			response.sendRedirect("Home");
			
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}

}
