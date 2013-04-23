package ar.com.magm.model.dao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.magm.model.Cliente;
import ar.com.magm.model.Usuario;
import ar.com.magm.persistencia.dao.ClienteDAO;
import ar.com.magm.persistencia.dao.UsuarioDAO;
import ar.com.magm.persistencia.exception.BussinessException;

@Component("inicializaCuentasClientesController")
@Scope("request")
public class InicializaCuentasClientesController {
	@Autowired
	private ClienteDAO clienteDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String salida = "";
		try {
			// Parte 1
			// Inicializamos usuario admin si no existe
			Usuario usuarioAdmin = usuarioDAO.get(33333);
			if (usuarioAdmin == null) {
				usuarioAdmin = new Usuario();
				usuarioAdmin.setId(33333);
				usuarioAdmin.setAdmin(true);
				usuarioAdmin.setNombre("admin");
				usuarioAdmin.setPassword("admin");
				usuarioDAO.saveOrUpdate(usuarioAdmin);
				salida += "Inicializada cuenta admin.\n";
			} else {
				salida += "La cuenta admin ya se encontraba inicializada.\n";
			}
			int cuentasInit = 0;
			// Parte 2
			// Inicializamos cuentas de usuario de cada cliente si no existe
			List<Cliente> clientes = clienteDAO.findAll();
			for (Cliente c : clientes) {
				Usuario usuario = usuarioDAO.get(c.getIdCliente());
				if (usuario == null) {
					cuentasInit++;
					usuario = new Usuario();
					usuario.setId(c.getIdCliente());
					usuario.setAdmin(false);
					usuario.setNombre(c.getCliente());
					usuario.setPassword("Clave " + c.getIdCliente());
					c.setUsuario(usuario);
					usuarioDAO.saveOrUpdate(usuario);
					clienteDAO.saveOrUpdate(c);
				}
			}
			salida += "Cuentas de cliente inicializadas=" + cuentasInit;
		} catch (BussinessException ex) {
			ex.printStackTrace();
			salida = "Error inicializando cuentas de clientes y admin\n"
					+ ex.getMessage();
		}

		response.getWriter().print(salida);
		response.getWriter().flush();
	}
}
