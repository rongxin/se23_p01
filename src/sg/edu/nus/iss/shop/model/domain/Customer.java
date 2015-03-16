package sg.edu.nus.iss.shop.model.domain;

public abstract class Customer {
	
	private String id;
	public Customer(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (!this.getClass().equals(object.getClass())) {
			return false;
		}
		Customer customer = (Customer) object;
		return this.getId().equals(customer.getId());
	}
	
	abstract public boolean isFirstPurchase();
	abstract public Discount getMaxDiscount();

}
