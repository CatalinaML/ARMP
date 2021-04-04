package com.proyecto.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String mensaje;
	private TipoConsulta tipoConsulta;
	@ManyToOne
	private Usuario duenioConsulta;

	public Consulta() {
		// TODO Auto-generated constructor stub
	}

	public enum TipoConsulta {
		GENERAL, CAPACITACION;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Usuario getDuenioConsulta() {
		return duenioConsulta;
	}

	public void setDuenioConsulta(Usuario duenioConsulta) {
		this.duenioConsulta = duenioConsulta;
	}

	public TipoConsulta getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(TipoConsulta tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

}
