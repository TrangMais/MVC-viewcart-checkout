package entity;

public class Comment {
	private String content;
	private int product_id;

	public Comment() {
	}
	
	public Comment(String content, int product_id) {
		super();
		this.content = content;
		this.product_id = product_id;
	}

	@Override
	public String toString() {
		return "Comment [content=" + content + ", product_id=" + product_id + "]";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	
}