package com.epf.rentmanager.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/rents/create")

public class RentCreateServlet extends HttpServlet {
    public RentCreateServlet() {}

    @Autowired
    ReservationService reservationService;
    @Autowired
    ClientService clientService;
    @Autowired
    VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            request.setAttribute("client", this.clientService.findAll());
            request.setAttribute("vehicle", this.vehicleService.findAll());

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse responce)
            throws ServletException, IOException {
        long client_id;
        long vehicle_id;
        LocalDate debut;
        LocalDate fin;

        client_id = Long.parseLong(request.getParameter("client_id"));
        vehicle_id = Long.parseLong(request.getParameter("vehicle_id"));
        debut = LocalDate.parse(request.getParameter("debut"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        fin = LocalDate.parse(request.getParameter("fin"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Reservation reservation = new Reservation(client_id, vehicle_id, debut, fin);

        try {
            reservationService.create(reservation);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        responce.sendRedirect("http://localhost:8080/rentmanager/rents");

    }
}
