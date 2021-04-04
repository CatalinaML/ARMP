package com.proyecto.modelos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("CAPACITACION")
public class Capacitacion extends Novedad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private Date fechaCapacitacion;
	private String link;

	public Capacitacion() {
		// TODO Auto-generated constructor stub
	}

	public Date getFechaCapacitacion() {
		return fechaCapacitacion;
	}

	public void setFechaCapacitacion(Date fechaCapacitacion) {
		this.fechaCapacitacion = fechaCapacitacion;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
