package com.aldairgonzalez.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldairgonzalez.webapp.biblioteca.model.Prestamo;
import com.aldairgonzalez.webapp.biblioteca.repository.PrestamoRepository;

@Service
public class PrestamoService implements IPrestamosService{

    @Autowired
    PrestamoRepository prestamoRepository;

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo guardarPrestamo(Prestamo prestamo) {
       return prestamoRepository.save(prestamo);
    }

    @Override
    public Prestamo buscarPrestamoPorId(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarPrestamo(Prestamo prestamo) {
        prestamoRepository.delete(prestamo);
    }

}
