package com.epf.rentmanager.service;


import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;

import java.util.List;

public class ReservationService {
    private ReservationDao reservationDao ;

        public static ReservationService instance;

        private ReservationService () {
            this.reservationDao = ReservationDao.getInstance();
        }

        public static ReservationService getInstance() {
            if (instance == null) {
                instance = new ReservationService();
            }

            return instance;
        }


        public long create(Reservation reservation) throws Exception {
            try {
                return reservationDao.create(reservation);
            } catch (DaoException e) {
                throw new Exception(e);
            }
        }

         public long delete(Reservation reservation) throws Exception {
          try {
            return reservationDao.delete(reservation);
        } catch (DaoException e) {
            throw new Exception(e);
        }
    }

        public List<Reservation> findByIdclient(long clientid) throws Exception{
            try {
                return reservationDao.findResaByClientId(clientid);
            } catch (DaoException e) {
                throw new Exception(e);
            }
        }

        public List<Reservation> findByIdvehicle(long vehicleId) throws Exception{
        try {
            return reservationDao.findResaByVehicleId(vehicleId);
        } catch (DaoException e) {
            throw new Exception(e);
        }
    }


        public List<Reservation> findAll() throws DaoException {
            // TODO: récupérer toutes les reservation
            try {
                return ReservationDao.findAll();
            } catch (DaoException e){
                e.printStackTrace();
                throw new DaoException();
            }
        }
        public int count() throws ServiceException, DaoException {
        // TODO: afficher le nombre de reservations
        try {
            return ReservationDao.getInstance().count();
        } catch (DaoException e){
            e.printStackTrace();
            throw new DaoException();
        }
    }
    }


