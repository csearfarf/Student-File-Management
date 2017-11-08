/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;  
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author Csearfarf
 */
public class StudentProfileController implements Initializable {
      String imgPath=null;
      private Image image;
      String pass;
      

    @FXML
    private JFXButton saveBtn;
    @FXML
    private TableView<studentDetails> tableview;
    @FXML
    private TableColumn<studentDetails, String> columnStudentNumber;
    @FXML
    private TableColumn<studentDetails, String> columnFirstname;
    @FXML
    private TableColumn<studentDetails, String> columnLastname;
    @FXML
    private TableColumn<studentDetails, String> columnContactNumber;
    @FXML
    private TableColumn<studentDetails, String> columnAddress;
    private ObservableList<studentDetails>data;
    private dbConnection dc;
    @FXML
    private JFXTextField studNoTxtfield;
    @FXML
    private JFXTextField fnameTxtfield;
    @FXML
    private JFXTextField lnameTxtfield;
    @FXML
    private JFXTextField contactNoTxtfield;
    @FXML
    private JFXTextField addressTxtfield;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXButton saveBtn1;
    @FXML
    private ImageView imageView;
    @FXML
    private BorderPane borderImage;
    private JFXTextField passTxtfield1;

   
    
    public void load(){// select * accounts
        //pane.visibleProperty().set(false);
        
        try{
        Connection conn= dc.Connect();
        data=FXCollections.observableArrayList();
        // Executee query and store result in a result set;
        ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM student_information where alive=0");
        while(rs.next()){
            //getting each data from db by column number
            data.add(new studentDetails(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));             
        }
        columnStudentNumber.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        columnFirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        columnLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        columnContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        }catch(SQLException ex){
        System.err.println("ERROR query"+ex);
        }
        
        tableview.setItems(null);
        tableview.setItems(data);
        //password generating
       
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             // preparation;
             
         dc= new dbConnection();
         // load data from mysql database to tableview
        load();
       
        
    }    

     public void clearInformation() { // clear inputs
        studNoTxtfield.clear();
        fnameTxtfield.clear();
        lnameTxtfield.clear();
        contactNoTxtfield.clear();
        addressTxtfield.clear();
        Pane pane = new Pane();
        borderImage.setCenter(pane);
        imgPath= null;
    }
    @FXML
    private void save(ActionEvent event) {
     dbConnection connect = new dbConnection();
        try{
            if(studNoTxtfield.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null,"Student Number is Required!");
                
            }
            else if(fnameTxtfield.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null,"Firstname is Required!");
                
            }
            else if(lnameTxtfield.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null,"Lastname is Required !");
                
            }
            else if(contactNoTxtfield.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null,"Contact Number is  Required !");
                
            }
            else  if(addressTxtfield.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null,"Address is Required !");
                
            }
            else if (imgPath == null){
        JOptionPane.showMessageDialog(null,"No Image");
        }
            else
                {
                    InputStream img = new FileInputStream(new File(imgPath));   
                    // connection.
                
                 
                 
                 PreparedStatement ps = connect.Connect().prepareStatement("CALL `insertStudent`(?, ?, ?, ?, ?, ?, ?)");
                    // studentnumber= varchar , firstname=in',
              ps.setString(1, studNoTxtfield.getText());
              ps.setString(2, fnameTxtfield.getText());
              ps.setString(3, lnameTxtfield.getText());
              ps.setString(4, contactNoTxtfield.getText());
              ps.setString(5, addressTxtfield.getText());
              ps.setBlob(6,img);
              ps.setInt(7, 0);
              ps.executeUpdate();
                 
                    
                    
                    
        //connect.connection("insert into student_information (studentNumber,firstName,lastName,contactNumber,address,image) values('"+studNoTxtfield.getText()+"','"+fnameTxtfield.getText()+"','"+lnameTxtfield.getText()+"','"+contactNoTxtfield.getText()+"','"+addressTxtfield .getText()+"','"+img+"')");
        // image
        
              
        JOptionPane.showMessageDialog(null,"Created successfully !");
        clearInformation();}
        }
        catch(Exception ex){}
    load();
    
    
    
    
  
        
      
    }
    

    @FXML
    private void setInfo(MouseEvent event) {
        
    }

    @FXML
    private void browseImage(ActionEvent event) {
      
         JFileChooser fileChooser = new JFileChooser();
         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
         fileChooser.addChoosableFileFilter(filter);
         int result = fileChooser.showOpenDialog(null);
         if(result == JFileChooser.APPROVE_OPTION){
             File selectedFile = fileChooser.getSelectedFile();
             String path = selectedFile.getAbsolutePath();
             imgPath= path;
             
             image =new Image(selectedFile.toURI().toString(),172,150,true,true);
             imageView=new ImageView(image);
             imageView.setFitWidth(172);
             imageView.setFitHeight(150);
             imageView.setPreserveRatio(true);
             
             borderImage.setCenter(imageView);
             borderImage.setAlignment(imageView, Pos.CENTER);
            
             
             
              }
         else if(result == JFileChooser.CANCEL_OPTION){
             imgPath= null;
             System.out.println("No Data");
         }
    
    }
   
}
