/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Csearfarf
 */
public class dbConnection {
    	public String dbURL = "jdbc:mysql://localhost:3306/javafinals";
	public String username = "root";
	public String password = "";
        public Connection con = null;
	public Statement st = null;
        public ResultSet  rs;   
        
 	    
        
    public Connection Connect(){
        
     try{
     
     Class.forName("com.mysql.jdbc.Driver");
     Connection conn=DriverManager.getConnection(dbURL,username,password);
     
     return conn;
     
       
   } catch(ClassNotFoundException | SQLException ex) {
       Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE,null,ex);
   } 
     return null;
        
    }
    
    	public void connection(String query){// insertion
              try{
	          con = DriverManager.getConnection(dbURL,username,password);
	          st = con.createStatement();
	          st.executeUpdate(query);// insertion/update/delete
	          //JOptionPane.showMessageDialog(null,"Query Executed");
	      }catch(Exception ex){
	          JOptionPane.showMessageDialog(null,ex.getMessage());
	      }
		
	}
    
}
