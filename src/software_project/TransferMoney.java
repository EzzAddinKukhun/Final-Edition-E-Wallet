/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_project;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP2020
 */
public class TransferMoney implements Initializable {
 String uniform_resource_locator= "jdbc:mysql://localhost:3306/e-wallet?useSSL=true"; 
    String user = "Ezz_Addin_kukhun"; 
    String pass = "1092000waz"; 
    
    @FXML
    private TextField reciever;
    @FXML
    private DatePicker date;
    @FXML
    private TextField amount;
    @FXML
    private TextField vpassword;
    @FXML
    private Label war;
    @FXML
    private Label sender;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setInfo (String senderid)
    {
        this.sender.setText(senderid);
    }
    
    @FXML
    private void submitTransfering(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        if (this.reciever.getText().isEmpty() || this.date.getValue() == null || this.amount.getText().isEmpty()
                || this.vpassword.getText().isEmpty()
                )
            
        {
            this.war.setVisible(true);
            this.war.setText("Please Fill all required fields");
        }
        
        else
        {
           Class.forName("com.mysql.cj.jdbc.Driver"); 
             Connection connection = DriverManager.getConnection(this.uniform_resource_locator,user,pass); 
             Statement stmt = connection.createStatement();   
             String selectuserdata = "select * from user where bankID='"+this.reciever.getText()+"'"; 
             ResultSet rs = stmt.executeQuery(selectuserdata); 
             
             if (!rs.next())
             {
                 this.war.setVisible(true);
                 this.war.setText("The BankID of this Reciever doesn't exist in E-Wallet");
             }
             
           else
             {
                 selectuserdata = "select * from user where bankID='"+this.sender.getText()+"'"; 
                 rs = stmt.executeQuery(selectuserdata); 
                 rs.next(); 
                 
                 if (!this.vpassword.getText().equals(rs.getString(11)))
                 {
                     this.war.setVisible(true);
                     this.war.setText("Verification password isn't correct");
                 }
                 else
                 {
                     int databaseamount = rs.getInt(15);
                     int enteredamount = Integer.parseInt(this.amount.getText()); 
                     
                     System.out.print(databaseamount);
                     
                     
                     if (databaseamount < enteredamount)
                     {
                         this.war.setVisible(true);
                         this.war.setText("You don't have enough money");
                     }
                     
                     else
                     {
                         int newamount = databaseamount - enteredamount; 
                         String updateamount = "update user set amount='"+newamount+"' where BankID='"+this.sender.getText()+"'"; 
                         stmt.executeUpdate(updateamount);
                         
                         String selectReciever = "select * from user where BankID='"+this.reciever.getText()+"'";
                         ResultSet rs2 = stmt.executeQuery(selectReciever);
                         rs2.next(); 
                         int databaseamountreciever = rs2.getInt(15); 
                         int newamountreciever = enteredamount + databaseamountreciever; 
                         String updateamountreciever = "update user set amount='"+newamountreciever+"' where BankID='"+this.reciever.getText()+"'"; 
                         stmt.executeUpdate(updateamountreciever);
                         
                        String updateamount1 = "update user set amount='"+newamount+"' where BankID='"+this.sender.getText()+"'"; 

                        this.war.setVisible(false);
                                         

                         
                         String insertuserdata = "insert into transfer values('0','"+this.date.getValue()+"','"+this.amount.getText()+"','10','"+this.reciever.getText()+"','"+this.sender.getText()+"')"; 
                 
                  stmt.executeUpdate(insertuserdata);
                  
                  Alert alert = new Alert (Alert.AlertType.INFORMATION);
             alert.setTitle("SUCCESS");
             alert.setContentText(    "Transfer had been Success"       ); 
             alert.showAndWait(); 
             
                     }
                     
                 }
                 
                 
                 
             }
             
        }
        
        
        
    }
    
}
