package com.company.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.contract.CategoryContract;
import com.company.core.ObjectHelper;
import com.company.interfaces.DALInterfaces;

public class CategoryDal extends ObjectHelper implements DALInterfaces<CategoryContract> {

	@Override
	public void Insert(CategoryContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Category (Name,ParentId) VALUES ('" + entity.getName() + "',"
					+ entity.getParentId() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<CategoryContract> GetAll() {
		List<CategoryContract> datacontract = new ArrayList<CategoryContract>();
		Connection connection = getConnection();
		CategoryContract contract;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Category");
			while (resultSet.next()) {
				contract = new CategoryContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setName(resultSet.getString("Name"));
				contract.setParentId(resultSet.getInt("ParentId"));

				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	public List<CategoryContract> GetAllParentId() {
		List<CategoryContract> datacontract = new ArrayList<CategoryContract>();
		Connection connection = getConnection();
		CategoryContract contract;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Category WHERE ParentId=0");
			while (resultSet.next()) {
				contract = new CategoryContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setName(resultSet.getString("Name"));
				contract.setParentId(resultSet.getInt("ParentId"));

				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	@Override
	public CategoryContract Delete(CategoryContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(CategoryContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE  Category SET Name='" + entity.getName() + "', ParentId="
					+ entity.getParentId() + " WHERE id=" + entity.getId() + " ");

			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<CategoryContract> GetById(int id) {

		return null;
	}

	public List<CategoryContract> getSearchCategory(String categoryName) {
		List<CategoryContract> dataContract = new ArrayList<CategoryContract>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM Category WHERE Name LIKE '" + "%" + categoryName + "%" + "'");
			while (resultSet.next()) {
				CategoryContract contract = new CategoryContract();

				contract.setName(resultSet.getString("Name"));
				contract.setParentId(resultSet.getInt("ParentId"));
				dataContract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContract;

	}

	public CategoryContract categoryId(CategoryContract entity) {
	CategoryContract datacontract=new CategoryContract();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery( " SELECT id FROM Category WHERE Name='" + entity.getName() + "' " );
			while (resultSet.next()) {
				CategoryContract contract = new CategoryContract();
				contract.setId(resultSet.getInt("Id"));
			
				datacontract=contract;
		} }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;

		}

}