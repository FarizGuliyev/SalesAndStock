package com.company.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;  

import com.company.contract.CostumerContract;
import com.company.contract.ProductContract;
import com.company.core.ObjectHelper;
import com.company.interfaces.DALInterfaces;

public class CostumerDal extends ObjectHelper implements DALInterfaces<CostumerContract> {

	@Override
	public void Insert(CostumerContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Costumer (NameSurname,Telephone,Adress,CityId) "
					+ "VALUES ('" + entity.getNameSurname() + "','"
					+ entity.getTelephone() + "','"+entity.getAdress()+"',"+entity.getCityId()+")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<CostumerContract> GetAll() {
		List<CostumerContract> datacontract = new ArrayList<CostumerContract>();
		Connection connection = getConnection();
		CostumerContract contract;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Costumer");
			while (resultSet.next()) {
				contract = new CostumerContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setNameSurname(resultSet.getString("NameSurname"));
				contract.setAdress(resultSet.getString("Adress"));
				contract.setCityId(resultSet.getInt("CityId"));
				contract.setTelephone(resultSet.getString("Telephone"));

				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	@Override
	public CostumerContract Delete(CostumerContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(CostumerContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE  Costumer SET NameSurname='" + entity.getNameSurname() + "', Telephone="
					+ entity.getTelephone() + ", Adress='"+entity.getAdress()+"', CityId="+entity.getCityId()+" WHERE id=" + entity.getId() + " ");

			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<CostumerContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	public CostumerContract costumerId(CostumerContract entity) {
		CostumerContract datacontract=new CostumerContract();
			Connection connection = getConnection();
			try {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery( " SELECT id FROM Costumer WHERE NameSurname='" + entity.getNameSurname() + "' " );
				while (resultSet.next()) {
					CostumerContract contract = new CostumerContract();
					contract.setId(resultSet.getInt("Id"));
					
				
					datacontract=contract;
			} }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return datacontract;}

			public List<CostumerContract> getSearchCostumer(String costumerName) {
				List<CostumerContract> dataContract = new ArrayList<CostumerContract>();
				Connection connection = getConnection();
				try {
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement
							.executeQuery("SELECT * FROM Costumer WHERE NameSurname LIKE '" + "%" + costumerName + "%" + "'");
					while (resultSet.next()) {
						CostumerContract contract = new CostumerContract();

						contract.setNameSurname(resultSet.getString("NameSurname"));

						dataContract.add(contract);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return dataContract;

			}
			

}
