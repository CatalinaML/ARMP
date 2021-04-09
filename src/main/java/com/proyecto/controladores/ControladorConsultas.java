package com.proyecto.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.modelos.Consulta;
import com.proyecto.modelos.Usuario;
import com.proyecto.repositorios.RepositorioConsultas;
import com.proyecto.repositorios.RepositorioUsuarios;

@RestController
@RequestMapping("/consultas")
public class ControladorConsultas {
	@Autowired
	private RepositorioConsultas repositorioConsultas;
	@Autowired
	private RepositorioUsuarios repositorioUsuarios;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String cargarDatos() {

		Usuario u = repositorioUsuarios.buscarPorDni("39337701");

		Consulta consulta = new Consulta();
		consulta.setTipoConsulta(Consulta.TipoConsulta.GENERAL);
		consulta.setMensaje("Primera consulta");
		consulta.setDuenioConsulta(u);
		repositorioConsultas.save(consulta);
		u.getConsulta().add(consulta);
		repositorioUsuarios.save(u);

		Usuario user = repositorioUsuarios.buscarPorDni("25432598");

		Consulta consulta1 = new Consulta();
		consulta1.setTipoConsulta(Consulta.TipoConsulta.CAPACITACION);
		consulta1.setMensaje("Primera consulta");
		consulta1.setDuenioConsulta(user);
		repositorioConsultas.save(consulta1);
		user.getConsulta().add(consulta1);
		repositorioUsuarios.save(user);

		return "Consultas cargadas con exito";
	}

	@RequestMapping(value = "/consultas", method = RequestMethod.GET)
	public String consultas(@RequestParam String dni) {

		Usuario u = repositorioUsuarios.buscarPorDni(dni);

		StringBuilder builder = new StringBuilder();

		if (u != null) {

			List<Consulta> consultas = u.getConsulta();
			builder.append("Consultas <br>");

			for (Consulta consulta : consultas) {

				builder.append("Tipo de consulta:" + consulta.getTipoConsulta() + "<br>");
				builder.append(consulta.getMensaje());

			}
		}

		Usuario user = repositorioUsuarios.buscarPorDni(dni);

		StringBuilder builderUser = new StringBuilder();

		if (user != null) {

			List<Consulta> consulta = user.getConsulta();
			builderUser.append("Consultas <br>");

			for (Consulta consultas : consulta) {

				builderUser.append("Tipo de consulta:" + consultas.getTipoConsulta() + "<br>");
				builderUser.append(consultas.getMensaje());

			}
		}
		return builder.toString();
	}
}
