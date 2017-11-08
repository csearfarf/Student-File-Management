/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Csearfarf
 */
public class Test extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        
       
        
        // loading the first fxml
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        stage.initStyle(StageStyle.DECORATED);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        //center screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        

stage.getIcons().add(new Image(getClass().getResourceAsStream("estrella.gif")));
    }//721/597

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try
     {
            Thread.sleep(500);
        }catch(Exception ex){
            
        }   
        launch(args);
    }
    
}
