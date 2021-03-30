package com.proyecto.modelos;

import java.io.File;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Archivo {

	
	@Id
	@GeneratedValue
	private Long id;
	private Date fechaCarga;
	private File archivo;
	
	public Archivo() {
		// TODO Auto-generated constructor stub
	}
	
	public enum TipoArchivo{
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
	
}
