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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author Csearfarf
 */
public class StudentFilesController implements Initializable {
    String id;
    String fileid,paylnaym;
    // for FILES
     String mime=null;
      int size;
      String filename = null;
      String imgPath=null;
      private Image image;
      
      public String dbURL = "jdbc:mysql://localhost:3306/javafinals";
    public String username = "root";
    public String password = "";
    public Connection con = null;
    public Statement st = null;
    public ResultSet  rs;   
      
      
    @FXML
    private TableView<filedetails> tableview;
  
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
    private JFXTextField searchStud;
    @FXML
    private BorderPane borderImage;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXButton saveFile;
    @FXML
    private JFXButton changeFile;
    @FXML
    private JFXButton deleteFile;
    @FXML
    private JFXButton browseBtn;
    @FXML
    private JFXButton updateFile;
    @FXML
    private JFXButton searchBtn1;
    
    @FXML
    private TableColumn<filedetails, String> file;
    private ObservableList<filedetails>data;
    private dbConnection dc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        disableAll();
    
    }    

    @FXML
    private void studentNumberClear(MouseEvent event) {
    }

    @FXML
    private void firstnameClear(MouseEvent event) {
    }

    @FXML
    private void lastnameClear(MouseEvent event) {
    }

    @FXML
    private void contactClear(MouseEvent event) {
    }

    @FXML
    private void addressClear(MouseEvent event) {
    }

    @FXML
    private void saveFile(ActionEvent event)  {
        
         dbConnection connect = new dbConnection();
        InputStream img;
          try {
              img = new FileInputStream(new File(imgPath));
              // connection.
              
                PreparedStatement ps = connect.Connect().prepareStatement("insert into student_files(student_id,student_number,mime,filename,size,student_file,alive) values(?,?,?,?,?,?,?)");
                
                ps.setString(1,id);
                ps.setString(2,studNoTxtfield.getText());
                ps.setString(3, mime);
                ps.setString(4,filename);
                ps.setInt(5, size);
                ps.setBlob(6,img);
                ps.setInt(7,0);
              ps.executeUpdate();
              JOptionPane.showMessageDialog(null,"INSERTED");
                 loadtotable();
          } catch (Exception ex) {
              Logger.getLogger(StudentFilesController.class.getName()).log(Level.SEVERE, null, ex);
          }
                
                 
                 
               
    }
void disableAll(){
    
    saveFile.setDisable(true);
    deleteFile.setDisable(true);
    changeFile.setDisable(true);
    updateFile.setDisable(true);
    browseBtn.setDisable(true);
    tableview.setVisible(false);
}
void foundData(){
    saveFile.setDisable(false);
    deleteFile.setDisable(true);
    changeFile.setDisable(true);
    updateFile.setDisable(true);
    browseBtn.setDisable(false);
    tableview.setVisible(true);
    
}
void selectingFiles(){
    saveFile.setDisable(true);
    deleteFile.setDisable(false);
    changeFile.setDisable(false);
    updateFile.setDisable(false);
    browseBtn.setDisable(true);
    tableview.setVisible(true);
    
    
}
    @FXML
    private void changeFile(ActionEvent event) {
        browse();
    }

    @FXML
    private void deleteFile(ActionEvent event) {
        
        dbConnection connect = new dbConnection();
    int dialogButton = JOptionPane.YES_NO_OPTION;
    int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this file ??","Warning",dialogButton);
    try {
    if(dialogResult == JOptionPane.YES_OPTION){
        PreparedStatement ps = connect.Connect().prepareStatement("UPDATE student_files set alive=? where id='"+fileid+"'");
              ps.setInt(1, 1);
              ps.executeUpdate();
        JOptionPane.showMessageDialog(null,"Deleted successfully !");
           loadtotable();
           foundData();
  
    }
    } catch(Exception ex) {}
     loadtotable();
    }

    @FXML
    private void browseFile(ActionEvent event) {
        browse();
    }
   public void loadtotable(){
     
        
          try{
        data=FXCollections.observableArrayList();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafinals","root","");
	  st = con.createStatement();
	  rs= st.executeQuery("Select * from student_files where student_id ='"+id+"' and alive =0");
         
        
        while(rs.next()){
            //getting each data from db by column number
            data.add(new filedetails(rs.getString(1),rs.getString(5)));             
        }
        file.setCellValueFactory(new PropertyValueFactory<>("filename"));
        
        }catch(Exception ex){
        System.err.println("ERROR query123123BIMBI"+ex);
        }
        
        tableview.setItems(null);
        tableview.setItems(data);
      
    }
    
    void search()
    {
        
        
    }

    @FXML
    private void updateFile(ActionEvent event) {
         dbConnection connect = new dbConnection();
        
         PreparedStatement ps;
           InputStream img;
          try {
              img = new FileInputStream(new File(imgPath));
            ps = connect.Connect().prepareStatement("UPDATE student_files set  student_id=?, student_number=? ,mime=? ,filename=?,size=?,student_file=? where id='"+fileid+"'");
            
              ps.setString(1, id);
              ps.setString(2, studNoTxtfield.getText());
              ps.setString(3, mime);
              ps.setString(4, filename);
              ps.setInt(5, size);
              ps.setBlob(6,img);
              ps.executeUpdate();
              JOptionPane.showMessageDialog(null,"FILE UPDATED !");
              loadtotable();
              foundData();
        
        } catch (Exception ex) {
            Logger.getLogger(StudentFilesController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
    void browse(){
          JFileChooser fileChooser = new JFileChooser();
         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
         FileNameExtensionFilter filter = new FileNameExtensionFilter("All Files", "*.*");
         fileChooser.addChoosableFileFilter(filter);
         int result = fileChooser.showOpenDialog(null);
         if(result == JFileChooser.APPROVE_OPTION){
             File selectedFile = fileChooser.getSelectedFile();
             String path = selectedFile.getAbsolutePath();
             
             
             filename=fileChooser.getSelectedFile().getName();
             
             size= (int) selectedFile.length();// size
             imgPath= path;// absolute path
             mime=filename.substring(filename.lastIndexOf("."),filename.length());//mime typee
             //mime=Files.probeContentType(imgPath);// mime type
             image =new Image(selectedFile.toURI().toString(),172,150,true,true);
             imageView=new ImageView(image);
             imageView.setFitWidth(172);
             imageView.setFitHeight(150);
             imageView.setPreserveRatio(true);
             
           
              }
         else if(result == JFileChooser.CANCEL_OPTION){
             imgPath= null;
             System.out.println("No Data");
         }
    
    }

    @FXML
    private void searchStud(ActionEvent event)throws Exception {
     
            dbConnection db = new dbConnection();        
        try {
                
                  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafinals","root","");
	          st = con.createStatement();
	         rs= st.executeQuery("Select * from student_information where studentNumber='"+searchStud.getText()+"'");
         
			if (rs.next()) {
                            if(rs.getString("studentNumber").equalsIgnoreCase(searchStud.getText())){
                            JOptionPane.showMessageDialog(null,"STUDENT NUMBER FOUND");
                            id=rs.getString("id");
                            studNoTxtfield.setText(rs.getString("studentNumber"));
                            fnameTxtfield.setText(rs.getString("firstName"));
                            lnameTxtfield.setText(rs.getString("lastName"));
                            contactNoTxtfield.setText(rs.getString("contactNumber"));
                            addressTxtfield.setText(rs.getString("address"));
                                  loadtotable();
                                  foundData();
                          
				
                            return;
                                
                              
                            }
                           
                        }
                          else{
                                JOptionPane.showMessageDialog(null,"STUDENT NUMBER NOT FOUND");
                            }

		} catch (Exception error) {
			System.out.println("Error: " + error);
		}
        
    }

    @FXML
    private void selectInfo(MouseEvent event) {
        
        filedetails fdetail   = tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
        fileid=fdetail.getFileid();
        paylnaym=fdetail.getFilename();
        
        System.out.println(fileid);
        System.out.println(paylnaym);
        selectingFiles();
    }
    }

