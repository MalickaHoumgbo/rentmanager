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
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository

public class ReservationDao {

	private ReservationDao() {}

	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String FIND_RESERVATION_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(id) AS nb_rent FROM Reservation;";
	private static final String UPDATE_RESERVATION_QUERY = "UPDATE Reservation SET client_id = ?, vehicle_id = ?, debut = ?, fin = ? WHERE id=?;";


	public long create(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(CREATE_RESERVATION_QUERY);
			statement.setLong(1, reservation.getClient_id());
			statement.setLong(2, reservation.getVehicle_id());
			statement.setDate(3, Date.valueOf(reservation.getDebut()));
			statement.setDate(4, Date.valueOf(reservation.getFin()));

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long delete(int identifier) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_RESERVATION_QUERY);
			statement.setInt(1, identifier);

			return statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Optional<Reservation> findById(long identifier) throws DaoException{
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_RESERVATION_QUERY);
			statement.setLong(1, identifier);
			ResultSet rs = statement.executeQuery();
			rs.next();

			Long client_id = rs.getLong("client_id");
			Long vehicle_id = rs.getLong("vehicle_id");
			LocalDate debut = rs.getDate("debut").toLocalDate();
			LocalDate fin = rs.getDate("fin").toLocalDate();

			Reservation reservation = new Reservation(identifier, client_id, vehicle_id, debut, fin);

			return Optional.of(reservation);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			statement.setLong(1, clientId);
			ResultSet rs = statement.executeQuery();
			List<Reservation> resa = new ArrayList<>();

			while (rs.next()) {

				Long identifier = rs.getLong("id");
				Long vehicle_id = rs.getLong("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(identifier, clientId, vehicle_id,debut, fin);

				resa.add(reservation);

			}

			return resa;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);

			statement.setLong(1, vehicleId);

			ResultSet rs = statement.executeQuery();

			List<Reservation> resa = new ArrayList<>();

			while (rs.next()) {

				Long identifier = rs.getLong("id");
				Long client_id = rs.getLong("client_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(identifier, client_id, vehicleId, debut, fin);

				resa.add(reservation);

			}

			return resa;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Reservation> findAll() throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_RESERVATIONS_QUERY);

			ResultSet rs = statement.executeQuery();

			List<Reservation> resa = new ArrayList<>();

			while (rs.next()) {
				Long identifier = rs.getLong("id");
				Long client_id= rs.getLong("client_id");
				Long vehicle_id = rs.getLong("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();


				Reservation reservation = new Reservation(identifier, client_id, vehicle_id, debut, fin);

				resa.add(reservation);
			}
			return resa;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public long count() throws DaoException {
		int nbreservations=1;
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement= connection.prepareStatement(COUNT_RESERVATIONS_QUERY);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				nbreservations = rs.getInt(nbreservations);
			}
			return nbreservations;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long update(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_RESERVATION_QUERY);

			statement.setLong(1, reservation.getClient_id());
			statement.setLong(2, reservation.getVehicle_id());
			statement.setDate(3, Date.valueOf(reservation.getDebut()));
			statement.setDate(4, Date.valueOf(reservation.getFin()));
			statement.setLong(5, reservation.getIdentifier());


			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
