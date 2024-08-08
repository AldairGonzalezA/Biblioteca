package com.aldairgonzalez.webapp.biblioteca.service;

import java.util.List;

import com.aldairgonzalez.webapp.biblioteca.model.Prestamo;

public interface IPrestamosService {
    public List<Prestamo> listarPrestamos();

    public Prestamo guardarPrestamo(Prestamo prestamo);

    public Prestamo buscarPrestamoPorId(Long id);

    public void eliminarPrestamo(Prestamo prestamo);
}
