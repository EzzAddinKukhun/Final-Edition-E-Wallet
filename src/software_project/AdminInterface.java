/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_project;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP2020
 */
public class AdminInterface implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Label DateToday;
    @FXML
    private Label CurrentTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Calendar calendar = Calendar.getInstance(); 
        
         this.DateToday.setText (""+calendar.get(Calendar.DATE)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR)); 
                  this.CurrentTime.setText (""+calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE) 
         
                  ); 

        
    }    

    @FXML
    private void addNewUser(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader (getClass().getResource("NewUser.fxml"));  
        Parent root = loader.load(); 
            NewUser m = loader.getController(); 
             Stage stage = new Stage (); 
             stage.setScene (new Scene (root)); 
             stage.show(); 
    }
    @FXML
    private void addNewCompney(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass().getResource("NewCompany.fxml"));
        Parent root = loader.load();
        NewCompany m = loader.getController();
        Stage stage = new Stage ();
        stage.setScene (new Scene (root));
        stage.show();
    }
    public void setInfo(String username)
    {
        this.username.setText(username);
    }

    public void deposit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass().getResource("Deposite.fxml"));
        Parent root = loader.load();
        Deposite m = loader.getController();
        Stage stage = new Stage ();
        stage.setScene (new Scene (root));
        stage.show();
    }
}
