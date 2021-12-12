/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_project;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author HP2020
 */
public class NewUser implements Initializable {

   String uniform_resource_locator= "jdbc:mysql://localhost:3306/e-wallet?useSSL=true"; 
    String user = "Ezz_Addin_kukhun"; 
    String pass = "1092000waz"; 
    
    @FXML
    private DatePicker birthdate;
    @FXML
    private ComboBox<String> country;
    @FXML
    private TextField  password;
    @FXML
    private TextField zipcode;
    @FXML
    private TextField verification_password;
    @FXML
    private TextField ccn;
    @FXML
    private TextField  username;
    @FXML
    private TextField  email;
    @FXML
    private ComboBox<String> city;
    @FXML
    private TextField  cpassword;
    @FXML
    private TextField  bankID;
    @FXML
    private TextField  cvpassword;
    @FXML
    private TextField  FirstName;
    @FXML
    private TextField  ssn1;
    @FXML
    private TextField  LastName;
    @FXML
    private Label war;
    @FXML
    private TextField  amount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.country.getItems().add("Palestine");
        this.country.getItems().add("Jordan");
        this.country.getItems().add("Syria");
        this.country.getItems().add("Lebanon");
        this.country.getItems().add("Egypt");
        this.country.getItems().add("Iraq");

        
        this.city.getItems().add("Jerusalem");
        this.city.getItems().add("Nablus");
        this.city.getItems().add("Ramallah");
        this.city.getItems().add("Tulkarem");
        this.city.getItems().add("Jenin");
        this.city.getItems().add("Hebron");
        this.city.getItems().add("Gaza");


        
        
        
    }    

    @FXML
    private void saveData(ActionEvent event) throws ClassNotFoundException, SQLException, ParseException {
        
       
        
        
          if (this.FirstName.getText().isEmpty() || this.LastName.getText().isEmpty() || this.ssn1.getText().isEmpty() ||
                this.email.getText().isEmpty() ||
                this.username.getText().isEmpty() || this.birthdate.getValue()==null || this.email.getText().isEmpty() ||
                this.verification_password.getText().isEmpty() || this.password.getText().isEmpty() ||this.cpassword.getText().isEmpty() || 
                 this.country.getSelectionModel().isEmpty() || this.city.getSelectionModel().isEmpty() || this.cvpassword.getText().isEmpty()
                  || this.zipcode.getText().isEmpty() || this.bankID.getText().isEmpty() || this.ccn.getText().isEmpty()
                  || this.amount.getText().isEmpty()
                )
        {
            this.war.setText("Please Fill All Reqiured Data");
            this.war.setVisible(true);
        }
          
          
          else if (!this.password.getText().equals(this.cpassword.getText()))
          {
             this.war.setText("Passwords don't match");
            this.war.setVisible(true); 
          }
              
          else if (!this.verification_password.getText().equals(this.cvpassword.getText()))
          {
             this.war.setText("Verfication Passwords don't match");
            this.war.setVisible(true); 
          }
          
          else
          {
             
              try
              {
              Class.forName("com.mysql.cj.jdbc.Driver"); 
             Connection connection = DriverManager.getConnection(this.uniform_resource_locator,user,pass); 
             Statement stmt = connection.createStatement();
              
              String insertuserdata = "insert into user" +
                      "(SSN,Fname,Lname,username,password,birthdate,email,country,city,zip_code,verification_password,BankID,CCN,blocking,amount)" +
                      " values('"+this.ssn1.getText()+"','"+this.FirstName.getText()+"','"+this.LastName.getText()+"','"+
                     this.username.getText()+"','"+this.password.getText()+"','"+this.birthdate.getValue().toString()+"','"+this.email.getText()+"','"+
                     this.country.getValue().toString()+"','"+this.city.getValue().toString()+"','"+this.zipcode.getText()+"','"+this.verification_password.getText()+"','"+
                     this.bankID.getText() +"','" + this.ccn.getText() +"','0','"+this.amount.getText()+"')"; 
                 
                  stmt.executeUpdate(insertuserdata);
                  
                  Alert alert = new Alert (Alert.AlertType.INFORMATION);
             alert.setTitle("SUCCESS");
             alert.setContentText(
                    "New User added to the E-Wallet Successfully"
             );
             
             alert.showAndWait(); 
              }
              catch (SQLException sql)
              {
                
                    Alert alert = new Alert (Alert.AlertType.ERROR);
             alert.setTitle("Failure");
             alert.setContentText(
                    "The SSN or CCN is already exist"
             );
                  
                  
              }
          }
        
    }
    
}
