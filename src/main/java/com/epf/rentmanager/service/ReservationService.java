package com.epf.rentmanager.service;


import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationService {
    private ReservationDao reservationDao ;

        public static ReservationService instance;

    private ReservationService(ReservationDao reservationDao){
        this.reservationDao = reservationDao;
    }


    public long create(Reservation reservation) throws ServiceException {
        try {
            return this.reservationDao.create(reservation);
        }catch (DaoException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Reservation findById(int identifier) throws ServiceException {
        try {
            return this.reservationDao.findById(identifier).get();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reservation> findAll() throws ServiceException {
        try {
            return this.reservationDao.findAll();
        }catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reservation> findResaByVehicleId(int vehicleId) throws ServiceException {
        try {
            return this.reservationDao.findResaByVehicleId(vehicleId);
        }catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reservation> findResaByClientId(int clientId) throws ServiceException {
        try {
            return this.reservationDao.findResaByClientId(clientId);
        }catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long count() throws ServiceException {
        try {
            return this.reservationDao.count();
        }catch (DaoException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long delete(int identifier) throws ServiceException {
        try {
            return this.reservationDao.delete(identifier);
        }catch (DaoException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long update(Reservation reservation) throws ServiceException {
        try {
            return this.reservationDao.update(reservation);
        }catch (DaoException e) {
            e.printStackTrace();
        }
        return 0;
    }
    }


