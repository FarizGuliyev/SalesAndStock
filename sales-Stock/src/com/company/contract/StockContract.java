package com.company.contract;

public class StockContract {
	private int id;
	private int workerId;
	private int productId;
	private String date;
	private int amount;

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getWorkerId() {
		return workerId;
	}



	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}



	public int getProductId() {
		return productId;
	}



	public void setProductId(int productId) {
		this.productId = productId;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id + " " + workerId + " " + productId + " " + date + " " + amount;
	}
}
