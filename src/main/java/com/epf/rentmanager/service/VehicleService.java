package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;

public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;
	
	private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance();
	}
	
	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}
	
	
	public long create(Vehicle vehicle) throws Exception {
		// TODO: créer un véhicule
		try {
			return  VehicleDao.create(vehicle);
		} catch (DaoException e) {
			throw new Exception(e);
		}

	}

	public Vehicle findById(long id) throws Exception {
		// TODO: récupérer un véhicule par son id

		try {
			return vehicleDao.findById(id);
		} catch (DaoException e) {
			throw new Exception(e);
		}
		
	}

	public List<Vehicle> findAll() throws ServiceException, DaoException {
		// TODO: récupérer tous les vehicules

		try {
			return VehicleDao.getInstance().findAll();
		} catch (DaoException e){
			e.printStackTrace();
			throw new DaoException();
		}
		
	}

	public int count() throws ServiceException, DaoException {
		// TODO: afficher le nombre de vehicles
		try {
			return VehicleDao.getInstance().count();
		} catch (DaoException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}
}
