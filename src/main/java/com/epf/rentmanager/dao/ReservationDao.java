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
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ReservationDao {

	private static ReservationDao instance = null;
	private ReservationDao() {}
	public static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";

	private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(id) AS count FROM Reservations ;";
		
	public long create(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(CREATE_RESERVATION_QUERY);
			statement.setLong(1,reservation.getClient_id());
			statement.setLong(2,reservation.getVehicle_id());
			statement.setString(3, String.valueOf(Date.valueOf(reservation.getDebut())));
			statement.setString(4,String.valueOf(Date.valueOf(reservation.getFin())));
			statement.execute();
			statement.close();
			return  reservation.getClient_id();
		} catch (SQLException e) {
			throw new DaoException();
		}
	}
	
	public long delete(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_RESERVATION_QUERY);
			statement.setLong(1,reservation.getClient_id());
			long result = statement.executeUpdate();
			statement.close();
			return  result;
		} catch (SQLException e) {
			throw new DaoException();
		}

	}

	
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			preparedStatement.setLong(1,clientId);
			ResultSet result = preparedStatement.executeQuery();
			ArrayList<Reservation> reservations = new ArrayList<>();
			while (result.next()){
			   long id = result.getLong("id");
			   long client_id = result.getLong("client_id");
			   long vehicle_id = result.getLong("vehicle_id");
			   LocalDate debut = result.getDate("debut").toLocalDate();
			   LocalDate fin = result.getDate("fin").toLocalDate();
			   Reservation reservation = new Reservation(id, client_id, vehicle_id,debut,fin);
			   reservations.add(reservation);

		} return reservations;

		}catch (SQLException e){
			throw  new DaoException();
		}
	}
	
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			preparedStatement.setLong(1,vehicleId);
			ResultSet result = preparedStatement.executeQuery();
			ArrayList<Reservation> reservations = new ArrayList<>();
			while (result.next()){
				long id = result.getLong("id");
				long client_id = result.getLong("client_id");
				long vehicle_id = result.getLong("vehicle_id");
				LocalDate debut = result.getDate("debut").toLocalDate();
				LocalDate fin = result.getDate("fin").toLocalDate();
				Reservation reservation = new Reservation(id, client_id, vehicle_id,debut,fin);
				reservations.add(reservation);

			} return reservations;

		}catch (SQLException e){
			throw  new DaoException();
		}

	}

	public static List<Reservation> findAll() throws DaoException {
		List<Reservation>reservations = new ArrayList<Reservation>();
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);

			while(rs.next()){
				int identifier = rs.getInt("id");
				int client_id = rs.getInt("client_id");
				int vehicle_id = rs.getInt("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();
				reservations.add(new Reservation(identifier, client_id, vehicle_id, debut, fin));
			}
			return reservations;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException ();
		}
	}
	public int count () throws DaoException {
		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(COUNT_RESERVATIONS_QUERY);
			ResultSet resultat = stmt.executeQuery();

			int nbreservations = 0;

			while (resultat.next()){

				nbreservations= resultat.getInt("count");
			} return nbreservations;

		} catch (SQLException  e){
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}
	}

}
