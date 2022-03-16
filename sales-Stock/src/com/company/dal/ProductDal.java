package com.company.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.contract.CategoryContract;
import com.company.contract.ProductContract;
import com.company.core.ObjectHelper;
import com.company.interfaces.DALInterfaces;

public class ProductDal extends ObjectHelper implements DALInterfaces<ProductContract> {

	@Override
	public void Insert(ProductContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement
					.executeUpdate("INSERT INTO Product(Name,CategoryId,DateDay,Price) " + "VALUES('" + entity.getName()
							+ "'," + entity.getCategoryId() + ",'" + entity.getDate() + "'," + entity.getPrice() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<ProductContract> GetAll() {
		List<ProductContract> datacontract = new ArrayList<ProductContract>();
		Connection connection = getConnection();
		ProductContract contract;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Product");
			while (resultSet.next()) {
				contract = new ProductContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setName(resultSet.getString("Name"));
				contract.setCategoryId(resultSet.getInt("CategoryId"));
				contract.setDate(resultSet.getString("DateDay"));

				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;

	}

	public List<ProductContract> getSearchProduct(String productName) {
		List<ProductContract> dataContract = new ArrayList<ProductContract>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM Product WHERE Name LIKE '" + "%" + productName + "%" + "'");
			while (resultSet.next()) {
				ProductContract contract = new ProductContract();

				contract.setName(resultSet.getString("Name"));

				dataContract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContract;

	}
	
	
	public ProductContract productId(ProductContract entity) {
		ProductContract datacontract=new ProductContract();
			Connection connection = getConnection();
			try {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery( " SELECT id,categoryId FROM Product WHERE Name='" + entity.getName() + "' " );
				while (resultSet.next()) {
					ProductContract contract = new ProductContract();
					contract.setId(resultSet.getInt("Id"));
					contract.setCategoryId(resultSet.getInt("CategoryId"));
				
					datacontract=contract;
			} }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return datacontract;

			}

	@Override
	public ProductContract Delete(ProductContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(ProductContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE  Product SET Name='" + entity.getName() + "', CategoryId="
					+ entity.getCategoryId() + ", DateDay='"+entity.getDate()+"', Price="+entity.getPrice()+" WHERE id=" + entity.getId() + " ");

			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<ProductContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
