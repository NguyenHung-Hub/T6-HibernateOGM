package dao.impl;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import dao.OrderDao;
import model.Order;
import util.HibernateUtil;

public class OrderImpl implements OrderDao {

	private OgmSessionFactory ogmSessionFactory;

	public OrderImpl() {
		ogmSessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public List<Order> getOrders(int skip, int limit) {
		OgmSession session = ogmSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			String query = "db.orders.aggregate([{'$skip':" + skip + "},{'$limit':" + limit + "}])";
			List<Order> orders = session.createNativeQuery(query, Order.class).getResultList();

			transaction.commit();

			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		session.close();
		return null;
	}

}
