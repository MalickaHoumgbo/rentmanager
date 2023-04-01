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

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ClientDao {
	
	private static ClientDao instance = null;
	private ClientDao() {}
	public static ClientDao getInstance() {
		if(instance == null) {
			instance = new ClientDao();
		}
		return instance;
	}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final  String COUNT_CLIENTS_QUERY = "SELECT COUNT(id) AS count FROM Client ;";
	
	public static long create(Client client) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(CREATE_CLIENT_QUERY);
			statement.setString(1,client.getName());
			statement.setString(2,client.getLastName());
			statement.setString(3, client.getEmailAdress());
			statement.setString(4,String.valueOf(Date.valueOf(client.getBirthdate())));
			statement.execute();
			statement.close();
			return  client.getIdentifier();
		} catch (SQLException e) {
			throw new DaoException();
		}
	}
	
	public static long delete(long clientId) throws DaoException {

		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT_QUERY);
			statement.setLong(1, clientId);
			long result = statement.executeUpdate();
			statement.close();
			return  result;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public Client findById(long id) throws DaoException {
		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(FIND_CLIENT_QUERY);
			stmt.setLong( 1, id);
			ResultSet resultat = stmt.executeQuery();
			Client client =new Client();
			client.setIdentifier(id);
			client.setName(resultat.getString("nom"));
			client.setLastName(resultat.getString("prenom"));
			client.setEmailAdress(resultat.getString("email"));
			client.setBirthdate(resultat.getDate("naissance").toLocalDate());
			return  client;

		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Client> findAll() throws DaoException {
		List<Client>clients = new ArrayList<Client>();
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);

			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("nom");
				String LastName = rs.getString("prenom");
				String emailAdress = rs.getString("email");
				LocalDate birthdate = rs.getDate("naissance").toLocalDate();
				clients.add(new Client(id, name, LastName, emailAdress, birthdate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException ();
		}
		return  clients;
	}

	public int count () throws DaoException {
		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(COUNT_CLIENTS_QUERY);
			ResultSet resultat = stmt.executeQuery();

			int nbclient = 0;

			while (resultat.next()){

			    nbclient= resultat.getInt("count");
			} return nbclient;

		} catch (SQLException  e){
			e.printStackTrace();
			throw new DaoException();
		}
	}

}
