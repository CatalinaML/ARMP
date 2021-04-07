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

		return "Consultas cargadas con exito";
	}

	@RequestMapping(value = "/consultas", method = RequestMethod.POST)
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

		return builder.toString();
	}
}
