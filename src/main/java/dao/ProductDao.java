package dao;

import java.util.List;

import model.Product;

public interface ProductDao {
	public boolean addProduct(Product product);

	public boolean addProducts(List<Product> products);

	public boolean updateProduct(Product product);

	public boolean deleteProduct(String productID);

	public Product getProduct(int productId);

	public List<Product> getProducts(int skip, int limit);

	public List<Product> getProducts(String keyWord);

	public Product getProductByName(String productName);

	public List<Product> getProductNotSold();

	public int getTotalOfProducts();

	public List<Product> getProductsByCategoryId(int id);

	public List<Product> getProductsByCategoryName(String name);

	public List<Product> getProductsByBrand(int brandId);

	public List<Product> getProductsByModelYear(int year);
}
