package dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import dao.ProductDao;
import model.Category;
import model.Product;
import util.HibernateUtil;

public class ProductImpl implements ProductDao {

	private OgmSessionFactory ogmSessionFactory;

	public ProductImpl() {
		ogmSessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public boolean addProduct(Product product) {
		OgmSession session = ogmSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			session.save(product);

			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		session.close();
		return false;
	}
	
	@Override
	public boolean addProducts(List<Product> products) {
		OgmSession session = ogmSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			for (Iterator iterator = products.iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
				
				session.save(product);
			}

			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		session.close();
		return false;
	}

	@Override
	public boolean updateProduct(Product product) {
		OgmSession session = ogmSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			session.update(product);

			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		session.close();
		return false;
	}

	@Override
	public boolean deleteProduct(String productID) {
		OgmSession session = ogmSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			session.delete(session.find(Product.class, productID));

			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
		return false;
	}

	@Override
	public Product getProduct(int productId) {
		OgmSession session = ogmSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			Product product = session.find(Product.class, productId);

			transaction.commit();
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<Product> getProducts(int skip, int limit) {

		OgmSession session = ogmSessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();

			String query = "db.products.aggregate([{'$skip':" + skip + "},{'$limit':" + limit + "}])";
			List<Product> products = session.createNativeQuery(query, Product.class).getResultList();

			tr.commit();

			return products;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<Product> getProducts(String keyWord) {
		OgmSession session = ogmSessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();

			String query = "db.products.find({'$text':{'$search':'" + keyWord + "'}})";
			List<Product> products = session.createNativeQuery(query, Product.class).getResultList();

			tr.commit();

			return products;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public Product getProductByName(String productName) {
		OgmSession session = ogmSessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();

			String query = "db.products.find({'product_name':'" + productName + "'})";
			Product product = session.createNativeQuery(query, Product.class).getSingleResult();

			tr.commit();

			return product;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<Product> getProductNotSold() {
		OgmSession session = ogmSessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		try {
//			String query = "db.products.aggregate([{'$lookup':{'from':'orders', 'localField':'_id', 'foreignField':'order_items.product_id', 'as':'rs'}},{'$match':{'$expr':{'$eq':[0,{'$size':'$rs'}]}}},{'$unset':'rs'}])";
			String query = "db.products.aggregate([{ '$lookup': { 'from': 'orders', 'localField': '_id', 'foreignField': 'order_items.product_id', 'as': 'rs' } }, { '$match': { '$expr': { '$eq': [0, { '$size': '$rs' }] } } },{'$unset':'rs'},{'$skip':1}])";
			List<Product> products = session.createNativeQuery(query, Product.class).getResultList();
			transaction.commit();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<Product> getProductsByBrand(int brandId) {
		OgmSession session = ogmSessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		try {
			String query = "db.products.aggregate([{'$match':{'brand_id':" + brandId + "}}])";
			List<Product> products = session.createNativeQuery(query, Product.class).getResultList();
			transaction.commit();

			return products;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		session.close();
		return null;
	}

	@Override
	public int getTotalOfProducts() {
		OgmSession session = ogmSessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		try {
			String query = "db.products.aggregate([{'$group':{'_id':null, sum:{'$sum':1}}}])";
			Object[] object = (Object[]) session.createNativeQuery(query).getSingleResult();
			int total = (int) object[1];
			transaction.commit();
			return total;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		session.close();
		return 0;
	}

	@Override
	public List<Product> getProductsByCategoryId(int id) {
		OgmSession session = ogmSessionFactory.openSession();
		Transaction tr = session.beginTransaction();

		try {
			String query = "select pr from Product pr where category = :x";
			Category category = new Category();
			category.setId(id);

			List<Product> products = session.createQuery(query, Product.class).setParameter("x", category)
					.getResultList();
			tr.commit();

			return products;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}

		session.close();
		return null;
	}

	@Override
	public List<Product> getProductsByCategoryName(String name) {
		OgmSession session = ogmSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			String query = "db.categories.aggregate([{'$match':{'category_name': '" + name
					+ "' }},{'$lookup':{'from':'products', 'localField':'_id', 'foreignField':'category_id', 'as':'ds'}},{'$unwind':'$ds'},{'$replaceWith':'$ds'}])";
			List<Product> products = session.createNativeQuery(query, Product.class).getResultList();
			transaction.commit();

			return products;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		session.close();
		return null;
	}

	@Override
	public List<Product> getProductsByModelYear(int year) {
		OgmSession session = ogmSessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		try {
			String query = "select pr from Product pr where pr.modelYear = :y";
			List<Product> products = session.createQuery(query, Product.class).setParameter("y", year).getResultList();
			transaction.commit();

			return products;

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}
}
