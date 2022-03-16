package com.company.complex;

public class SalesContractComplex {

	private int id;
	private String costumerName;
	private String workerName;
	private String productName;
	private int amount;
	private String date;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCostumerName() {
		return costumerName;
	}

	public void setCostumerName(String costumerName) {
		this.costumerName = costumerName;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public Object[] getData() {
		Object [] data= {id,workerName,costumerName,productName,amount,date};
		return data;
		
	}

	@Override
	public String toString() {

		return costumerName + " " + workerName + " " + productName;
	}

}
