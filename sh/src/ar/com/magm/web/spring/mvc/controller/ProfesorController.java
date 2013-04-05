package ar.com.magm.web.spring.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.com.magm.beans.Profesor;
import ar.com.magm.beans.dao.ProfesorDAO;
import ar.com.magm.persistencia.exception.BussinessException;

@Controller
public class ProfesorController {

	@Autowired
	private ProfesorDAO profesorDAO;

	@RequestMapping({ "/index.html" })
	public ModelAndView read(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<>();
		String viewName;

		try {
			Profesor profesor = profesorDAO.get(101);
			model.put("texto", profesor.toString());
			viewName = "profesor";
		} catch (BussinessException ex) {
			model.put("msgError", "No es posible obtener los datos");
			viewName = "error";
		}

		return new ModelAndView(viewName, model);
	}
}