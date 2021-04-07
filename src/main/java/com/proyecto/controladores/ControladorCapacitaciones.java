package com.proyecto.controladores;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.modelos.Capacitacion;
import com.proyecto.repositorios.RepositorioCapacitaciones;

@RestController
@RequestMapping("/capacitaciones")
public class ControladorCapacitaciones {
	@Autowired
	private RepositorioCapacitaciones repositorioCapacitaciones;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String cargarDatos() {

		repositorioCapacitaciones.cargarCapacitacion("CCuerpo1", "CUno", new Date(), new Date(), "link");
		repositorioCapacitaciones.cargarCapacitacion("CCuerpo2", "CDos", new Date(), new Date(), "link");
		repositorioCapacitaciones.cargarCapacitacion("CCuerpo3", "CTres", new Date(), new Date(), "link");

		return "Capacitaciones cargadas con exito";
	}

	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public String inicio(@RequestParam String dni) {

		StringBuilder builder = new StringBuilder();

		Collection<Capacitacion> capacitaciones = repositorioCapacitaciones.buscarMuchas();
		builder.append("<br> Capacitaciones: <br>");

		for (Capacitacion capacitacion : capacitaciones) {

			builder.append(capacitacion.getTitulo() + "<br>");
			builder.append(capacitacion.getCuerpo() + "<br>");
			builder.append(capacitacion.getLink() + "<br>");
			builder.append("Fecha de capacitacion: " + capacitacion.getFechaCapacitacion() + "<br>");
			builder.append("Fecha de publicacion: " + capacitacion.getFechaPublicacion() + "<br>");

		}

		return builder.toString();
	}

}
