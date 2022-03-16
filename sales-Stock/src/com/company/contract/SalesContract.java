package com.company.contract;

public class SalesContract {
	private int id;
	private int costumerId;
	private int workerId;
	private int productId;
	private int amount;
	private String date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCostumerId() {
		return costumerId;
	}

	public void setCostumerId(int costumerId) {
		this.costumerId = costumerId;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id + " " + costumerId + " " + workerId + " " + productId + " " + amount + " " + date;
	}
}
