package com.epf.rentmanager.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users/update")

public class UserUpdateServlet extends HttpServlet {
    @Autowired
    ClientService clientService;

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

            Client oldClient = this.clientService.findById((int) identifier);

            request.setAttribute("oldClient", oldClient);
            String birthdate = oldClient.getBirthdate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));;
            request.setAttribute("naissance", birthdate);

            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/update.jsp").forward(request, response);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        String name;
        String lastName;
        String emailAdress;
        LocalDate birthday;

        name = request.getParameter("nom");
        lastName = request.getParameter("prenom");
        emailAdress = request.getParameter("email");
        birthday = LocalDate.parse(request.getParameter("naissance"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));


        Client client = new Client(identifier, name, lastName, emailAdress, birthday);

        try {
            this.clientService.update(client);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        responce.sendRedirect("http://localhost:8080/rentmanager/users");

    }
}
