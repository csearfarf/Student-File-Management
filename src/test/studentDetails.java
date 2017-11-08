
package test;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;




public class studentDetails {
    private final StringProperty studentNumber;
    private final StringProperty firstname;
    private final StringProperty lastname;
    private final StringProperty contactNumber;
    private final StringProperty address;
    private final StringProperty id;
   

    //default constructor
    public studentDetails(String id,String studentNumber, String firstname, String lastname, String contactNumber, String address) {
        
        this.id = new SimpleStringProperty(id);
        this.studentNumber = new SimpleStringProperty(studentNumber);
        this.firstname = new SimpleStringProperty(firstname );
        this.lastname = new SimpleStringProperty(lastname);
        this.contactNumber = new SimpleStringProperty(contactNumber);
        this.address = new SimpleStringProperty(address);
    }

       // getters
    public String getStudentNumber() {
        return studentNumber.get();
    }

    public String getFirstname() {
        return firstname.get();
    }

    public String getLastname() {
        return lastname.get();
    }

    public String getContactNumber() {
        return contactNumber.get();
    }

    public String getAddress() {
        return address.get();
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
    public void setContactNumber(String value){
        contactNumber.set(value);
    }
    public void setAddress(String value){
        address.set(value);
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
    public StringProperty contactNumberProperty(){
        return contactNumber;
    }
    public StringProperty addressProperty(){
        return address;
    }
    public StringProperty idProperty(){
        return id;
    }
    
          
    
}
