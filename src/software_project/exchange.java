/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_project;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author HP2020
 */
public class exchange implements Initializable {

    @FXML
    private JFXTextField ID;
    @FXML
    private JFXDatePicker Date;
    @FXML
    private JFXTimePicker Time;
    @FXML
    private JFXComboBox<?> currency1;
    @FXML
    private JFXComboBox<?> currency2;
    @FXML
    private JFXTextField amount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    private exchange(ActionEvent event) {
    }
    
}
