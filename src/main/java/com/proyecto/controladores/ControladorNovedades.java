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

		repositorioNovedades.cargarNovedad("Cuerpo1", "Uno", new Date());
		repositorioNovedades.cargarNovedad("Cuerpo2", "Dos", new Date());
		repositorioNovedades.cargarNovedad("Cuerpo3", "Tres", new Date());

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
			builder.append("Fecha de publicacion: " + novedad.getFechaPublicacion() + "<br>");

		}

		return builder.toString();
	}
}
