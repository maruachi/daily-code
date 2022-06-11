package Shopping;

public enum Coupon {
	C001(){
		@Override
		int discount(Product product, int price) {
			String brand = null;
			
			if(brand.equals("B001")) {
				return 5000;
			}
			else if (brand.equals("B002")) {
				return 10000;
			}
			else if (brand.equals("B003")) {
				return 7000;
			}
			return 0;
		}
	},
	C002(){
		@Override
		int discount(Product product, int price) {
			String brand = null;
			
			if(brand.equals("B001") || brand.equals("B002")) {
				if(price/2 > 15000) {
					return 15000;
				}
				return price/2;
			}
			else if (brand.equals("B003")) {
				return price/4;
			}
			return 0;
		}
	},
	C003(){

		@Override
		int discount(Product product, int price) {
			if(false) {
				return 3000;
			}
			else {
				return 1500;
			}
		}
		
	};
	
	abstract int discount(Product product, int price);
}
