package Shopping;

public enum Coupon {
	C001{
		public int use(Product product) {
			return 0;
		}
	},
	C002{
		public int use(Product product) {
			return 0;
		}
	},
	C003{
		public int use(Product product) {
			return 0;
		}
	};
	
	public abstract int use(Product product);
}
