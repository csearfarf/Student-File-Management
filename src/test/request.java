/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Hex
 */
public class request {
    private final StringProperty studentNumber;
    private final StringProperty firstname;
    private final StringProperty lastname;
    private final StringProperty file;
    private final StringProperty accept;
    private final StringProperty id;

    //default constructor
    public request(String id,String studentNumber, String firstname, String lastname, String file, String accept) {
        
        this.id = new SimpleStringProperty(id);
        this.studentNumber = new SimpleStringProperty(studentNumber);
        this.firstname = new SimpleStringProperty(firstname );
        this.lastname = new SimpleStringProperty(lastname);
        this.file = new SimpleStringProperty(file);
        this.accept = new SimpleStringProperty(accept);
      
    }
    
     public String getStudentNumber() {
        return studentNumber.get();
    }

    public String getFirstname() {
        return firstname.get();
    }

    public String getLastname() {
        return lastname.get();
    }

    public String getAccept() {
        return accept.get();
    }

    public String getFile() {
        return file.get();
    }
    public String getId() {
       return id.get();
    }

    // setters
    public void setStudentNumber(String value){
        studentNumber.set(value);
    }
    public void setFirstname(String value){
        firstname.set(value);
    }
    public void setNLastname(String value){
        lastname.set(value);
    }
    public void setAccept(String value){
        accept.set(value);
    }
    public void setFile(String value){
        file.set(value);
    }
    public void setId(String value){
        id.set(value);
    }
   
    
    //Property  Values on each
    public StringProperty studentNumberProperty(){
        return studentNumber;
    }
    public StringProperty firstnameProperty(){
        return firstname;
    }
    public StringProperty lastnameProperty(){
        return lastname;
    }
    public StringProperty acceptProperty(){
        return accept;
    }
    public StringProperty fileProperty(){
        return file;
    }
    public StringProperty idProperty(){
        return id;
    }
   
       
       
    
}
