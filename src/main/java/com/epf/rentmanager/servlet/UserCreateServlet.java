package com.epf.rentmanager.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users/create")

public class UserCreateServlet extends HttpServlet {
    public UserCreateServlet() {}

    @Autowired
    ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        String name;
        String lastName;
        String emailAdress;
        LocalDate birthdate;

        name = request.getParameter("nom");
        lastName = request.getParameter("prenom");
        emailAdress = request.getParameter("email");
        birthdate = LocalDate.parse(request.getParameter("naissance"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));


        Client client = new Client(name, lastName, emailAdress, birthdate);

        try {
            this.clientService.create(client);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        responce.sendRedirect("http://localhost:8080/rentmanager/users");

    }
}
