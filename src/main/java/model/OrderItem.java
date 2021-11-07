package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Embeddable
@Table(name = "order_items")
public class OrderItem {
	private int quantity;
	private double discount;
	@Column(name = "list_price")
	private double price;
	@Column(name = "line_total")
	private double lineTotal;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public OrderItem() {
	}

	public OrderItem(int quantity, double discount, double price, Product product) {
		super();
		this.quantity = quantity;
		this.discount = discount;
		this.price = price;
		
		this.product = product;
		
		this.lineTotal = quantity * price * (1 - discount);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getLineTotal() {
		return lineTotal;
	}

	public void setLineTotal(double lineTotal) {
		this.lineTotal = lineTotal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "OrderItem [quantity=" + quantity + ", discount=" + discount + ", price=" + price + ", lineTotal="
				+ lineTotal + ", product=" + product + "]";
	}

}
