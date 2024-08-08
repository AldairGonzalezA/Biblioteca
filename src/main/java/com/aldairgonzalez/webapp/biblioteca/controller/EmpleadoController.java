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

import com.aldairgonzalez.webapp.biblioteca.model.Empleado;
import com.aldairgonzalez.webapp.biblioteca.service.EmpleadoService;

@Controller
@RestController
@RequestMapping(value = "")
public class EmpleadoController {


    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/empleados")
    public ResponseEntity <List<Empleado>> listarEmpleados(){
        try {
            return ResponseEntity.ok(empleadoService.listarEmpleados());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/empleado")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(empleadoService.buscarEmpleadoPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/empleado")
    public ResponseEntity<Map<String, String>> agregarEmpleado(@RequestBody Empleado empleado ){
        Map<String, String> response = new HashMap<>();
        try {
            empleadoService.guardarEmpleado(empleado);
            response.put("message", "Empleado creado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el empleado");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/empleado")
    public ResponseEntity<Map<String, String>> editarEmpleado(@RequestParam Long id, @RequestBody Empleado nuevoempleado){
        Map<String, String> response = new HashMap<>();
        try {
            Empleado empleadoOld = empleadoService.buscarEmpleadoPorId(id);
            empleadoOld.setNombre(nuevoempleado.getNombre());
            empleadoOld.setApellido(nuevoempleado.getApellido());
            empleadoOld.setTelefono(nuevoempleado.getTelefono());
            empleadoOld.setDireccion(nuevoempleado.getDireccion());
            empleadoOld.setDpi(nuevoempleado.getDpi());
            empleadoService.guardarEmpleado(empleadoOld);
            response.put("message", "Empleado editado con exito!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "El empleado no se pudo editar!");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/empleado")
    public ResponseEntity<Map<String, String>> eliminarEmpleado(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
            empleadoService.eliminarEmpleado(empleado);
            response.put("message","Empleado eliminado con exito!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al eliminar el empleado");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
