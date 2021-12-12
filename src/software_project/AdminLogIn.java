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
import java.sql.SQLException;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP2020
 */
public class AdminLogIn implements Initializable {
    String uniform_resource_locator= "jdbc:mysql://localhost:3306/e-wallet?useSSL=true"; 
    String user = "Ezz_Addin_kukhun"; 
    String pass = "1092000waz"; 
    
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Label warning;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LogIn(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        
        if (this.password.getText().isEmpty() || this.username.getText().isEmpty())
        {
            this.warning.setVisible(true);
            this.warning.setText("Please Fill All Required Fields");
        }
        else
        {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
             Connection connection = DriverManager.getConnection(this.uniform_resource_locator,user,pass); 
             Statement stmt = connection.createStatement();   
             String selectuserdata = "select * from admin where username='"+this.username.getText()+"'"; 
             ResultSet rs = stmt.executeQuery(selectuserdata); 
             
             if (!rs.next())
             {
                 this.warning.setVisible(true);
             this.warning.setText("This username doesn't exist"); 
 
             }
             
             else
             {
                selectuserdata = "select * from admin where username='"+this.username.getText()+"'"; 
                ResultSet rs2 = stmt.executeQuery(selectuserdata);  
                rs2.next(); 
                
                if (!this.password.getText().equals(rs2.getString(3)))
                {
                     this.warning.setVisible(true);
             this.warning.setText("Password is incorrect"); 
                }
                
                else
                {
                    FXMLLoader loader = new FXMLLoader (getClass().getResource("AdminInterface.fxml"));  
            Parent root = loader.load(); 
            AdminInterface admininterface = loader.getController(); 
            admininterface.setInfo(this.username.getText());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
            Scene scene = new Scene (root); 
            stage.setScene(scene);
            stage.show();
                }
        }
        
        
    }
    
}
}