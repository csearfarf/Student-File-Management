/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Csearfarf
 */
public class StudentChoiceController implements Initializable {

    @FXML
    private JFXButton AddProfile;
    @FXML
    private JFXButton EditProfile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addProfile(ActionEvent event) {
        
        // AnchorPane pane = FXMLLoader.load(getClass().getResource("studentChoice.fxml"));
        //rootPane.getChildren().setAll(pane);
        
    }

    @FXML
    private void editProfile(ActionEvent event) {
    }
    
}
