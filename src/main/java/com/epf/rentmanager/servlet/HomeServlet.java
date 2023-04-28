package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	@Autowired
	ClientService clientService;
	@Autowired
	VehicleService vehicleService;
	@Autowired
	ReservationService reservationService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

    public static final String home = "/WEB-INF/views/home.jsp";
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("nbClients", this.clientService.count());
			request.setAttribute("nbvehicles", this.vehicleService.count());
			request.setAttribute("nbreservations", this.reservationService.count());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher(home).forward(request, response);
	}
}
