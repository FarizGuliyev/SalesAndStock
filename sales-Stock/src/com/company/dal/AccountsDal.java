package com.company.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.contract.AccountsContract;
import com.company.contract.WorkerContract;
import com.company.core.ObjectHelper;
import com.company.interfaces.DALInterfaces;

public class AccountsDal extends ObjectHelper implements DALInterfaces<AccountsContract> {

	@Override
	public void Insert(AccountsContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Accounts (PermitId,WorkerId,Passvord) VALUES (" + entity.getPermitId()
					+ "," + entity.getWorkerId() + ",'" + entity.getPassword() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public AccountsContract GetWorkerIdPassword(int workerId, String password) {
		AccountsContract contract = new AccountsContract();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts  WHERE WorkerId=" + workerId + " AND Passvord='" + password.trim() + "'");

			while (resultSet.next()) {
				contract.setId(resultSet.getInt("Id"));
				contract.setWorkerId(resultSet.getInt("WorkerId"));
				contract.setPassword(resultSet.getString("Passvord"));
				contract.setPermitId(resultSet.getInt("PermitId"));
			}
			statement.close();
			connection.close();

		} catch (SQLException e) {
			System.out.println(e);
		}

		return contract;
	}

	public AccountsContract GetPermitId(int workerId) {
		AccountsContract contract = new AccountsContract();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts  WHERE WorkerId=" + workerId + "");

			while (resultSet.next()) {
				contract.setId(resultSet.getInt("Id"));
				contract.setWorkerId(resultSet.getInt("WorkerId"));
				contract.setPermitId(resultSet.getInt("PermitId"));
			}
			statement.close();
			connection.close();

		} catch (SQLException e) {
			System.out.println(e);
		}

		return contract;
	}
	
	public List<AccountsContract> GetAll() {

		return null;
	}

	@Override
	public AccountsContract Delete(AccountsContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(AccountsContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE  Accounts SET PermitId=" + entity.getPermitId() + ", WorkerId="
					+ entity.getWorkerId() + ", Passvord='"+entity.getPassword()+"'  WHERE id=" + entity.getId() + " ");

			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<AccountsContract> GetById(int id) {
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
	
	public AccountsContract accountId(WorkerContract entity) {
		AccountsContract dataContract=new AccountsContract();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(" SELECT id FROM Accounts WHERE WorkerId='" + entity.getId() + "' ");
			while (resultSet.next()) {
				AccountsContract contract = new AccountsContract();
				contract.setId(resultSet.getInt("Id"));

				dataContract = contract;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContract;

		
	}

}
