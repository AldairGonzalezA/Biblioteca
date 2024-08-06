package com.aldairgonzalez.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aldairgonzalez.webapp.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
