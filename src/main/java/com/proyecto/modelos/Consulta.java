package com.proyecto.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Consulta {

	@Id
	@GeneratedValue
	private Long id;
	private String mensaje;
	
	public Consulta() {
		// TODO Auto-generated constructor stub
	}

	public enum tipoConsulta{
		GENERAL,
		CAPACITACION;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
