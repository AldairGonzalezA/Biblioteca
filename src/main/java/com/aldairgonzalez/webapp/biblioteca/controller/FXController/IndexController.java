package com.aldairgonzalez.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.aldairgonzalez.webapp.biblioteca.system.Main;

import javafx.fxml.Initializable;
import lombok.Setter;

@Component
public class IndexController implements Initializable {

    @Setter
    private Main stage;

    @Override
    public void initialize(URL url, ResourceBundle resources) {

    }

}
