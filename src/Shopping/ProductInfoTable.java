package Shopping;

import java.util.HashMap;
import java.util.Map;

public class ProductInfoTable {
	private static final Map<Product, String> brands;
	private static final Map<Product, String> categories = new HashMap<Product, String>();
	
	{
		brands = new HashMap<Product, String>();
	}
}
