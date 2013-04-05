package ar.com.magm.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ar.com.magm.model.dao.controller.ClienteTestController;

@WebServlet("/TestHibernateConSpring")
public class TestHibernateConSpring extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		ClienteTestController controller = (ClienteTestController) applicationContext
				.getBean("clienteController");

		controller.processRequest(request, response);
	}

}
