package software_project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CompanyMainVeiw implements Initializable {

    @FXML
    private ImageView imagebill;
    @FXML
    private ImageView imagehistory;
   private String company_name1;
    String uniform_resource_locator= "jdbc:mysql://localhost:3306/e-wallet?useSSL=true"; 
    String user = "Ezz_Addin_kukhun"; 
    String pass = "1092000waz"; 

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection connection = DriverManager.getConnection(this.uniform_resource_locator, user, pass);
           Statement stmt = connection.createStatement();
           String selectuserdata = "SELECT name FROM companies where id='" +company_name1+ "';";
           ResultSet rs = stmt.executeQuery(selectuserdata);
           if (rs.next())
           {
           }
       }
        catch (SQLException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }



    }


    public void setCompany_name1(String company_name1) {
        this.company_name1 = company_name1;
    }


    public void go_to_bills(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass().getResource("CompanyBills.fxml"));
        Parent root = loader.load();
        CompanyBills userinterface = loader.getController();
        userinterface.setBID(company_name1);
        Stage stage = new Stage();
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();

    }

    public void go_to_history(MouseEvent mouseEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader (getClass().getResource("CompenyHistory.fxml"));
        Parent root = loader.load();
        CompanyHistory userinterface = loader.getController();
        userinterface.setBID(company_name1);
        Stage stage = new Stage();
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();

    }
}
