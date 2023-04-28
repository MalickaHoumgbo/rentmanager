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

@WebServlet("/cars/details")
public class VahicleDetailsServlet extends HttpServlet {
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

        List<Reservation> Resa = new ArrayList<>();
        List<Client> clients = new ArrayList<>();

        Vehicle vehicle = new Vehicle();

        try {
            vehicle = vehicleService.findById((int) identifier);
            Resa = reservationService.findResaByVehicleId((int) identifier);

            for (int i = 0; i < Resa.size(); i++) {
                clients.add(clientService.findById((int) Resa.get(i).getClient_id()));
            }
            int nbreResa = Resa.size();
            int nbreClient = clients.size();

            request.setAttribute("vehicles", this.vehicleService.findById((int) identifier));
            request.setAttribute("listeResa", Resa);
            request.setAttribute("listeClients", clients);
            request.setAttribute("nombre_reservations", nbreResa);
            request.setAttribute("nombre_clients", nbreClient);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/details.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse responce)
            throws ServletException, IOException {

    }

}
