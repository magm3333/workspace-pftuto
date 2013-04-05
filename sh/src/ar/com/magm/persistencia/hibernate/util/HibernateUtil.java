package ar.com.magm.persistencia.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static HibernateUtilInternalState state = new HibernateUtilInternalState();

	public static void buildSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		state.sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
	}

	public static void closeSessionFactory() {
		state.sessionFactory.close();
	}

	public static void openSessionAndAttachToThread() {
		Session session = state.sessionFactory.openSession();
		state.threadLocalSession.set(session);
	}

	public static SessionFactory getSessionFactory() {
		return new SessionFactoryImplThreadLocal(state);
	}

	public static void closeSessionAndDeattachFromThread() {
		Session session = state.threadLocalSession.get();
		if (session != null) {
			session.close();
		}
		state.threadLocalSession.set(null);
	}

	public static boolean isSessionAttachToThread() {
		if (state.threadLocalSession.get() != null) {
			return true;
		} else {
			return false;
		}
	}
}
