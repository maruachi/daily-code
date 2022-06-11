package Shopping;

import java.util.Set;

public class Product {
	private final String code;
	private final int price;
	
	public Product(String code, int price) {
		this.code = code;
		this.price = price;
	}
	
	public static Product[] defaultProducts() {
		Product[] products = null;
		
		return products;
	}
	
	public Set getBrands(String brand) {
		
		return null;
	}
	
	public Set getCategories(String category) {
		
		return null;
	}
}
