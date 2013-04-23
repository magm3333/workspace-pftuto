package ar.com.magm.model;

public class Cliente {
	private String cliente;
	private boolean cuentaHabilitada;
	private int idCliente;
	private Zona zona;

	public String getCliente() {
		return cliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public Zona getZona() {
		return zona;
	}

	public boolean isCuentaHabilitada() {
		return cuentaHabilitada;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void setCuentaHabilitada(boolean cuentaHabilitada) {
		this.cuentaHabilitada = cuentaHabilitada;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
