package app;

import java.util.ArrayList;
import java.util.List;

import dao.OrderDao;
import dao.ProductDao;
import dao.StaffDao;
import dao.impl.OrderImpl;
import dao.impl.ProductImpl;
import dao.impl.StaffImpl;
import model.Product;
import model.Staff;

public class App {
	public static void main(String[] args) {

		StaffDao staffDao = new StaffImpl();
		ProductDao productDao = new ProductImpl();
		OrderDao orderDao = new OrderImpl();
		
		// ---------------------------------------
		
		List<Product> products= new ArrayList<Product>();
		Product p1 = new Product(2021, 2021, "product2021", 2000);
		Product p2 = new Product(2022, 2021, "product20213", 2900);
		products.add(p1);
		products.add(p2);
		
		boolean rs2 = productDao.addProducts(products);
		if (rs2) {
			System.out.println("them thanh cong " + products.size() + "san pham");
		}else {
			System.out.println("that bai");
		}
		
		// ---------------------------------------
//		orderDao.getOrders(2, 3).forEach(i->{
//			System.out.println(i);
//		});

		// ---------------------------------------
//		productDao.getProducts("Surly Wednesday").forEach(i -> {
//			System.out.println(i);
//		});
		// ---------------------------------------
//		productDao.getProductNotSold().forEach(i -> {
//			System.out.println(i);
//		});
		// ---------------------------------------
//		productDao.getProductsByModelYear(2017).forEach(i -> {
//			System.out.println(i);
//		});
		// ---------------------------------------
//		productDao.getProductsByBrand(9).forEach(i -> {
//			System.out.println(i);
//		});
		// ---------------------------------------
//		productDao.getProductsByCategoryName("Cruisers Bicycles").forEach(i -> {
//			System.out.println(i);
//		});
		// ---------------------------------------
//		productDao.getProductsByCategoryId(2).forEach(i -> {
//			System.out.println(i);
//		});
		// ---------------------------------------

//		int total = productDao.getTotalOfProducts();
//		System.out.println("Number of products: " + total);
//		
		// ---------------------------------------

//		System.out.println(productDao.getProduct(1));

		// ---------------------------------------

//		System.out.println(productDao.getProductByName("Trek Remedy 29 Carbon Frameset - 2016"));
		// ---------------------------------------

//		productDao.getProducts(1, 3).forEach(i -> {
//			System.out.println(i);
//		});

		// ---------------------------------------
//		Staff staff = new Staff(20212, "nguyen", "hung", "email@gmail.com", "0980000000", 1);
//		boolean rs = staffDao.addStaff(staff);
//		if (rs) {
//			System.out.println("thanh cong");
//		} else {
//			System.out.println("that bai");
//		}
	}
}
