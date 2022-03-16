package com.company.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.contract.CityContract;
import com.company.contract.ProductContract;
import com.company.core.ObjectHelper;
import com.company.interfaces.DALInterfaces;

public class CityDal extends ObjectHelper implements DALInterfaces<CityContract> {

	@Override
	public void Insert(CityContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO City (Name) " + " VALUES ('" + entity.getName() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<CityContract> GetAll() {
		List<CityContract> datacontract = new ArrayList<CityContract>();
		Connection connection = getConnection();
		CityContract contract;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM City");
			while (resultSet.next()) {
				contract = new CityContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setName(resultSet.getString("Name"));

				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	@Override
	public CityContract Delete(CityContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(CityContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE  City SET Name='" + entity.getName() + "'  WHERE id=" + entity.getId() + " ");

			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public CityContract cityId(CityContract entity) {
		CityContract datacontract=new CityContract();
			Connection connection = getConnection();
			try {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery( " SELECT id,name FROM City WHERE Name='" + entity.getName() + "' " );
				while (resultSet.next()) {
					CityContract contract = new CityContract();
					contract.setId(resultSet.getInt("Id"));
					contract.setName(resultSet.getString("Name"));
				
				
					datacontract=contract;
			} }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return datacontract;

			}
	
	
	public List<CityContract> getSearchCity(String cityName) {
		List<CityContract> dataContract = new ArrayList<CityContract>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM City WHERE Name LIKE '" + "%" + cityName + "%" + "'");
			while (resultSet.next()) {
				CityContract contract = new CityContract();

				contract.setName(resultSet.getString("Name"));

				dataContract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContract;

	}
	@Override
	public List<CityContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
