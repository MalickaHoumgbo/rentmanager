package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;
	
	private ClientService() {
		this.clientDao = ClientDao.getInstance();
	}
	
	public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		
		return instance;
	}
	
	
	public long create(Client client) throws Exception {
		// TODO: créer un client
		try {
			return  ClientDao.create(client);
		} catch (DaoException e) {
			throw new Exception(e);
		}

	}

	public long delete(long id) throws Exception {
		try {
			return  ClientDao.delete(id);
		} catch (DaoException e) {
			throw  new Exception(e);
		}
	}


	public Client findById(long id) throws Exception {
		// TODO: récupérer un client par son id

		try {
			return clientDao.findById(id);
		} catch (DaoException e) {
			throw new Exception(e);
		}
	}

	public List<Client> findAll() throws ServiceException, DaoException {
		// TODO: récupérer tous les clients
		try {
			return ClientDao.getInstance().findAll();
		} catch (DaoException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public int count() throws ServiceException, DaoException {
		// TODO: afficher le nombre de clients
		try {
			return ClientDao.getInstance().count();
		} catch (DaoException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}

}
