package com.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.modelos.Archivo;

public interface RepositorioArchivos extends JpaRepository<Archivo, Long> {

}
