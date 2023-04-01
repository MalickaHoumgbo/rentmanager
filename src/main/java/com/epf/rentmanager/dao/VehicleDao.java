package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {}
	public static VehicleDao getInstance() {
		if(instance == null) {
			instance = new VehicleDao();
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, modele,nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";

	private static final String COUNT_VEHICULES_QUERY = "SELECT COUNT(id) AS count FROM Vehicle ;";

	public static long create(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(CREATE_VEHICLE_QUERY);
			statement.setString(1,vehicle.getConstructeurs());
			statement.setString(2,vehicle.getModele());
			statement.setInt(3, vehicle.getNbplaces());

			statement.execute();
			statement.close();
			return  vehicle.getIdentifier();
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public long delete(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_VEHICLE_QUERY);
			statement.setLong(1,vehicle.getIdentifier());
			long result = statement.executeUpdate();
			statement.close();
			return  result;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public Vehicle findById(long id) throws DaoException {


		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(FIND_VEHICLE_QUERY);
			stmt.setLong( 1, id);
			ResultSet resultat = stmt.executeQuery();
			Vehicle vehicle =new Vehicle();
			vehicle.setIdentifier(id);
			vehicle.setConstructeurs(resultat.getString("constructeur"));
			vehicle.setModele(resultat.getString("modele"));
			vehicle.setNbplaces(resultat.getInt("nb_places"));

			return  vehicle;


		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Vehicle> findAll() throws DaoException {

		List<Vehicle>vehicule = new ArrayList<Vehicle>();
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);

			while(rs.next()){
				int identifier = rs.getInt("id");
				String constructeurs = rs.getString("constructeur");
				String modele = rs.getString("modele");
				int nbplaces = rs.getInt("nb_places");

				vehicule.add(new Vehicle(identifier, constructeurs, modele, nbplaces));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException ();
		}
		return  vehicule;
		
	}
	public int count () throws DaoException {
		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(COUNT_VEHICULES_QUERY);
			ResultSet resultat = stmt.executeQuery();

			int nbvehicle = 0;

			while (resultat.next()){

				nbvehicle= resultat.getInt("count");
			} return nbvehicle;

		} catch (SQLException  e){
			e.printStackTrace();
			throw new DaoException();
		}
	}
	

}
