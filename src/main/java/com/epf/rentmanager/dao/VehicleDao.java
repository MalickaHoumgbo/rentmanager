package com.epf.rentmanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import  org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {}

	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, modele,nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";

	private static final String COUNT_VEHICULES_QUERY = "SELECT COUNT(id) AS count FROM Vehicle ;";
	private static final String UPDATE_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur = ?, nb_places = ? WHERE id=?;";

	public static long create(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(CREATE_VEHICLE_QUERY);
			statement.setString(1,vehicle.getConstructeurs());
			statement.setString(2,vehicle.getModele());
			statement.setInt(3, vehicle.getNbplaces());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long delete(int identifier) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_VEHICLE_QUERY);
			statement.setLong(1, identifier);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Optional<Vehicle> findById(long identifier) throws DaoException {


		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_VEHICLE_QUERY);
			statement.setLong( 1, identifier);
			ResultSet resultat = statement.executeQuery();
			resultat.next();
			String constructeurs = resultat.getString("constructeur");
			String modele = resultat.getString("modele");
			Integer nbplaces = resultat.getInt("nb_places");

			Vehicle vehicle = new Vehicle(identifier, constructeurs, modele,nbplaces );

			return Optional.of(vehicle);

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public List<Vehicle> findAll() throws DaoException {

		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);
            List<Vehicle> vehicule = new ArrayList<Vehicle>();
			while(rs.next()){
				long identifier = rs.getLong("id");
				String constructeurs = rs.getString("constructeur");
				String modele = rs.getString("modele");
				int nbplaces = rs.getInt("nb_places");

				vehicule.add(new Vehicle(identifier, constructeurs, modele, nbplaces));
			}
			return  vehicule;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  null;
		
	}
	public int count () throws DaoException {
		int nbvehicles=1;
		try {

			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(COUNT_VEHICULES_QUERY);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				nbvehicles = rs.getInt(nbvehicles);
			}

			return nbvehicles;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long update(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_VEHICLE_QUERY);

			statement.setString(1, vehicle.getConstructeurs());
			statement.setString(2, vehicle.getModele());
			statement.setInt(3, vehicle.getNbplaces());
			statement.setLong(4, vehicle.getIdentifier());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	

}
