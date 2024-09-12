package com.aldairgonzalez.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aldairgonzalez.webapp.biblioteca.model.Categoria;
import com.aldairgonzalez.webapp.biblioteca.service.CategoriaService;
import com.aldairgonzalez.webapp.biblioteca.system.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import lombok.Setter;

@Component
public class IndexController implements Initializable {

    @Setter
    private Main stage;

    @FXML
    MenuItem btnCategorias,btnClientes,btnLibros,btnEmpleados,btnPrestamos;

    @Override
    public void initialize(URL url, ResourceBundle resources) {

    }

    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCategorias){

        }else if(event.getSource() == btnClientes){

        }else if(event.getSource() == btnEmpleados){

        }else if(event.getSource() == btnLibros){

        }else if(event.getSource() == btnPrestamos){
            
        }
    }

    
}
