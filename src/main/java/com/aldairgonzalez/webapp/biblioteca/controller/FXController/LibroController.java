package com.aldairgonzalez.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import com.aldairgonzalez.webapp.biblioteca.model.Categoria;
import com.aldairgonzalez.webapp.biblioteca.model.Libro;
import com.aldairgonzalez.webapp.biblioteca.service.LibroService;
import com.aldairgonzalez.webapp.biblioteca.system.Main;
import com.aldairgonzalez.webapp.biblioteca.util.EstadoLibro;
import com.aldairgonzalez.webapp.biblioteca.util.MethodType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

public class LibroController implements Initializable {

    @FXML
    TextField tfId, tfIsbn, tfNombre, tfAutor, tfEditorial, tfdisponibilidad, tfEstanteria, tfCluster;
    @FXML
    TextArea taSipnosis;
    @FXML
    ComboBox cmbCategorias;
    @FXML
    Button btnGuardar, btnLimpiar, btnEliminar;
    @FXML
    TableView tablaLibros;
    @FXML
    TableColumn colId, colIsbn, colNombre,colSipnosis, colAutor, colEditorial,colDisponibilidad,colEstanteria,colCluster,colCategoria;

    @Setter
    private Main stage;

    @Autowired
    LibroService libroService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void cargarDatos(){
        tablaLibros.setItems(listarLibros());
        colId.setCellFactory(new PropertyValueFactory<Libro, Long>("id"));
        colIsbn.setCellFactory(new PropertyValueFactory<Libro,String>("isbn"));
        colNombre.setCellFactory(new PropertyValueFactory<Libro,String>("nombre"));
        colSipnosis.setCellFactory(new PropertyValueFactory<Libro,String>("sipnosis"));
        colAutor.setCellFactory(new PropertyValueFactory<Libro,String>("autor"));
        colEditorial.setCellFactory(new PropertyValueFactory<Libro,String>("editorial"));
        colDisponibilidad.setCellFactory(new PropertyValueFactory<Libro,String>("disponibilidad"));
        colEstanteria.setCellFactory(new PropertyValueFactory<Libro,String>("numeroEstanteria"));
        colCluster.setCellFactory(new PropertyValueFactory<Libro,String>("cluster"));
        colCategoria.setCellFactory(new PropertyValueFactory<Libro, Categoria>("categoria"));
    }

    public ObservableList<Libro> listarLibros(){
        return FXCollections.observableList(libroService.listarLibros());
    }

    public void agregarLibro(){
        Libro libro = null;
        libro.setIsbn(tfIsbn.getText());
        libro.setNombre(tfNombre.getText());
        libro.setSipnosis(taSipnosis.getText());
        libro.setAutor(tfAutor.getText());
        libro.setEditorial(tfEditorial.getText());
        libro.setNumeroEstanteria(tfEstanteria.getText());
        libro.setCluster(tfCluster.getText());
        //libro.setCategoria(cmbCategorias.getSelectionModel());
        libroService.guardarLibro(libro, MethodType.POST);
        cargarDatos();
    }

    public void editarLibro(){
        Libro libro = libroService.buscarLibroPorId(Long.parseLong(tfId.getText()));
        libro.setIsbn(tfIsbn.getText());
        libro.setNombre(tfNombre.getText());
        libro.setSipnosis(taSipnosis.getText());
        libro.setAutor(tfAutor.getText());
        libro.setEditorial(tfEditorial.getText());
        libro.setNumeroEstanteria(tfEstanteria.getText());
        libro.setCluster(tfCluster.getText());
        //libro.setCategoria(cmbCategorias.getSelectionModel());
        libroService.guardarLibro(libro, MethodType.PUT);
        cargarDatos();
    }

    public void eliminarLibro(){
        Libro libro = libroService.buscarLibroPorId(Long.parseLong(tfId.getText()));
        libroService.eliminarLibro(libro);
        cargarDatos();
    }

}
