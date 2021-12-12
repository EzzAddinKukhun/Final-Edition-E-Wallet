/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_project;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP2020
 */
public class NewCompany implements Initializable {
     String uniform_resource_locator= "jdbc:mysql://localhost:3306/e-wallet?useSSL=true"; 
    String user = "Ezz_Addin_kukhun"; 
    String pass = "1092000waz"; 

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField id1;
    @FXML
    private TextField password2;
    @FXML
    private TextField password;

    @FXML
    private TextField email;
    @FXML
    private TextField city;
    @FXML
    private TextField pnumber;
    @FXML
    private TextField name;
    @FXML
    private TextField country;
    @FXML
    private Label war;
    @FXML
    private  TextField Bank_ID;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SAVE(ActionEvent event) throws ClassNotFoundException, ParseException {


        if (        this.id1.getText().isEmpty() || this.name.getText().isEmpty()  ||
                    this.email.getText().isEmpty() || this.city.getText().isEmpty() ||
                    this.country.getText().isEmpty()  ||
                    this.password.getText().isEmpty() || this.pnumber.getText().isEmpty() ||
                    this.password2.getText().isEmpty() || this.Bank_ID.getText().isEmpty() )

        {
            this.war.setText("Please Fill All Reqiured Data");
            this.war.setVisible(true);
        } else if (!this.password.getText().equals(this.password2.getText())) {
            this.war.setText("Passwords don't match");
            this.war.setVisible(true);
        }
        else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(this.uniform_resource_locator,user,pass);
                Statement stmt = connection.createStatement();

                String insertuserdata = "insert into companies values ('"+id1.getText()+"','"
                        +name.getText()+"','"+password.getText()+"','"+email.getText()+"','"+pnumber.getText()+"','"
                        +country.getText()+"','"+city.getText()+"','"+Bank_ID.getText()+"','0');";


                stmt.executeUpdate(insertuserdata);

                Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setTitle("SUCCESS");
                alert.setContentText(
                        "New compeny added to the E-Wallet Successfully"
                );
                alert.show();



            }
            catch (SQLException sql){
                Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setTitle("failed");
                alert.setContentText(
                        "this ID already exists, or enter valid ID"
                );
                alert.show();

            }

        }
    }

    }
