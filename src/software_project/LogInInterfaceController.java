/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_project;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP2020
 */
public class LogInInterfaceController implements Initializable {
                         int counter =0; 

     String uniform_resource_locator= "jdbc:mysql://localhost:3306/e-wallet?useSSL=true"; 
    String user = "Ezz_Addin_kukhun"; 
    String pass = "1092000waz"; 
    @FXML
    private Label warnings;
    @FXML
    private JFXTextField ID;
    @FXML
    private JFXPasswordField Password;
    
    int i =0; 
    String names [] = new String [2]; 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LogIn(ActionEvent event) {
        
        if (i>1)
        {
            i=0; 
        }
        
        
        
           names [i] = this.ID.getText(); 
           
           if (names[1] != null)
           {
               if (!names [0].equals(names[1]))
                       {
                           counter =0; 
                       }
           }
           
            
           
     
        if (this.ID.getText().isEmpty() || this.Password.getText().isEmpty())
        {
                             this.warnings.setVisible(true);
            this.warnings.setText("Please fill the fields"); 
        }
        else 
        {
          
        try {
             Class.forName("com.mysql.cj.jdbc.Driver"); 
             Connection connection = DriverManager.getConnection(this.uniform_resource_locator,user,pass); 
             Statement stmt = connection.createStatement();   
             String selectuserdata = "select * from user where bankID='"+this.ID.getText()+"'"; 
             ResultSet rs = stmt.executeQuery(selectuserdata); 
             
             if (!rs.next())
             {
                 this.warnings.setVisible(true);
             this.warnings.setText("This BankID doesn't exist"); 
 
             }
             
             else
             {
                selectuserdata = "select * from user where bankID='"+this.ID.getText()+"'"; 
                ResultSet rs2 = stmt.executeQuery(selectuserdata);  
                rs2.next(); 
                
                if (rs2.getInt(14) == 1)
                {
                    this.warnings.setVisible(true); 
                    this.warnings.setText("This account is blocked,activate it from admin");
                }
                
                else
                {
                
                    
                if (this.Password.getText().trim().equals(rs2.getString(5).trim()))
                {
            FXMLLoader loader = new FXMLLoader (getClass().getResource("UserInterface.fxml"));  
            Parent root = loader.load(); 
            UserInterface userinterface = loader.getController(); 
            userinterface.setInfo(this.ID.getText());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
            Scene scene = new Scene (root); 
            stage.setScene(scene);
            stage.show();

                }
                else
                {
                    counter++; 
                    i++;
                    if (counter >= 3)
                    {
                        String update = "update user set blocking='"+1+"' where bankID='"+this.ID.getText()+"'"; 
                        stmt.executeUpdate(update); 
                         this.warnings.setVisible(true);
                         this.warnings.setText("This account is blocked at the"); 
                    }
                    else
                    {
                                     this.warnings.setVisible(true);
                         this.warnings.setText("Password is wrong"); 
                    }
                }
                 
             }
             
             }  
        
        }
       catch (Exception e)
       {
           System.err.print(e);
       }
    }
    
        }

    @FXML
    private void adminLogIn(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader (getClass().getResource("AdminLogIn.fxml"));  
        Parent root = loader.load(); 
            AdminLogIn m = loader.getController(); 
             Stage stage = new Stage (); 
             stage.setScene (new Scene (root)); 
             stage.show(); 
        
        
        
    }


    public void compenylogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass().getResource("CompenyLogIn.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage ();
        stage.setScene (new Scene (root));
        stage.show();
    }
}
    
