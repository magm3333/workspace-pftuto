package ar.com.magm.web.primefaces;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ar.com.magm.model.Venta;
import ar.com.magm.persistencia.hibernate.util.HibernateUtil;

public class VentasBean implements Serializable {

	protected final Log log = LogFactory.getLog(getClass());

	private static final long serialVersionUID = -6690574219803425728L;

	private String[] meses;

	private String sql = "SELECT year(fecha) as anio, month(fecha) as mes, zona, cliente, sum(importe*cantidad) as ventas FROM dw_ventasfact v INNER JOIN clientes c ON v.idCliente=c.idCliente INNER JOIN zonas z ON z.idZona=c.idZona WHERE cliente like ? GROUP BY zona, cliente, anio, mes ORDER BY anio,mes,zona,cliente";
	private List<Venta> ventas;
	private List<Venta> ventasFiltradas;
	private List<String> zonas;
	private FacesContext jsfCtx;
	private ResourceBundle bundle;

	SessionFactory sessionFactory; 

	public VentasBean() {
		jsfCtx = FacesContext.getCurrentInstance();
		bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
		sessionFactory = HibernateUtil.getSessionFactory();
		// processList(null);
	}

	public SelectItem[] getMesesOptions() {
		meses = bundle.getString("lbl.months").split(",");
		SelectItem[] r = new SelectItem[13];
		r[0] = new SelectItem("", bundle.getString("lbl.all.m"));
		for (int t = 0; t < meses.length; t++)
			r[t + 1] = new SelectItem(meses[t], meses[t]);
		return r;
	}

	public List<Venta> getVentas() {
		if (ventas == null) {
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false);
			LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
			String parametroNombre = loginBean.getNombre();
			if (parametroNombre.equals("admin")) {
				parametroNombre = "%";
			}
			processList(new String[] { parametroNombre });
		}
		return ventas;
	}

	public List<Venta> getVentasFiltradas() {
		return ventasFiltradas;
	}

	public SelectItem[] getZonasOptions() {
		SelectItem[] r = new SelectItem[zonas.size() + 1];
		r[0] = new SelectItem("", bundle.getString("lbl.all.f"));
		for (int t = 0; t < zonas.size(); t++)
			r[t + 1] = new SelectItem(zonas.get(t), zonas.get(t));
		return r;
	}

	private void processList(Object args[]) {
		meses = bundle.getString("lbl.months").split(",");
		ventas = new ArrayList<Venta>();
		zonas = new ArrayList<String>();
		Session session = sessionFactory.getCurrentSession(); 
		Query query = session.createSQLQuery(sql);
		if (args != null) {
			for (int t = 0; t < args.length; t++) {
				query.setParameter(0, args[t]);
			}
		}
		List<Object[]> vtaTmp = query.list();
		for (Object[] vo : vtaTmp) {
			Venta venta = new Venta(vo[2].toString(), vo[3].toString(),
					Integer.parseInt(vo[0].toString()), Integer.parseInt(vo[1]
							.toString()), meses[Integer.parseInt(vo[1]
							.toString()) - 1], Double.parseDouble(vo[4]
							.toString()));
			ventas.add(venta);
			if (!zonas.contains(venta.getZona()))
				zonas.add(venta.getZona());
		}

		/*
		ServletContext sc = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		Connection cn = (Connection) sc.getAttribute("datasource");
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			if (args != null) {
				for (int t = 0; t < args.length; t++) {
					pst.setObject(t + 1, args[t]);
				}
			}
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String zona = rs.getString("zona");
				Venta venta = new Venta(zona, rs.getString("cliente"),
						rs.getInt("anio"), rs.getInt("mes"),
						meses[rs.getInt("mes") - 1], rs.getDouble("ventas"));
				ventas.add(venta);
				if (!zonas.contains(zona))
					zonas.add(zona);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}

	public void setVentasFiltradas(List<Venta> ventasFiltradas) {
		this.ventasFiltradas = ventasFiltradas;
	}

}
