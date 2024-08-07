package com.aldairgonzalez.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aldairgonzalez.webapp.biblioteca.model.Libro;
import com.aldairgonzalez.webapp.biblioteca.service.LibroService;

@Controller
@RestController
@RequestMapping(value = "")
public class LibroController {

    @Autowired
    LibroService libroService;

    @GetMapping("/libros")
    public ResponseEntity <List<Libro>> listarLibros(){
        try {
            return ResponseEntity.ok(libroService.listarLibros());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/libro")
    public ResponseEntity<Libro> buscarLibroPorId(@RequestParam Long libroId){
        try {
            return ResponseEntity.ok(libroService.buscarLibroPorId(libroId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/libro")
    public ResponseEntity<Map<String, String>> agregarLibro(@RequestBody Libro libro){
        Map<String, String> response = new HashMap<>();
        try {
            libroService.guardarLibro(libro);
            response.put("message", "Libro creado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el libro");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/libro")
    public ResponseEntity<Map<String, String>> editarLibro(@RequestParam Long libroId, @RequestBody Libro newlibro){
        Map<String, String> response = new HashMap<>();
        try {
            Libro libroOld = libroService.buscarLibroPorId(libroId);
            libroOld.setIsbn(newlibro.getIsbn());
            libroOld.setNombre(newlibro.getNombre());
            libroOld.setSipnosis(newlibro.getSipnosis());
            libroOld.setAutor(newlibro.getAutor());
            libroOld.setEditorial(newlibro.getEditorial());
            libroOld.setDisponibilidad(newlibro.getDisponibilidad());
            libroOld.setNumeroEstanteria(newlibro.getNumeroEstanteria());
            libroOld.setCluster(newlibro.getCluster());
            libroOld.setCategoria(newlibro.getCategoria());
            libroService.guardarLibro(libroOld);
            response.put("message", "Libro editado con exito!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "El libro no se pudo editar!");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/libro")
    public ResponseEntity<Map<String, String>> eliminarLibro(@RequestParam Long libroId){
        Map<String, String> response = new HashMap<>();
        try {
            Libro libro = libroService.buscarLibroPorId(libroId);
            libroService.eliminarLibro(libro);
            response.put("message","Libro eliminado con exito!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el libro");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
