package com.proyecto.controladores;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.modelos.Usuario;
import com.proyecto.repositorios.RepositorioUsuarios;

@RestController
@RequestMapping("/usuarios")
public class ControladorUsuarios {
	@Autowired
	private RepositorioUsuarios repositorioUsuarios;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String cargarDatos() {
		repositorioUsuarios.cargarUsuario("39337701", "Carolina", "Lopez", "2231212121", "123456-8", new Date(),
				new Date(), "contrasenia");
		repositorioUsuarios.cargarUsuario("25432598", "Juan", "Perez", "2235483269", "5487236-9", new Date(),
				new Date(), "juanperez");
		repositorioUsuarios.cargarUsuario("21369548", "Veronica", "Suarez", "2236485127", "2457813-5", new Date(),
				new Date(), "123456");
		return "Datos cargados con exito";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam String dni, @RequestParam String contrasenia) {

		Usuario u = repositorioUsuarios.buscarPorDni(dni);

		if (u != null && contrasenia.equals(u.getContrasenia())) {
			return "Logeo exitoso";
		} else {
			return "Error";
		}
	}

	@RequestMapping(value = "/mis-datos", method = RequestMethod.GET)
	public String misDatos(@RequestParam String dni) {

		Usuario u = repositorioUsuarios.buscarPorDni(dni);

		StringBuilder builder = new StringBuilder();

		if (u != null) {

			builder.append("Tus datos: <br>");
			builder.append("Nombre y apellido: " + u.getNombre() + " " + u.getApellido() + "<br>");
			builder.append("DNI: " + u.getDni() + "<br>");
			builder.append("Matricula: " + u.getMatricula() + "<br>");
			builder.append("Fecha de ingreso: " + u.getFechaIngreso() + "<br>");

		}

		return builder.toString();
	}
}
