package com.proyecto.controladores;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.modelos.Novedad;
import com.proyecto.repositorios.RepositorioNovedades;

@RestController
@RequestMapping("/novedades")
public class ControladorNovedades {
	@Autowired
	private RepositorioNovedades repositorioNovedades;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String cargarDatos() {

		repositorioNovedades.cargarNovedad("Abierta la inscripción curso 'x'", "Curso 'x'", new Date());
		repositorioNovedades.cargarNovedad("Por favor actualizar datos personales", "Datos personales!", new Date());

		return "Novedades cargadas con exito";
	}

	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public String inicio(@RequestParam String dni) {

		StringBuilder builder = new StringBuilder();

		Collection<Novedad> novedades = repositorioNovedades.buscarMuchas();
		builder.append("Novedades: <br>");

		for (Novedad novedad : novedades) {

			builder.append(novedad.getTitulo() + "<br>");
			builder.append(novedad.getCuerpo() + "<br>");
			builder.append("Fecha de publicacion: " + novedad.getFechaPublicacion() + "<br> <br>");

		}

		return builder.toString();
	}
}
