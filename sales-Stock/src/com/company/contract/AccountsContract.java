package com.company.contract;

public class AccountsContract {
	private int id;
	private int permitId;
	private int workerId;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPermitId() {
		return permitId;
	}

	public void setPermitId(int permitId) {
		this.permitId = permitId;
	}

	public int getWorkerId() {
		return workerId;
	}

	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id + " " + permitId + " " + workerId + " " + password;
	}
}
