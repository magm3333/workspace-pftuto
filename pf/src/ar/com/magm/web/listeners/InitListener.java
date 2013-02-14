package ar.com.magm.web.listeners;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class InitListener implements ServletContextListener {

	public InitListener() {
	}

	public void contextInitialized(ServletContextEvent sce) {
		DataSource fuenteDatos = null;
		Context ctx;
		try {
			ServletContext sc = sce.getServletContext();
			ctx = new InitialContext();

			fuenteDatos = (DataSource) ctx
					.lookup("java:comp/env/jdbc/practico");
			Connection cn = fuenteDatos.getConnection();
			sc.setAttribute("datasource", cn);

		} catch (NamingException e) {
			throw new RuntimeException(e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

}
