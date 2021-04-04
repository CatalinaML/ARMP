package com.proyecto.repositorios;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.modelos.Novedad;

@Repository
public interface RepositorioNovedades extends JpaRepository<Novedad, Long> {

	@Query("SELECT n FROM Novedad n WHERE n.id = :id")
	public Novedad buscarPorId(@Param("id") Long id);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO NOVEDAD (DTYPE, CUERPO, TITULO, FECHA_PUBLICACION) VALUES ('NOVEDAD', :cuerpo, :titulo, :fecha_publicacion)", nativeQuery = true)
	public void cargarNovedad(@Param("cuerpo") String cuerpo, @Param("titulo") String titulo,
			@Param("fecha_publicacion") Date fecha_publicacion);

	@Query("SELECT n FROM Novedad n")
	public Collection<Novedad> buscarMuchas();
}
