package com.epf.rentmanager.servlet;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars/update")

public class VehicleUpdateServlet extends HttpServlet{
    @Autowired
    VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    private static final long serialVersionUID = 1L;

    long identifier = 0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            identifier = Long.parseLong(request.getParameter("id"));

            Vehicle oldVehicle = this.vehicleService.findById((int) identifier);

            request.setAttribute("oldVehicle", oldVehicle);

            this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/update.jsp").forward(request, response);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        String constructeurs;
        String modele;
        Integer nbplaces;

        constructeurs = request.getParameter("manufacturer");
        modele = request.getParameter("modele");
        nbplaces = Integer.valueOf(request.getParameter("seats"));


        Vehicle vehicle = new Vehicle(identifier, constructeurs, modele,  nbplaces);

        try {
            this.vehicleService.update(vehicle);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        responce.sendRedirect("http://localhost:8080/rentmanager/cars");

    }
}
