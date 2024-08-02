package com.aldairgonzalez.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aldairgonzalez.webapp.biblioteca.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
