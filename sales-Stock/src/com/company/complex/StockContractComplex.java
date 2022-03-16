package com.company.complex;

public class StockContractComplex {

	private int id;
	private String workerName;
	private String productName;
	private String date;
	private int amount;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Object[] getData() {
		Object [] data= {id,workerName,productName,amount,date}; 
		return data;
	}
	@Override
	public String toString() {

		return workerName + " " + productName;
	}
}
