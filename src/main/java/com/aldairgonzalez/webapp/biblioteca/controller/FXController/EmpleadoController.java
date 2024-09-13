package com.aldairgonzalez.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import com.aldairgonzalez.webapp.biblioteca.model.Empleado;
import com.aldairgonzalez.webapp.biblioteca.service.EmpleadoService;
import com.aldairgonzalez.webapp.biblioteca.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

public class EmpleadoController implements Initializable {

    @FXML
    TextField tFId, tfNombre, tfApellido,tfTelefono, tfDireccion, tfDpi;
    @FXML
    Button btnGuardar, btnLimpiar, btnEliminar;
    @FXML
    TableView tablaEmpleados;
    @FXML
    TableColumn colId, colNombre, colApellido, colTelefono, colDireccion, colDpi;

    @Setter
    private Main stage;

    @Autowired
    EmpleadoService empleadoService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void cargarDatos(){
        tablaEmpleados.setItems(listarEmpleados());
        colId.setCellFactory(new PropertyValueFactory<Empleado,Long>("empleadoId"));
        colNombre.setCellFactory(new PropertyValueFactory<Empleado,String>("nombre"));
        colApellido.setCellFactory(new PropertyValueFactory<Empleado,String>("apellido"));
        colTelefono.setCellFactory(new PropertyValueFactory<Empleado,String>("telefono"));
        colDireccion.setCellFactory(new PropertyValueFactory<Empleado,String>("direccion"));
        colDpi.setCellFactory(new PropertyValueFactory<Empleado,String>("dpi"));
    }

    public ObservableList<Empleado> listarEmpleados(){
        return FXCollections.observableList(empleadoService.listarEmpleados());
    }

    public void agregarEmpleado(){
        Empleado empleado = null;
        empleado.setNombre(tfNombre.getText());
        empleado.setApellido(tfApellido.getText());
        empleado.setTelefono(tfTelefono.getText());
        empleado.setDireccion(tfDireccion.getText());
        empleado.setDpi(tfDpi.getText());
        empleadoService.guardarEmpleado(empleado);
        cargarDatos();
    }

    public void editarEmpleado(){
        Empleado empleado = empleadoService.buscarEmpleadoPorId(Long.parseLong(tFId.getText()));
        empleado.setNombre(tfNombre.getText());
        empleado.setApellido(tfApellido.getText());
        empleado.setTelefono(tfTelefono.getText());
        empleado.setDireccion(tfDireccion.getText());
        empleado.setDpi(tfDpi.getText());
        empleadoService.guardarEmpleado(empleado);
        cargarDatos();
    }

    public void eliminarEmpleado(){
        Empleado empleado = empleadoService.buscarEmpleadoPorId(Long.parseLong(tFId.getText()));
        empleadoService.eliminarEmpleado(empleado);
        cargarDatos();
    }

}
