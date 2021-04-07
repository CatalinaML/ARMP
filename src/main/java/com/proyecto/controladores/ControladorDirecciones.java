package com.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.modelos.Direccion;
import com.proyecto.modelos.Usuario;
import com.proyecto.repositorios.RepositorioDirecciones;
import com.proyecto.repositorios.RepositorioUsuarios;

@RestController
@RequestMapping("/direcciones")
public class ControladorDirecciones {
	@Autowired
	private RepositorioDirecciones repositorioDirecciones;
	@Autowired
	private RepositorioUsuarios repositorioUsuarios;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String cargarDatos() {

		Usuario u = repositorioUsuarios.buscarPorDni("39337701");

		if (u != null) {

			Direccion dire = new Direccion();
			dire.setCalle("Misiones");
			dire.setCodigoPostal("7600");
			dire.setDuenio(u);
			repositorioDirecciones.save(dire);
			u.setDireccion(dire);
			repositorioUsuarios.save(u);

		}

		return "Datos de direccion cargados con exito";
	}

	@RequestMapping(value = "/mis-datos", method = RequestMethod.GET)
	public String misDatos(@RequestParam String dni) {

		Usuario u = repositorioUsuarios.buscarPorDni(dni);

		StringBuilder builder = new StringBuilder();

		Direccion dire = u.getDireccion();

		if (dire != null) {

			builder.append("Direccion: " + (dire.getCalle() != null ? dire.getCalle() : "") + " "
					+ (dire.getNumero() != null ? dire.getNumero() : "") + " "
					+ (dire.getPiso() != null ? dire.getPiso() : "") + " "
					+ (dire.getDepartamento() != null ? dire.getDepartamento() : "") + " "
					+ (dire.getCodigoPostal() != null ? dire.getCodigoPostal() : ""));

		}

		return builder.toString();
	}

}
