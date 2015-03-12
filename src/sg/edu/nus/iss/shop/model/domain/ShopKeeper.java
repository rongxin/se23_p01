package sg.edu.nus.iss.shop.model.domain;

public class ShopKeeper {
	
	private String name;
	private String password;
	
	public ShopKeeper(String name, String password) {
		this.setName(name);
		this.setPassword(password);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
