package com.epf.rentmanager.main;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.utils.IOUtils;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ServiceException, DaoException
    {

        Client c=new Client(123,"Malicka","Houmgbo","malickahoumgbo@gmail.com", LocalDate.of(2000,4,3));
        System.out.println(c);







    }
}


