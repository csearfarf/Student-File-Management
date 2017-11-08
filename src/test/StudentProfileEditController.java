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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author Csearfarf
 */
public class StudentProfileEditController implements Initializable {
     String imgPath=null;
      private Image image;
      String trigger="";

      
      //
      
      
    public String dbURL = "jdbc:mysql://localhost:3306/javafinals";
    public String username = "root";
    public String password = "";
    public Connection con = null;
    public Statement st = null;
    public ResultSet  rs;   
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private TableView<studentDetails> tableview;
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
    private JFXTextField searchStud;
    @FXML
    private Label id;
    @FXML
    private JFXButton saveBtn1;
    @FXML
    private BorderPane borderImage;
    @FXML
    private ImageView imageView;
 
    @FXML
    private JFXButton deleteBt;
    // for search
    

    public void load(){// select * accounts
        //pane.visibleProperty().set(false);
        // SEARCH 
        
        
        
        
        
        
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
        System.err.println("ERROR 1123123"+ex);
        }
        
        tableview.setItems(null);
        tableview.setItems(data);
          //searching
         searchbyStudent();
    }
        
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        // SEARCH
        
              dc= new dbConnection();
        load();
        disableFields();
      
    }    

    @FXML
    private void selectInfo(MouseEvent event) throws IOException {
        enableFields();
    dbConnection connect = new dbConnection();

    
    studentDetails  stud = tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
    id.setText(stud.getId());
    studNoTxtfield.setText(stud.getStudentNumber());
    fnameTxtfield.setText(stud.getFirstname());
    lnameTxtfield.setText(stud.getLastname());
    contactNoTxtfield.setText(stud.getContactNumber());
    addressTxtfield.setText(stud.getAddress());
    
    
         try {
             String id1=id.getText();
              con = DriverManager.getConnection(dbURL,username,password);
              
              
             st = con.createStatement();
             String query = "select *  from student_information where id=?";
             
             PreparedStatement ps1 = con.prepareStatement(query);
             ps1.setString(1,id.getText());
             rs= ps1.executeQuery();
            while(rs.next()){
            // imgPath=rs.getString(7);
            InputStream is = rs.getBinaryStream("image"); 
            OutputStream os = new FileOutputStream(new File("photo.png"));
            byte[] content= new byte[1024];
            int size =0;
            while((size=is.read(content))!=-1){
                os.write(content,0,size);
            }
            
             image =new Image("file:photo.png",172,150,true,true);
             ImageView imageView1=new ImageView(image);
             imageView1.setFitWidth(172);
             imageView1.setFitHeight(150);
             imageView1.setPreserveRatio(true);
             // imgPath= image.toString();
               trigger="";
             borderImage.setCenter(imageView1);
             borderImage.setAlignment(imageView1, Pos.CENTER);
            
           
            
            }
         } catch (SQLException ex) {
             Logger.getLogger(StudentProfileEditController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (FileNotFoundException ex) {
             Logger.getLogger(StudentProfileEditController.class.getName()).log(Level.SEVERE, null, ex);
         }
  
    }
    
    @FXML
    private void update(ActionEvent event) {
        String test;
        
        test=image.toString();
         
        System.out.println(trigger);
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
        
           
            else
                {
                    if(trigger.equalsIgnoreCase("") && imgPath==null){
        
              PreparedStatement ps = connect.Connect().prepareStatement("UPDATE student_information set studentNumber=?, firstName=?, lastName=? ,contactNumber=? ,address=? where id='"+id.getText()+"'");
              ps.setString(1, studNoTxtfield.getText());
              ps.setString(2, fnameTxtfield.getText());
              ps.setString(3, lnameTxtfield.getText());
              ps.setString(4, contactNoTxtfield.getText());
              ps.setString(5, addressTxtfield.getText());
              ps.executeUpdate();
                    }
                    else  if(trigger.equalsIgnoreCase("1"))
                    {
              InputStream img = new FileInputStream(new File(imgPath));   
              PreparedStatement ps = connect.Connect().prepareStatement("UPDATE student_information set studentNumber=?, firstName=?, lastName=? ,contactNumber=? ,address=?,image=? where id='"+id.getText()+"'");
              ps.setString(1, studNoTxtfield.getText());
              ps.setString(2, fnameTxtfield.getText());
              ps.setString(3, lnameTxtfield.getText());
              ps.setString(4, contactNoTxtfield.getText());
              ps.setString(5, addressTxtfield.getText());
              ps.setBlob(6,img);
              ps.executeUpdate();
              
              
                    }
                    
   
      JOptionPane.showMessageDialog(null,"Updated successfully !");
      }
        }
        catch(Exception ex){}
    load();
    disableFields();
    
    }

    
    

    @FXML
    private void studentNumberClear(MouseEvent event) {
         studNoTxtfield.clear();
    }

    @FXML
    private void firstnameClear(MouseEvent event) {
        fnameTxtfield.clear();
    }

    @FXML
    private void lastnameClear(MouseEvent event) {
        lnameTxtfield.clear();
    }

    @FXML
    private void contactClear(MouseEvent event) {
        contactNoTxtfield.clear();
    }

    @FXML
    private void addressClear(MouseEvent event) {
        addressTxtfield.clear();
    }

    @FXML
    private void delete(ActionEvent event) {
    dbConnection connect = new dbConnection();
    int dialogButton = JOptionPane.YES_NO_OPTION;
    int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure ?","Warning",dialogButton);
    try {
    if(dialogResult == JOptionPane.YES_OPTION){
        PreparedStatement ps = connect.Connect().prepareStatement("UPDATE student_information set alive=? where id='"+id.getText()+"'");
              ps.setInt(1, 1);
              ps.executeUpdate();
        JOptionPane.showMessageDialog(null,"Deleted successfully !");
  
    }
    } catch(Exception ex) {}
     load();
    }// delete

   

    @FXML
    private void browseImage(ActionEvent event)  {
      
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
            
             
             trigger="1";
              }
         else if(result == JFileChooser.CANCEL_OPTION){
              trigger="";
             imgPath= null;
             System.out.println("No Data");
         }
    
    }

 
    
    private void disableFields(){
    studNoTxtfield.setDisable(true);
        fnameTxtfield.setDisable(true);
        lnameTxtfield.setDisable(true);
        contactNoTxtfield.setDisable(true);
        addressTxtfield.setDisable(true);
        saveBtn.setDisable(true);
        saveBtn1.setDisable(true);
        deleteBt.setDisable(true);

}
        private void enableFields(){
    studNoTxtfield.setDisable(false);
        fnameTxtfield.setDisable(false);
        lnameTxtfield.setDisable(false);
        contactNoTxtfield.setDisable(false);
        addressTxtfield.setDisable(false);
        saveBtn.setDisable(false);
        saveBtn1.setDisable(false);
        deleteBt.setDisable(false);
 
}

    @FXML
    private void search(MouseEvent event) {
        
        
    }

   
   

 public void searchbyStudent(){
           FilteredList<studentDetails> filteredData= new FilteredList<>(data, e->true);
      
           searchStud.textProperty().addListener((observableValue, oldValue, newValue)->{
        filteredData.setPredicate((Predicate<?  super studentDetails>)user->{
            if (newValue==null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(user.getStudentNumber().toLowerCase().contains(lowerCaseFilter)){
                return true;
               
            }
            return false;
            
        });
       });
           SortedList<studentDetails> sortedData= new SortedList<>(filteredData);
           sortedData.comparatorProperty().bind(tableview.comparatorProperty());
           tableview.setItems(sortedData); 
           
           searchStud.clear();
 }




  
}
