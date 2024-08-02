package com.aldairgonzalez.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aldairgonzalez.webapp.biblioteca.model.Cliente;
import com.aldairgonzalez.webapp.biblioteca.service.ClienteService;

@Controller
@RestController
@RequestMapping(value = "cliente-api")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/")
    public List<Cliente> listarClieantes(){
        return clienteService.listarClieantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long dpi){
        try {
            return ResponseEntity.ok(clienteService.buscarClientePorId(dpi));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Map<String,String>> agregarCliente(@RequestBody Cliente cliente){
        Map<String, String> response = new HashMap<>();
        try {
            clienteService.guardarCliente(cliente);
            response.put("message", "Cliente agregado con exito!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error al agregar el Cliente!");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> editarCliente(@PathVariable Long dpi, @RequestBody Cliente clienteNew){
        Map<String, String> response = new HashMap<>();
        try {
            Cliente clienteOld = clienteService.buscarClientePorId(dpi);
            clienteOld.setNombreCliente(clienteNew.getNombreCliente());
            clienteOld.setApellidoCliente(clienteNew.getApellidoCliente());
            clienteOld.setTelefonoCliente(clienteNew.getTelefonoCliente());
            clienteService.guardarCliente(clienteOld);
            response.put("message", "El Cliente se edito con exito!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "El cliente no se pudon editar");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarCliente(@PathVariable Long dpi){
        Map<String, String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarClientePorId(dpi);
            clienteService.eliminarCliente(cliente);
            response.put("message", "Cliente eliminado con exito!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "El Cliente no se pudo eliminar!");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
