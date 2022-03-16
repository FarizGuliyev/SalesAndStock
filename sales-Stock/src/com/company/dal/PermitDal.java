package com.company.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.contract.PermitContract;
import com.company.contract.WorkerContract;
import com.company.core.ObjectHelper;
import com.company.interfaces.DALInterfaces;

public class PermitDal extends ObjectHelper implements DALInterfaces<PermitContract> {

	@Override
	public void Insert(PermitContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Permit (Name) VALUES ('" + entity.getName() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<PermitContract> GetAll() {
		List<PermitContract> datacontract = new ArrayList<PermitContract>();
		Connection connection = getConnection();
		PermitContract contract;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Permit");
			while (resultSet.next()) {
				contract = new PermitContract();
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
	public PermitContract Delete(PermitContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(PermitContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE  Permit SET Name='" + entity.getName() + "'  WHERE id=" + entity.getId() + " ");

			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public List<PermitContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PermitContract> getSearchPermit(String permitName) {
		List<PermitContract> dataContract = new ArrayList<PermitContract>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM Permit WHERE Name LIKE '" + "%" + permitName + "%" + "'");
			while (resultSet.next()) {
				PermitContract contract = new PermitContract();

				contract.setName(resultSet.getString("Name"));

				dataContract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContract;

	}

	public PermitContract permitId(PermitContract entity) {
		PermitContract datacontract = new PermitContract();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery(" SELECT id FROM Permit WHERE Name='" + entity.getName() + "' ");
			while (resultSet.next()) {
				PermitContract contract = new PermitContract();
				contract.setId(resultSet.getInt("Id"));

				datacontract = contract;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

}
