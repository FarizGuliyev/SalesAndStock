package com.company.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.contract.CostumerContract;
import com.company.contract.WorkerContract;
import com.company.core.ObjectHelper;
import com.company.interfaces.DALInterfaces;

public class WorkerDal extends ObjectHelper implements DALInterfaces<WorkerContract> {

	@Override
	public void Insert(WorkerContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Worker (NameSurname, Email)" + " VALUES ('" + entity.getNameSurname()
					+ "','" + entity.getEmail() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<WorkerContract> GetAll() {
		List<WorkerContract> datacontract = new ArrayList<WorkerContract>();
		Connection connection = getConnection();
		WorkerContract contract;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Worker");
			while (resultSet.next()) {
				contract = new WorkerContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setNameSurname(resultSet.getString("NameSurname"));
				contract.setEmail(resultSet.getString("Email"));

				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datacontract;
	}

	@Override
	public WorkerContract Delete(WorkerContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(WorkerContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE  Worker SET NameSurname='" + entity.getNameSurname() + "', Email='"
					+ entity.getEmail() + "'  WHERE id=" + entity.getId() + " ");

			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<WorkerContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WorkerContract> getSearchWorker(String workerName) {
		List<WorkerContract> dataContract = new ArrayList<WorkerContract>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM Worker WHERE NameSurname LIKE '" + "%" + workerName + "%" + "'");
			while (resultSet.next()) {
				WorkerContract contract = new WorkerContract();

				contract.setNameSurname(resultSet.getString("NameSurname"));

				dataContract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContract;

	}

	public WorkerContract workerId(WorkerContract entity) {
		WorkerContract datacontract = new WorkerContract();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery(" SELECT id FROM Worker WHERE NameSurname='" + entity.getNameSurname() + "' ");
			while (resultSet.next()) {
				WorkerContract contract = new WorkerContract();
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
