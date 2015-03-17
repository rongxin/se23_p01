package sg.edu.nus.iss.shop.model.nondomain;

import java.util.Date;

 

public class TransactionRecord {
	private int id;
	private String productId;
	private String customerId;
	private int quantity;
	private Date transactionDate;
	
	public TransactionRecord()
	{}
	public TransactionRecord(int id,String productId,String customerId,int quantity,Date transactionDate)
	{
		this.id = id;
		this.productId = productId;
		this.customerId = customerId;
		this.quantity = quantity;
		this.transactionDate = transactionDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}
