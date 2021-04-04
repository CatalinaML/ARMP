package com.proyecto.repositorios;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.modelos.Usuario;

@Repository
public interface RepositorioUsuarios extends JpaRepository<Usuario, Long> {

	@Query("SELECT p FROM Usuario p WHERE p.dni = :dni")
	public Usuario buscarPorDni(@Param("dni") String dni);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO PERSONA (DTYPE, DNI, NOMBRE, APELLIDO,TELEFONO,MATRICULA,FECHA_INGRESO, FECHA_NACIMIENTO, CONTRASENIA) VALUES ('USUARIO', :dni, :nombre, :apellido,:telefono, :matricula, :fecha_ingreso, :fecha_nacimiento, :contrasenia)", nativeQuery = true)
	public void cargarUsuario(@Param("dni") String dni, @Param("nombre") String nombre,
			@Param("apellido") String apellido, @Param("telefono") String telefono,
			@Param("matricula") String matricula, @Param("fecha_ingreso") Date fecha_ingreso,
			@Param("fecha_nacimiento") Date fecha_nacimiento, @Param("contrasenia") String contrasenia);

}
