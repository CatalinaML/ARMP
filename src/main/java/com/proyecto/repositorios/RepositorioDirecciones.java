package com.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.modelos.Direccion;

public interface RepositorioDirecciones extends JpaRepository<Direccion, Long> {

}
