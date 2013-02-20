package ar.com.magm.web.primefaces;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

public class LoginBean implements Serializable {

	private static final long serialVersionUID = -2152389656664659476L;

	private String nombre;

	private String clave;
	private boolean logeado = false;

	public boolean estaLogeado() {
		return logeado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void login(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		if (nombre != null && nombre.equals("admin") && clave != null
				&& clave.equals("admin")) {
			logeado = true;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@",
					nombre);
		} else {
			logeado = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
					"Credenciales no válidas");
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("estaLogeado", logeado);
		if (logeado)
			context.addCallbackParam("view", "gauge.xhtml");
	}

	public void logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.invalidate();
		logeado = false;
	}
}
