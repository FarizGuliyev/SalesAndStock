package com.company.contract;

public class CostumerContract {
	private int id;
	private String nameSurname;
	private String telephone;
	private String adress;
	private int cityId;

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNameSurname() {
		return nameSurname;
	}



	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}



	public String getTelephone() {
		return telephone;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	public String getAdress() {
		return adress;
	}



	public void setAdress(String adress) {
		this.adress = adress;
	}



	public int getCityId() {
		return cityId;
	}



	public void setCityId(int cityId) {
		this.cityId = cityId;
	}



	@Override
	public String toString() {
		
		return nameSurname;
	}

}
