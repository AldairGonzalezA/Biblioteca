package com.aldairgonzalez.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import com.aldairgonzalez.webapp.biblioteca.model.Cliente;
import com.aldairgonzalez.webapp.biblioteca.service.ClienteService;
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

public class ClienteController implements Initializable {

    @FXML
    TextField tfId, tfNombre, tfApellido, tfTelefono;
    @FXML
    Button btnGuardar, btnLimpiar, btnEliminar;
    @FXML
    TableView tablaClientes;
    @FXML
    TableColumn colId, colNombre, colApellido, colTelefono;

    @Setter
    private Main stage;

    @Autowired
    ClienteService clienteService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void cargarDatos(){
        tablaClientes.setItems(listarClientes());
        colId.setCellFactory(new PropertyValueFactory<Cliente,Long>("dpi"));
        colNombre.setCellFactory(new PropertyValueFactory<Cliente,String>("nombreCliente"));
        colApellido.setCellFactory(new PropertyValueFactory<Cliente,String>("apellidoCliente"));
        colTelefono.setCellFactory(new PropertyValueFactory<Cliente,String>("telefonoCliente"));
    }

    public ObservableList<Cliente> listarClientes(){
        return FXCollections.observableList(clienteService.listarClieantes());
    }

    public void agregarCliente(){
        Cliente cliente = null;
        cliente.setNombreCliente(tfNombre.getText());
        cliente.setApellidoCliente(tfApellido.getText());
        cliente.setTelefonoCliente(tfTelefono.getText());
        clienteService.guardarCliente(cliente);
        cargarDatos();
    }

    public void editarCliente(){
        Cliente cliente = clienteService.buscarClientePorId(Long.parseLong(tfId.getText()));
        cliente.setNombreCliente(tfNombre.getText());
        cliente.setApellidoCliente(tfApellido.getText());
        cliente.setTelefonoCliente(tfTelefono.getText());
        clienteService.guardarCliente(cliente);
        cargarDatos();
    }

    public void eliminarCliente(){
        Cliente cliente = clienteService.buscarClientePorId(Long.parseLong(tfId.getText()));
        clienteService.eliminarCliente(cliente);
        cargarDatos();
    }

}
