/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;    
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Csearfarf
 */
public class IndexTestController implements Initializable {

    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private AnchorPane anchorPane2;
    @FXML
    private JFXButton btn1;
    @FXML
    private JFXButton btn2;
    @FXML
    private JFXButton btn3;
    @FXML
    private JFXButton btn4;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        // TODO
    }    


    @FXML
    private void studentEdit(ActionEvent event)throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("StudentProfileEdit.fxml"));
        
       anchorPane2.getChildren().setAll(pane);
    }
    

    @FXML
    private void studentFile(ActionEvent event) throws IOException{
        
       AnchorPane pane = FXMLLoader.load(getClass().getResource("StudentFiles.fxml"));
        
       anchorPane2.getChildren().setAll(pane);
    }

    private void studentRequest(ActionEvent event)  throws IOException{
        
           AnchorPane pane = FXMLLoader.load(getClass().getResource("requestedFiles.fxml"));
        
       anchorPane2.getChildren().setAll(pane);
    }
    //7764/659

    private void exit(MouseEvent event) {
        
        System.exit(0);
        
    }
void makeFadeOut(){
    FadeTransition fadeTransition = new FadeTransition();
    fadeTransition.setDuration(Duration.millis(1000));
    fadeTransition.setNode(anchorPane2);
    fadeTransition.setFromValue(1);
    fadeTransition.setToValue(0);
    
    fadeTransition.setOnFinished((ActionEvent event) ->{
        loadNextScene();
    }
    
    );
    fadeTransition.play();
}
 void loadNextScene(){
     
     try{
         Parent secondView;
         secondView=(AnchorPane) FXMLLoader.load(getClass().getResource("StudentProfile.fxml"));
         Scene newScene = new Scene(secondView);
         Stage curStage=(Stage) anchorPane2.getScene().getWindow();
         
     }catch(IOException ex) {
         
     }
 }


    private void add(ActionEvent event)throws IOException {
        
        
    }

    @FXML
    private void studentRe(ActionEvent event)throws IOException  {
        
        
           AnchorPane pane = FXMLLoader.load(getClass().getResource("requestedFiles.fxml"));
        
       anchorPane2.getChildren().setAll(pane);
    }

    @FXML
    private void add(MouseEvent event)throws IOException
    {
          
    }

    @FXML
    private void addstud(ActionEvent event ) throws IOException {
        
          AnchorPane pane = FXMLLoader.load(getClass().getResource("StudentProfile.fxml"));
       anchorPane2.getChildren().setAll(pane);
    }

    
}
