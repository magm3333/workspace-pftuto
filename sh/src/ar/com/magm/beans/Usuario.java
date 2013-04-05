package ar.com.magm.beans;

import java.io.Serializable;

import org.hibernate.Hibernate;

public class Usuario implements Serializable {

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApe1() {
		return ape1;
	}

	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}

	public String getApe2() {
		return ape2;
	}

	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private int idUsuario;
	private String login;
	private String nombre;
	private String ape1;
	private String ape2;
	private String password;

	public Usuario() {
	}

	public Usuario(String login, String nombre, String ape1, String ape2,
			String password) {
		this.login = login;
		this.nombre = nombre;
		this.ape1 = ape1;
		this.ape2 = ape2;
		this.password = password;
	}

	@Override
	public String toString() {
		return getLogin() + "-" + getNombre() + " " + getApe1() + " "
				+ getApe2() +" ("+getIdUsuario()+")";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (Hibernate.getClass(this) != Hibernate.getClass(obj)) {
			return false;
		}

		Usuario usuario = (Usuario) obj;
		Object dato1 = getLogin();
		Object dato2 = usuario.getLogin();

		if (dato1 == null) {
			if (dato2 == null) {
				return true;
			} else {
				return false;
			}
		} else if (dato1.equals(dato2) == true) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		Object dato1 = getLogin();
		int resultado = 45;

		resultado = 31 * resultado + (dato1 == null ? 0 : dato1.hashCode());

		return resultado;
	}
}