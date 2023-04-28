package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ClientDao {
	
	private static ClientDao instance = null;
	private ClientDao() {}
	//public static ClientDao getInstance() {
	//	if(instance == null) {
	//		instance = new ClientDao();
	//	}
	//	return instance;
	//}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final  String COUNT_CLIENTS_QUERY = "SELECT COUNT(id) AS count FROM Client ;";

	private static final String UPDATE_CLIENT_QUERY = "UPDATE Client SET nom = ?, prenom = ?, email = ?, naissance = ? WHERE id=?;";

	public long create(Client client) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(CREATE_CLIENT_QUERY);

			statement.setString(1, client.getName());
			statement.setString(2, client.getLastName());
			statement.setString(3, client.getEmailAdress());
			statement.setDate(4, Date.valueOf(client.getBirthdate()));

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long delete(long clientId) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT_QUERY);

			statement.setLong(1, clientId);
			return statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Optional<Client> findById(long clientId) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENT_QUERY);
			pstmt.setLong(1, clientId);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String Name = rs.getString("nom");
			String LastName = rs.getString("prenom");
			String emailAdress = rs.getString("email");
			LocalDate Birthday = rs.getDate("naissance").toLocalDate();

			Client client = new Client(clientId,Name,LastName,emailAdress,Birthday );

			return Optional.of(client); // ou Optionla.offNullable(client)

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public List<Client> findAll() throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_CLIENTS_QUERY);
			ResultSet rs = statement.executeQuery();
			List<Client> clients = new ArrayList<>();

			while (rs.next()) {
				long identifier = rs.getLong("id");
				String Name = rs.getString("nom");
				String LastName = rs.getString("prenom");
				String emailAdress = rs.getString("email");
				LocalDate Birthday = rs.getDate("naissance").toLocalDate();
				Client client = new Client(identifier, Name, LastName, emailAdress, Birthday);
				clients.add(client);
			}
			return clients;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public long count() throws DaoException {
		int nbClients = 1;
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(COUNT_CLIENTS_QUERY);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				nbClients = rs.getInt(nbClients);
			}
			return nbClients;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long update(Client client) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT_QUERY);
			statement.setString(1, client.getName());
			statement.setString(2, client.getLastName());
			statement.setString(3, client.getEmailAdress());
			statement.setDate(4, Date.valueOf(client.getBirthdate()));
			statement.setLong(5, client.getIdentifier());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
