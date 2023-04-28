package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Service;

@Service

public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;

	private VehicleService(VehicleDao vehicleDao){
		this.vehicleDao = vehicleDao;
	}


	public long create(Vehicle vehicle) throws ServiceException {
		try {
			return this.vehicleDao.create(vehicle);
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;

	}


	public Vehicle findById(int identifier) throws ServiceException {
		try {
			return this.vehicleDao.findById(identifier).get();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;

	}


	public List<Vehicle> findAll() throws ServiceException {
		try {
			return this.vehicleDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}


	public long delete(int identifier) throws ServiceException {
		try {
			return this.vehicleDao.delete(identifier);
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long count() throws ServiceException {
		try {
			return this.vehicleDao.count();
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long update(Vehicle vehicle) throws ServiceException {
		try {
			return this.vehicleDao.update(vehicle);
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;

	}

}
