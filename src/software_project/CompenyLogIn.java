package software_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class CompenyLogIn implements Initializable {
   @FXML
    private Label label;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView image;

    @FXML
    private TextField companyID;

  String uniform_resource_locator= "jdbc:mysql://localhost:3306/e-wallet?useSSL=true"; 
    String user = "Ezz_Addin_kukhun"; 
    String pass = "1092000waz"; 

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

 public void go_on(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {


     if (password.getText().isEmpty() || companyID.getText().isEmpty()){
         label.setText("please fill in all of the above ");
        }
     else {
     try {
     label.setText("");

       Class.forName("com.mysql.cj.jdbc.Driver");
       Connection connection = DriverManager.getConnection(this.uniform_resource_locator, user, pass);
       Statement stmt = connection.createStatement();
       String selectuserdata = "SELECT id,password FROM companies where id='" + companyID.getText() + "' and password ='" + password.getText() + "';";
       ResultSet rs = stmt.executeQuery(selectuserdata);
       if (rs.next()){
           FXMLLoader loader = new FXMLLoader (getClass().getResource("CompanyMainVeiw.fxml"));
           Parent root = loader.load();
           CompanyMainVeiw userinterface = loader.getController();
           userinterface.setCompany_name1(companyID.getText());
           Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
           Scene scene = new Scene (root);
           stage.setScene(scene);
           stage.show();
       }
     }
     catch (SQLException exception){
      Alert alert = new Alert (Alert.AlertType.ERROR);
      alert.setTitle("Failed");
      alert.setContentText(    "this ID or password doesn't exist"      );
      alert.showAndWait();

     } catch (IOException e) {
         e.printStackTrace();
     }
     }
 }
}
