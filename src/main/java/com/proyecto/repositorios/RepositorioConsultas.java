package com.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.modelos.Consulta;

public interface RepositorioConsultas extends JpaRepository<Consulta, Long> {

}
