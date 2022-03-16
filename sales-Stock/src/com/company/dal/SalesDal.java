package com.company.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.complex.SalesContractComplex;
import com.company.contract.SalesContract;
import com.company.core.ObjectHelper;
import com.company.interfaces.DALInterfaces;

public class SalesDal extends ObjectHelper implements DALInterfaces<SalesContract> {

	@Override
	public void Insert(SalesContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Sales ( ProductId , CostumerId , DateDay , Amount , WorkerId ) "
					+ " VALUES (" + entity.getProductId() + "," + entity.getCostumerId() + "," + "'" + entity.getDate()
					+ "'," + entity.getAmount() + "," + entity.getWorkerId() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<SalesContractComplex> getAllSales() {
		List<SalesContractComplex> datacontract = new ArrayList<SalesContractComplex>();
		Connection connection = getConnection();
		SalesContractComplex contract;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT sales.Id, costumer.NameSurname, worker.NameSurname, Name, Amount, sales.DateDay FROM Sales "
							+ " LEFT JOIN costumer ON sales.CostumerId=costumer.Id "
							+ " LEFT JOIN product ON sales.ProductId=product.Id  "
							+ " LEFT JOIN worker ON sales.WorkerId=worker.Id " + " ORDER BY sales.Id  ");
			while (resultSet.next()) {
				contract = new SalesContractComplex();
				contract.setId(resultSet.getInt("sales.Id"));
				contract.setCostumerName(resultSet.getString("costumer.NameSurname"));
				contract.setWorkerName(resultSet.getString("worker.NameSurname"));
				contract.setProductName(resultSet.getString("Name"));
				contract.setAmount(resultSet.getInt("Amount"));
				contract.setDate(resultSet.getString("sales.DateDay"));

				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	public List<SalesContractComplex> getTotalSales() {
		List<SalesContractComplex> datacontract = new ArrayList<SalesContractComplex>();
		Connection connection = getConnection();
		SalesContractComplex contract;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT SUM(Amount) AS whole , sales.Id, costumer.NameSurname, worker.NameSurname, Name, Amount, sales.DateDay FROM Sales "
							+ " LEFT JOIN costumer ON sales.CostumerId=costumer.Id "
							+ " LEFT JOIN product ON sales.ProductId=product.Id  "
							+ " LEFT JOIN worker ON sales.WorkerId=worker.Id " + " GROUP BY ProductId  ");
			while (resultSet.next()) {
				contract = new SalesContractComplex();
				contract.setId(resultSet.getInt("sales.Id"));
				contract.setCostumerName(resultSet.getString("costumer.NameSurname"));
				contract.setWorkerName(resultSet.getString("worker.NameSurname"));
				contract.setProductName(resultSet.getString("Name"));
				contract.setAmount(resultSet.getInt("whole"));
				contract.setDate(resultSet.getString("sales.DateDay"));

				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	@Override
	public List<SalesContract> GetAll() {

		return null;

	}

	@Override
	public SalesContract Delete(SalesContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(SalesContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SalesContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
