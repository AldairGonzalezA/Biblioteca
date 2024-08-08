package com.aldairgonzalez.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldairgonzalez.webapp.biblioteca.model.Empleado;
import com.aldairgonzalez.webapp.biblioteca.repository.EmpleadoRepository;

@Service
public class EmpleadoService implements IEmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> listarEmpleados() {
       return empleadoRepository.findAll();
    }

    @Override
    public Boolean guardarEmpleado(Empleado empleado) {
        if(!verificarDpiDupicado(empleado)){
            empleadoRepository.save(empleado);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Empleado buscarEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarEmpleado(Empleado empleado) {
        empleadoRepository.delete(empleado);
    }

    @Override
    public Boolean verificarDpiDupicado(Empleado empleadoNuevo) {
        List<Empleado> empleados = listarEmpleados();
        Boolean flag = false;
        for (Empleado empleado : empleados) {
            if(empleado.getDpi().equals(empleadoNuevo.getDpi())){
                flag = true;
            }
        }
        return flag;
    }

}
