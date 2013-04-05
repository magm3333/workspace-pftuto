package ar.com.magm.persistencia.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtilInternalState {
	SessionFactory sessionFactory;
	ThreadLocal<Session> threadLocalSession = new ThreadLocal<>();
}
