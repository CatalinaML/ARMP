package com.proyecto.modelos;

import java.io.File;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Archivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private Date fechaCarga;
	private File archivo;
	@ManyToOne
	private Usuario duenioArchivo;

	public Archivo() {
		// TODO Auto-generated constructor stub
	}

	public enum TipoArchivo {
		CAPACITACION, LICENCIA, CERTIFICADO_MEDICO;
	}

	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	public Usuario getDuenioArchivo() {
		return duenioArchivo;
	}

	public void setDuenioArchivo(Usuario duenioArchivo) {
		this.duenioArchivo = duenioArchivo;
	}

}
