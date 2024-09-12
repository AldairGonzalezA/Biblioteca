package com.aldairgonzalez.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import com.aldairgonzalez.webapp.biblioteca.model.Categoria;
import com.aldairgonzalez.webapp.biblioteca.service.CategoriaService;
import com.aldairgonzalez.webapp.biblioteca.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

public class CategoriaController implements Initializable {

    
    @FXML
    TextField tfId, tfNombre;
    @FXML
    Button btnGuardar,btnLimpiar,btnEliminar;
    @FXML
    TableView tableCategorias;
    @FXML
    TableColumn colIdCategoria,colNombreCategoria;

    @Setter
    private Main stage;

    @Autowired
    CategoriaService categoriaService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }

    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar){
            if(tfId.getText().isBlank()){
                agregarCategoria();
            }else{
                editarCategoria();
            }
        }
    }

    public ObservableList<Categoria> listarCategorias(){
        return FXCollections.observableList(categoriaService.listarCategorias());
    }

    public void cargarDatos(){
        tableCategorias.setItems(listarCategorias());
        colIdCategoria.setCellFactory(new PropertyValueFactory<Categoria,Long>("id"));
        colNombreCategoria.setCellValueFactory(new PropertyValueFactory<Categoria,String>("nombreCategoria"));
    }

    public void cargarFormEditar(){
        Categoria categoria = (Categoria)tableCategorias.getSelectionModel().getSelectedItem();
        tfId.setText(Long.toString(categoria.getId()));
        tfNombre.setText(categoria.getNombreCategoria());
    }


    public void agregarCategoria(){
        Categoria categoria = null;
        categoria.setNombreCategoria(tfNombre.getText());
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void editarCategoria(){
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        categoria.setNombreCategoria(tfNombre.getText());
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void eliminarCategoria(){
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        categoriaService.eliminarCategoria(categoria);
        cargarDatos();
    }


}
