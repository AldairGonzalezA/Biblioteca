package com.aldairgonzalez.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aldairgonzalez.webapp.biblioteca.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo,Long>{

}
