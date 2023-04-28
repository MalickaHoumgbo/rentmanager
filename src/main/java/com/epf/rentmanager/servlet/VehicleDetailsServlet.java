package com.epf.rentmanager.servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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

@WebServlet("/cars/details")

public class VehicleDetailsServlet extends HttpServlet{
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
        List<Client> clients = new ArrayList<>();

        Vehicle vehicle = new Vehicle();

        try {
            vehicle = vehicleService.findById((int) identifier);
            resa = reservationService.findResaByVehicleId((int) identifier);

            for (int i = 0; i < resa.size(); i++) {
                clients.add(clientService.findById((int) clients.get(i).getIdentifier()));
            }
            int reservations = resa.size();
            int nbClient = clients.size();

            request.setAttribute("vehicles", this.vehicleService.findById((int) identifier));
            request.setAttribute("resa", resa);
            request.setAttribute("clients", clients);
            request.setAttribute("nbRents", reservations);
            request.setAttribute("nbClient", nbClient);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/details.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse responce)
            throws ServletException, IOException {

    }
}
