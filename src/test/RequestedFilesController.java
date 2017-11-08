/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
 
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;


public class RequestedFilesController implements Initializable 
{
  public String dbURL = "jdbc:mysql://localhost:3306/javafinals";
    public String username = "root";
    public String password = "";
    public Connection con = null;
    public Statement st = null;
    public ResultSet  rs;   

    @FXML
    private JFXButton acceptedButton;
    @FXML
    private JFXButton deniedButton;
    @FXML
    private JFXButton clearButton;
    @FXML
    private JFXTextArea messageText;
    @FXML
    private TableView<request> tbl_request;
    @FXML
    private TableColumn<request, String> col_stnum;
    @FXML
    private TableColumn<request, String> col_firstname;
    @FXML
    private TableColumn<request, String> col_lastname;
    @FXML
    private TableColumn<request, String> col_fileReq;
    @FXML
    private TableColumn<request, String> col_Accept;
    @FXML
    private JFXButton acceptButton;
    
    private ObservableList<request>data;
    private dbConnection dc;
    String acc="";
    String Snum= "";
    String id1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc=new dbConnection();
        loadtable();
        setTextArea();

    }    

    @FXML
    private void accepted(ActionEvent event) {
        
    }

    @FXML
    private void denied(ActionEvent event) {
    }

    @FXML
    private void clear(ActionEvent event) {
    }

    @FXML//for accepting file
    
    private void accept(ActionEvent event) {
        
        Connection conn=dc.Connect();
        
        try{
         PreparedStatement ps = conn.prepareStatement("UPDATE student_request set approval=? where student_number=? and id=?");
              ps.setString(1, "1");
              ps.setString(2, Snum);
              ps.setString(3, id1);
              ps.executeUpdate();
              loadtable();
        }
        
        catch(SQLException ex){
           System.err.println("Error"+ex);
        }
          JOptionPane.showMessageDialog(null,"accepted!");
    }
    //for the table
    void loadtable(){
         try{
        Connection conn=dc.Connect();
    data=FXCollections.observableArrayList();
    ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM student_request ");
    
     while(rs.next()){
    data.add(new request(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
    
     }
   
    }
   
    catch (SQLException ex){
    System.err.println("Error"+ex);
    }
         
    id1=new PropertyValueFactory<>("studentNumber").toString();
    col_stnum.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
    col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
    col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
    col_fileReq.setCellValueFactory(new PropertyValueFactory<>("accept"));
    col_Accept.setCellValueFactory(new PropertyValueFactory<>("file"));
    tbl_request.setItems(null);
    tbl_request.setItems(data);
       
     }
    
    private void setTextArea(){
       
        //tbl_request.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
       // @Override
        //public void handle(MouseEvent event){
         //   request req =tbl_request.getItems().get(tbl_request.getSelectionModel().getSelectedIndex());
           // messageText.setText(req.getComm());
            // enableFields();
        //}
        
        
        //});
        
    }
 
    
  

    @FXML
    private void clickk(MouseEvent event) {
        
           request fdetail   = tbl_request.getItems().get(tbl_request.getSelectionModel().getSelectedIndex());
         Snum= fdetail.getStudentNumber();
         id1= fdetail.getId();
        System.out.println(Snum);
        System.out.println(id1);
    }

   
}
