package com.company.contract;

public class CategoryContract {
	private int id;
	private String name;
	private int parentId;

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getParentId() {
		return parentId;
	}


	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
