/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_project;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HP2020
 */
public class Accept implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    int response = -1;
    Bills bills; 
    
    
    public void setInfo (Bills object)
    {
        this.bills = object; 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Accept(MouseEvent event) throws SQLException, ClassNotFoundException {
        
        this.response =1; 
        this.bills.setReply(response);
    }

    @FXML
    private void notAccept(MouseEvent event) throws SQLException, ClassNotFoundException {
        
       this.response =0;
               this.bills.setReply(response);

    }
    
    
    public int reply ()
    {
        return response; 
    }

    
    
}
