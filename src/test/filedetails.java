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
 * @author Csearfarf
 */
public class filedetails {
    private final StringProperty fileid;
    private final StringProperty filename;
    
     public filedetails(String fileid,String filename) {
        
        this.fileid = new SimpleStringProperty(fileid);
        this.filename = new SimpleStringProperty(filename);
        
    }
      public String getFileid() {
        return fileid.get();
    }

    public String getFilename() {
        return filename.get();
    }
    
        // setters
    public void setFileid(String value){
        fileid.set(value);
    }
    public void setFilename(String value){
        filename.set(value);
    }
    
     //Property  Values on each
    public StringProperty fileidProperty(){
        return fileid;
    }
    public StringProperty filenameProperty(){
        return filename;
    }

    
    
}
