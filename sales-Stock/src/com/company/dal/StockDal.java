package com.company.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.complex.StockContractComplex;
import com.company.contract.StockContract;
import com.company.core.ObjectHelper;
import com.company.interfaces.DALInterfaces;

public class StockDal extends ObjectHelper implements DALInterfaces<StockContract> {

	@Override
	public void Insert(StockContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(
					" INSERT INTO Stock (WorkerId,ProductId,DateDay,Amount)" + " VALUES (" + entity.getWorkerId() + ","
							+ entity.getProductId() + "," + "'" + entity.getDate() + "'," + entity.getAmount() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<StockContractComplex> getAllStock() {
		List<StockContractComplex> datacontract = new ArrayList<StockContractComplex>();
		Connection connection = getConnection();
		StockContractComplex contract;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT stock.Id, NameSurname, Name, Amount, stock.DateDay FROM Stock "
					+ "LEFT JOIN product ON stock.ProductId=product.Id "
					+ "LEFT JOIN worker ON stock.WorkerId=worker.Id");
			while (resultSet.next()) {
				contract = new StockContractComplex();
				contract.setId(resultSet.getInt("Id"));
				contract.setWorkerName(resultSet.getString("NameSurname"));
				contract.setProductName(resultSet.getString("product.Name"));
				contract.setAmount(resultSet.getInt("Amount"));
				contract.setDate(resultSet.getString("stock.DateDay"));

				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	public List<StockContractComplex> getTotalStock() {
		List<StockContractComplex> datacontract = new ArrayList<StockContractComplex>();
		Connection connection = getConnection();
		StockContractComplex contract;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT SUM(Amount) AS whole , stock.Id, NameSurname, Name, Amount, stock.DateDay FROM Stock "
							+ "LEFT JOIN product ON stock.ProductId=product.Id "
							+ "LEFT JOIN worker ON stock.WorkerId=worker.Id " + "GROUP BY ProductId");
			while (resultSet.next()) {
				contract = new StockContractComplex();
				contract.setId(resultSet.getInt("Id"));
				contract.setWorkerName(resultSet.getString("NameSurname"));
				contract.setProductName(resultSet.getString("product.Name"));	
				contract.setAmount(resultSet.getInt("Whole"));
				contract.setDate(resultSet.getString("stock.DateDay"));

				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	@Override
	public List<StockContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockContract Delete(StockContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(StockContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<StockContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
