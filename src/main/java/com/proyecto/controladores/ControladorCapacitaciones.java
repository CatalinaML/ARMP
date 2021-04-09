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

		repositorioCapacitaciones.cargarCapacitacion(
				"Habilitada la capacitación 'x', pueden acceder a ella quienes cumplan los requisitos",
				"Capacitación 'x'", new Date(), new Date(), "link.de.capacitacion");
		repositorioCapacitaciones.cargarCapacitacion("Habilitada la capacitación 'y'", "Capacitación 'y'", new Date(),
				new Date(), "link.capacitacion");

		return "Capacitaciones cargadas con exito";
	}

	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public String inicio(@RequestParam String dni) {

		StringBuilder builder = new StringBuilder();

		Collection<Capacitacion> capacitaciones = repositorioCapacitaciones.buscarMuchas();
		builder.append("<br> Capacitaciones: <br>");

		for (Capacitacion capacitacion : capacitaciones) {

			builder.append(capacitacion.getTitulo() + "<br> <br>");
			builder.append(capacitacion.getCuerpo() + "<br>");
			builder.append(capacitacion.getLink() + "<br>");
			builder.append("Fecha de capacitacion: " + capacitacion.getFechaCapacitacion() + "<br>");
			builder.append("Fecha de publicacion: " + capacitacion.getFechaPublicacion() + "<br> <br>");

		}

		return builder.toString();
	}

}
