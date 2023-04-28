package com.epf.rentmanager.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/users/details")

public class UserDetailsServlet extends HttpServlet {
    public UserDetailsServlet() {
    }

    @Autowired
    VehicleService vehicleService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long identifier = Long.parseLong(request.getParameter("id"));

        List<Reservation> resa = new ArrayList<>();
        List<Vehicle> vehicule = new ArrayList<>();

        Client client = new Client();

        try {
            client = clientService.findById((int) identifier);
            resa = reservationService.findResaByClientId((int) identifier);

            for (int i = 0; i < resa.size(); i++) {
                vehicule.add(vehicleService.findById((int) resa.get(i).getVehicle_id()));
            }
            int reservations = vehicule.size();
            int vehicles = vehicule.size();

            request.setAttribute("client", this.clientService.findById((int) identifier));
            request.setAttribute("resa", resa);
            request.setAttribute("vehicule", vehicule);
            request.setAttribute("reservations", reservations);
            request.setAttribute("vehicles", vehicles);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/details.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse responce)
            throws ServletException, IOException {

    }

}
