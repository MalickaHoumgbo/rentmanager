package com.epf.rentmanager.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users/delete")
public class UserDeleteServlet extends HttpServlet {
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

        try {

            long identifier = Long.parseLong(request.getParameter("id"));

            this.clientService.delete((int) identifier);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect("http://localhost:8080/rentmanager/users");

    }
}
