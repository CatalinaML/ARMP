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
			dire.setNumero("2548");
			dire.setCodigoPostal("7600");
			dire.setDuenio(u);
			repositorioDirecciones.save(dire);
			u.setDireccion(dire);
			repositorioUsuarios.save(u);
		}

		Usuario usuario = repositorioUsuarios.buscarPorDni("21369548");

		if (usuario != null) {

			Direccion dire = new Direccion();
			dire.setCalle("Av. Tejedor");
			dire.setNumero("7895");
			dire.setPiso("7mo");
			dire.setDepartamento("E");
			dire.setCodigoPostal("7600");
			dire.setDuenio(usuario);
			repositorioDirecciones.save(dire);
			usuario.setDireccion(dire);
			repositorioUsuarios.save(usuario);
		}

		Usuario user = repositorioUsuarios.buscarPorDni("25432598");

		if (user != null) {

			Direccion dire = new Direccion();
			dire.setCalle("Belgrano");
			dire.setNumero("6284");
			dire.setCodigoPostal("7600");
			dire.setDuenio(user);
			repositorioDirecciones.save(dire);
			user.setDireccion(dire);
			repositorioUsuarios.save(user);
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

		Usuario usuario = repositorioUsuarios.buscarPorDni(dni);

		StringBuilder builderU = new StringBuilder();

		Direccion direU = usuario.getDireccion();

		if (direU != null) {

			builderU.append("Direccion: " + (direU.getCalle() != null ? direU.getCalle() : "") + " "
					+ (direU.getNumero() != null ? direU.getNumero() : "") + " "
					+ (direU.getPiso() != null ? direU.getPiso() : "") + " "
					+ (direU.getDepartamento() != null ? direU.getDepartamento() : "") + " "
					+ (direU.getCodigoPostal() != null ? direU.getCodigoPostal() : ""));

		}

		Usuario user = repositorioUsuarios.buscarPorDni(dni);

		StringBuilder builderUser = new StringBuilder();

		Direccion direUser = user.getDireccion();

		if (direUser != null) {

			builderUser.append("Direccion: " + (direUser.getCalle() != null ? direUser.getCalle() : "") + " "
					+ (direUser.getNumero() != null ? direUser.getNumero() : "") + " "
					+ (direUser.getPiso() != null ? direUser.getPiso() : "") + " "
					+ (direUser.getDepartamento() != null ? direUser.getDepartamento() : "") + " "
					+ (direUser.getCodigoPostal() != null ? direUser.getCodigoPostal() : ""));

		}

		return builder.toString();
	}

}
