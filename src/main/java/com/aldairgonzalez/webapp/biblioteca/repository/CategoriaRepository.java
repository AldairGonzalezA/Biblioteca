package com.aldairgonzalez.webapp.biblioteca.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aldairgonzalez.webapp.biblioteca.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}
