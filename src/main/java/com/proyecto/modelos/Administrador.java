package com.proyecto.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Administrador extends Persona{

	@Id
	@GeneratedValue
	private Long id;
	public Administrador() {
	}
	
	public void cargarNovedad() {
		
	}
	public void cargarCapacitacion() {
		
	}
	public void crearUsuario() {
		
	}
}
