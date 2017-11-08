/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;


/**
 *
 * @author Csearfarf
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXButton button;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    
  
    @FXML
    private void handleButtonAction(ActionEvent event)throws IOException {
        
           if(username.getText().equalsIgnoreCase("")){
               JOptionPane.showMessageDialog(null, "Username is Required ! ");
               
           }else if (password.getText().equalsIgnoreCase("")){
               JOptionPane.showMessageDialog(null, "Password is Required ! ");
           }
           else
           {
               
        
   JOptionPane.showMessageDialog(null, "SUCCESSFULLY LOGIN ");
       Parent parent= FXMLLoader.load(getClass().getResource("IndexTest.fxml"));
       Scene second = new Scene(parent);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     //
       //fadeout(); b
       stage.hide();
       stage.setScene(second);
       stage.show();
       
       
       Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
       stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
       stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
     
       
           }

    }
    @FXML
    private void exit(MouseEvent event)throws IOException {
     System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void fadeout() {
    FadeTransition fade = new FadeTransition();
    fade.setDuration(Duration.millis(500));
    fade.setNode(anchorPane);
    fade.setFromValue(1);
    fade.setFromValue(0);
    fade.play();
    
    }

    @FXML
    private void enter(KeyEvent event) throws IOException {
        
             if(username.getText().equalsIgnoreCase("")){
               JOptionPane.showMessageDialog(null, "Username is Required ! ");
               
           }else if (password.getText().equalsIgnoreCase("")){
               JOptionPane.showMessageDialog(null, "Password is Required ! ");
           }
           else
           {
               
        
        JOptionPane.showMessageDialog(null, "SUCCESSFULLY LOGIN ");
       Parent parent= FXMLLoader.load(getClass().getResource("IndexTest.fxml"));
       Scene second = new Scene(parent);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     //
       //fadeout(); b
       stage.hide();
       stage.setScene(second);
       stage.show();
       
       
       Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
       stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
       stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
     
       
           }
        
        
    }
    
    
}
