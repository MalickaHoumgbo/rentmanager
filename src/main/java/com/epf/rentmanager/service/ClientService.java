package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

@Service
public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;

	private ClientService(ClientDao clientDao){
		this.clientDao = clientDao;
	}




	public long create(Client client) throws ServiceException {
		try {
			return this.clientDao.create(client);
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}


	public Client findById(int clientId) throws ServiceException {
		try {
			return this.clientDao.findById(clientId).get();
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}


	public List<Client> findAll() throws ServiceException {
		try {
			return this.clientDao.findAll();
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}


	public long delete(int clientId) throws ServiceException {
		try {
			return this.clientDao.delete(clientId);
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long count() throws ServiceException {
		try {
			return this.clientDao.count();
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long update(Client client) throws ServiceException {
		try {
			return this.clientDao.update(client);
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
