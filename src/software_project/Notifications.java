/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author HP2020
 */
public class Notifications implements Initializable {

    @FXML
    private TableView<?> NotificationTable;
    @FXML
    private TableColumn<?, ?> ID;
    @FXML
    private TableColumn<?, ?> Date;
    @FXML
    private TableColumn<?, ?> Time;
    @FXML
    private TableColumn<?, ?> Type;
    @FXML
    private TableColumn<?, ?> Message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
