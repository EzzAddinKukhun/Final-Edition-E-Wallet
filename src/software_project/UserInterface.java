/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_project;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP2020
 */
public class UserInterface implements Initializable {
    String uniform_resource_locator= "jdbc:mysql://localhost:3306/e-wallet?useSSL=true"; 
    String user = "Ezz_Addin_kukhun"; 
    String pass = "1092000waz"; 
    
    
    @FXML
    private Label username;
    @FXML
    private Label bankID;

    /**
     * Initializes the controller class.
     */
    
    public void setInfo (String BankId) throws ClassNotFoundException, SQLException
    {
        this.bankID.setText(BankId);
        Class.forName("com.mysql.cj.jdbc.Driver"); 
             Connection connection = DriverManager.getConnection(this.uniform_resource_locator,user,pass); 
             Statement stmt = connection.createStatement();   
             String selectuserdata = "select * from user where bankID='"+BankId+"'"; 
             ResultSet rs = stmt.executeQuery(selectuserdata); 
             rs.next(); 
             this.username.setText(rs.getString(4)); 
             
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void transferMoney(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader (getClass().getResource("TransferMoney.fxml"));  
        Parent root = loader.load(); 
            TransferMoney transfer = loader.getController(); 
            transfer.setInfo(this.bankID.getText());
             Stage stage = new Stage (); 
             stage.setScene (new Scene (root)); 
             stage.show(); 
        
        
    }

    @FXML
    private void payService(ActionEvent event) throws IOException, ClassNotFoundException, ClassNotFoundException, SQLException {
         FXMLLoader loader = new FXMLLoader (getClass().getResource("Bills.fxml"));  
          Parent root = loader.load(); 
            Bills bills = loader.getController(); 
            bills.setBankID(this.bankID.getText());
             Stage stage = new Stage (); 
             stage.setScene (new Scene (root)); 
             stage.show(); 
    }

    @FXML
    private void showTransactions(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass().getResource("userhistory.fxml"));
        Parent root = loader.load();
        userhistory bills = loader.getController();
        bills.setBank_id(this.bankID.getText());
        Stage stage = new Stage ();
        stage.setScene (new Scene (root));
        stage.show();
    }

    @FXML
    private void showNotifications(ActionEvent event) {

    }

    @FXML
    private void showExchangeRate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass().getResource("ezz.fxml"));
        Parent root = loader.load();
        ezz ezz = loader.getController();

        Stage stage = new Stage ();
        stage.setScene (new Scene (root));
        stage.show();
    }

    public void snake(MouseEvent mouseEvent) {
        new GameFrame();
    }
}
