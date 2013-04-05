package ar.com.magm.persistencia.hibernate.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;

import ar.com.magm.persistencia.hibernate.util.HibernateUtil;

@WebListener()
@WebFilter(urlPatterns = { "/TestHibernateConSpring" })
public class HibernateContextListenerAndFilter implements Filter,
		ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		HibernateUtil.buildSessionFactory();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		try {
			HibernateUtil.openSessionAndAttachToThread();
			filterChain.doFilter(servletRequest, servletResponse);
		} finally {
			if (HibernateUtil.isSessionAttachToThread()) {
				HibernateUtil.closeSessionAndDeattachFromThread();
			}
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtil.closeSessionFactory();
	}
}
