package entity;

public class ProductInCart {
	private int id;
	private String name;
	private double price;
	private double subTotal;
	private int quantity;
	
	public ProductInCart(int id, String name, double price, double subTotal, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.subTotal = subTotal;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductInCart [id=" + id + ", name=" + name + ", price=" + price + ", subTotal=" + subTotal
				+ ", quantity=" + quantity + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
