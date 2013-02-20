package ar.com.magm.web.primefaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.com.magm.model.Venta;

public class VentasBean implements Serializable {

	private static final long serialVersionUID = -6690574219803425728L;

	private String sql = "SELECT zona, cliente, year(fecha) as anio, month(fecha) as mes, sum(importe*cantidad) as ventas FROM dw_ventasfact v INNER JOIN clientes c ON v.idCliente=c.idCliente INNER JOIN zonas z ON z.idZona=c.idZona GROUP BY zona, cliente, anio, mes ORDER BY anio,mes,zona";

	
	private List<Venta> ventas ;
	
	public List<Venta> getVentas() {
		ventas= new ArrayList<Venta>();
		
		
		return ventas;
	}
}
