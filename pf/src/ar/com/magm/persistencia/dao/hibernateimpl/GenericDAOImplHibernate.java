package ar.com.magm.persistencia.dao.hibernateimpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ar.com.magm.persistencia.dao.GenericDAO;
import ar.com.magm.persistencia.exception.BussinessException;
import ar.com.magm.persistencia.exception.BussinessMessage;
import ar.com.magm.persistencia.hibernate.util.HibernateUtil;

public class GenericDAOImplHibernate<T, ID extends Serializable> implements
		GenericDAO<T, ID> {
	SessionFactory sessionFactory;
	protected final Log log = LogFactory.getLog(getClass());

	public GenericDAOImplHibernate() {
		sessionFactory = HibernateUtil.getSessionFactory();

	}

	@Override
	public T create() throws BussinessException {
		try {
			return getEntityClass().newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new RuntimeException(ex);
		} catch (RuntimeException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	

	@Override
	public void saveOrUpdate(T entity) throws BussinessException {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(entity);
			session.getTransaction().commit();
		} catch (javax.validation.ConstraintViolationException cve) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw new BussinessException(cve);
		} catch (org.hibernate.exception.ConstraintViolationException cve) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw new BussinessException(cve);
		} catch (RuntimeException ex) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw ex;
		} catch (Exception ex) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw new RuntimeException(ex);
		}
	}

	@Override
	public T get(ID id) throws BussinessException {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			T entity = (T) session.get(getEntityClass(), id);
			session.getTransaction().commit();

			return entity;
		} catch (javax.validation.ConstraintViolationException cve) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw new BussinessException(cve);
		} catch (org.hibernate.exception.ConstraintViolationException cve) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw new BussinessException(cve);
		} catch (RuntimeException ex) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw ex;
		} catch (Exception ex) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void delete(ID id) throws BussinessException {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			T entity = (T) session.get(getEntityClass(), id);
			if (entity == null) {
				throw new BussinessException(new BussinessMessage(null,
						"Los datos a borrar ya no existen"));
			}
			session.delete(entity);
			session.getTransaction().commit();
		} catch (javax.validation.ConstraintViolationException cve) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw new BussinessException(cve);
		} catch (org.hibernate.exception.ConstraintViolationException cve) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw new BussinessException(cve);
		} catch (BussinessException ex) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw ex;
		} catch (RuntimeException ex) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw ex;
		} catch (Exception ex) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw new RuntimeException(ex);
		}
	}

	@Override
	public List<T> findAll() throws BussinessException {
		Session session = sessionFactory.getCurrentSession();
		try {

			Query query = session.createQuery("SELECT e FROM "
					+ getEntityClass().getName() + " e");
			List<T> entities = query.list();

			return entities;
		} catch (javax.validation.ConstraintViolationException cve) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw new BussinessException(cve);
		} catch (org.hibernate.exception.ConstraintViolationException cve) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw new BussinessException(cve);
		} catch (RuntimeException ex) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw ex;
		} catch (Exception ex) {
			try {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} catch (Exception exc) {
				log.error("Falló al hacer un rollback", exc);
			}
			throw new RuntimeException(ex);
		}
	}

	private Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}