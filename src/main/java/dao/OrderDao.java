package dao;

import java.util.List;

import model.Order;

public interface OrderDao {
	public List<Order> getOrders(int skip, int limit);
}
