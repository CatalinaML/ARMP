package com.proyecto.controladores;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.modelos.Archivo;
import com.proyecto.modelos.Usuario;
import com.proyecto.repositorios.RepositorioArchivos;
import com.proyecto.repositorios.RepositorioUsuarios;

@RestController
@RequestMapping("/archivos")
public class ControladorArchivos {
	@Autowired
	private RepositorioArchivos repositorioArchivos;
	@Autowired
	private RepositorioUsuarios repositorioUsuarios;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String cargarDatos() {

		Usuario u = repositorioUsuarios.buscarPorDni("39337701");

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

		return "Archivos cargados con exito";
	}

	@RequestMapping(value = "/mis-datos", method = RequestMethod.GET)
	public String misDatos(@RequestParam String dni) {

		Usuario u = repositorioUsuarios.buscarPorDni(dni);

		StringBuilder builder = new StringBuilder();

		List<Archivo> archivos = u.getArchivo();
		builder.append("<br> Archivos: <br>");

		for (Archivo archivo : archivos) {
			builder.append(archivo.getFechaCarga() + "<br>");
			builder.append(archivo.getArchivo() + "<br>");
		}

		return builder.toString();
	}

}
