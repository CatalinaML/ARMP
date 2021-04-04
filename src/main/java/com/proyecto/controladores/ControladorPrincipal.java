package com.proyecto.controladores;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.modelos.Archivo;
import com.proyecto.modelos.Capacitacion;
import com.proyecto.modelos.Consulta;
import com.proyecto.modelos.Direccion;
import com.proyecto.modelos.Novedad;
import com.proyecto.modelos.Usuario;
import com.proyecto.repositorios.RepositorioArchivos;
import com.proyecto.repositorios.RepositorioCapacitaciones;
import com.proyecto.repositorios.RepositorioConsultas;
import com.proyecto.repositorios.RepositorioDirecciones;
import com.proyecto.repositorios.RepositorioNovedades;
import com.proyecto.repositorios.RepositorioUsuarios;

@RestController
public class ControladorPrincipal {

	@Autowired
	private RepositorioUsuarios repositorioUsuarios;
	@Autowired
	private RepositorioNovedades repositorioNovedades;
	@Autowired
	private RepositorioCapacitaciones repositorioCapacitaciones;
	@Autowired
	private RepositorioDirecciones repositorioDirecciones;
	@Autowired
	private RepositorioArchivos repositorioArchivos;
	@Autowired
	private RepositorioConsultas repositorioConsultas;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String cargarDatos() {
		repositorioUsuarios.cargarUsuario("39337701", "Manuel", "Liste", "2231212121", "12345678", new Date(),
				new Date(), "manueliste");
		Usuario u = repositorioUsuarios.buscarPorDni("39337701");
		if (u != null) {
			Direccion dire = new Direccion();
			dire.setCalle("Misiones");
			dire.setCodigoPostal("7600");
			dire.setDuenio(u);
			repositorioDirecciones.save(dire);
			u.setDireccion(dire);
			repositorioUsuarios.save(u);

			try {
				Archivo archivo = new Archivo();
				archivo.setFechaCarga(new Date());
				archivo.setDuenioArchivo(u);
				File file = ResourceUtils.getFile("classpath:diagrama-terminado.png");
				archivo.setArchivo(file);
				repositorioArchivos.save(archivo);
				u.getArchivo().add(archivo);
				repositorioUsuarios.save(u);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Consulta consulta = new Consulta();
			consulta.setTipoConsulta(Consulta.TipoConsulta.GENERAL);
			consulta.setMensaje("Primera consulta");
			consulta.setDuenioConsulta(u);
			repositorioConsultas.save(consulta);
			u.getConsulta().add(consulta);
			repositorioUsuarios.save(u);
		}
		repositorioNovedades.cargarNovedad("Cuerpo1", "Uno", new Date());
		repositorioNovedades.cargarNovedad("Cuerpo2", "Dos", new Date());
		repositorioNovedades.cargarNovedad("Cuerpo3", "Tres", new Date());
		repositorioCapacitaciones.cargarCapacitacion("CCuerpo1", "CUno", new Date(), new Date(), "link");
		repositorioCapacitaciones.cargarCapacitacion("CCuerpo2", "CDos", new Date(), new Date(), "link");
		repositorioCapacitaciones.cargarCapacitacion("CCuerpo3", "CTres", new Date(), new Date(), "link");
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

			Direccion dire = u.getDireccion();

			if (dire != null) {
				builder.append("Direccion: " + (dire.getCalle() != null ? dire.getCalle() : "") + " "
						+ (dire.getNumero() != null ? dire.getNumero() : "") + " "
						+ (dire.getPiso() != null ? dire.getPiso() : "") + " "
						+ (dire.getDepartamento() != null ? dire.getDepartamento() : "") + " "
						+ (dire.getCodigoPostal() != null ? dire.getCodigoPostal() : ""));
			}
			List<Archivo> archivos = u.getArchivo();
			builder.append("<br> Archivos: <br>");
			for (Archivo archivo : archivos) {
				builder.append(archivo.getFechaCarga() + "<br>");
				builder.append(archivo.getArchivo() + "<br>");
			}
		}

		return builder.toString();
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

		return builder.toString();
	}

}
