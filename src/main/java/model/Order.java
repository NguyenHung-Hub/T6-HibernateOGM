package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	private int id;
	@Column(name = "order_date")
	private Date orderDate;
	@Column(name = "order_status")
	private int orderStatus;
	@Column(name = "order_total")
	private double orderTotal;
	@Column(name = "required_date")
	private Date requiredDate;
	@Column(name = "shipped_date")
	private Date shippedDate;

	@ElementCollection
	private List<OrderItem> orderItems;

	@ManyToOne
	@JoinColumn(name = "staff_id")
	private Staff staff;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Order() {
		this.orderItems = new ArrayList<OrderItem>();
	}

	public Order(int id, int orderStatus, Date requiredDate, Date shippedDate) {
		super();
		this.id = id;
		this.orderStatus = orderStatus;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;

		this.orderDate = new Date();
		this.orderTotal = 0.0;
		this.orderItems = new ArrayList<OrderItem>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

//	public double getOrderTotal() {
//		return orderTotal;
//	}
//
//	public void setOrderTotal(double orderTotal) {
//		this.orderTotal = orderTotal;
//	}

//	public Date getRequiredDate() {
//		return requiredDate;
//	}
//
//	public void setRequiredDate(Date requiredDate) {
//		this.requiredDate = requiredDate;
//	}
//
//	public Date getShippedDate() {
//		return shippedDate;
//	}
//
//	public void setShippedDate(Date shippedDate) {
//		this.shippedDate = shippedDate;
//	}
//
//	public List<OrderItem> getOrderItems() {
//		return orderItems;
//	}
//
//	public void setOrderItems(List<OrderItem> orderItems) {
//		this.orderItems = orderItems;
//	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus + ", orderTotal="
				+ orderTotal + ", requiredDate=" + requiredDate + ", shippedDate=" + shippedDate + ", orderItems="
				+ orderItems + ", staff=" + staff + ", customer=" + customer + "]";
	}

}
