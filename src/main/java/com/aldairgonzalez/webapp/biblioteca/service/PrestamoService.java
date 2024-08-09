package com.aldairgonzalez.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldairgonzalez.webapp.biblioteca.model.Libro;
import com.aldairgonzalez.webapp.biblioteca.model.Prestamo;
import com.aldairgonzalez.webapp.biblioteca.repository.PrestamoRepository;
import com.aldairgonzalez.webapp.biblioteca.util.MethodType;

@Service
public class PrestamoService implements IPrestamosService{

    @Autowired
    PrestamoRepository prestamoRepository;

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Integer guardarPrestamo(Prestamo prestamo, MethodType methodType) {
        Integer token = 0;
       if(methodType == MethodType.POST){
            if(!verificarPrestamoActivo(prestamo)){
                if(!verificarLibro(prestamo)){
                    prestamoRepository.save(prestamo);
                    token = 1;
                }
                token = 2;
            }
            
       }else if(methodType == MethodType.PUT){
             prestamoRepository.save(prestamo);
       }else{
            //return prestamoRepository.save(null);
       }
       return token;
    }

    @Override
    public Prestamo buscarPrestamoPorId(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarPrestamo(Prestamo prestamo) {
        prestamoRepository.delete(prestamo);
    }

    @Override
    public Boolean verificarPrestamoActivo(Prestamo prestamoNuevo) {
        List<Prestamo> prestamos = listarPrestamos();
        Boolean flag = false;
        for (Prestamo prestamo : prestamos) {
            if(prestamo.getCliente().getDpi().equals(prestamoNuevo.getCliente().getDpi()) && prestamo.getVigencia() == true && !prestamo.getId().equals(prestamoNuevo.getId())){
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public Boolean verificarLibro(Prestamo prestamoNuevo) {
        List<Libro> libros = prestamoNuevo.getLibros();
        Boolean flag = false;
        for (Libro libro : libros) {
            if(libro.getDisponibilidad() == false){
                flag = true;
            }
        }
        return flag;
    }

    

}
