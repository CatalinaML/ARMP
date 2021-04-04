package com.proyecto.repositorios;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.modelos.Capacitacion;

public interface RepositorioCapacitaciones extends JpaRepository<Capacitacion, Long> {

	@Query("SELECT c FROM Capacitacion c WHERE c.id = :id")
	public Capacitacion buscarPorId(@Param("id") Long id);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO NOVEDAD (DTYPE, CUERPO, TITULO, FECHA_PUBLICACION, FECHA_CAPACITACION, LINK) VALUES ('CAPACITACION', :cuerpo, :titulo, :fecha_publicacion, :fecha_capacitacion, :link)", nativeQuery = true)
	public void cargarCapacitacion(@Param("cuerpo") String cuerpo, @Param("titulo") String titulo,
			@Param("fecha_publicacion") Date fecha_publicacion, @Param("fecha_capacitacion") Date fecha_capacitacion,
			@Param("link") String link);

	@Query("SELECT c FROM Capacitacion c")
	public Collection<Capacitacion> buscarMuchas();

}
