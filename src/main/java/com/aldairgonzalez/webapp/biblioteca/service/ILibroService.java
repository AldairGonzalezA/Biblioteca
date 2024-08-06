package com.aldairgonzalez.webapp.biblioteca.service;

import java.util.List;

import com.aldairgonzalez.webapp.biblioteca.model.Libro;

public interface ILibroService {
    public List<Libro> listarLibros();

    public Libro guardarLibro(Libro libro);

    public Libro buscarLibroPorId(Long libroId);

    public void eliminarLibro(Libro libro);
    
}
