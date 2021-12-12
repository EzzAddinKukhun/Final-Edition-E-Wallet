package software_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;


public class NewBill {
    private  String BID;
    private String comname;
   @FXML
    private TextField amount;
   @FXML
   private TextField Date;
   @FXML
   private TextField Ser_name;
   @FXML
   private Label label;
    private String uniform_resource_locator= "jdbc:mysql://localhost:3306/e-wallet?useSSL=true";
    private String user = "root";
    private String pass = "mohamadw22:(";

    public void newBill(ActionEvent event) {
        if (amount.getText().isEmpty() || Date.getText().isEmpty() || Ser_name.getText().isEmpty())
            label.setText("please fill all the fields above");
        else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(this.uniform_resource_locator, user, pass);
                Statement stmt = connection.createStatement();
                String selectuserdata = "SELECT name FROM companies where id='"+BID+"';";
                ResultSet rs =stmt.executeQuery(selectuserdata);
                rs.next();
                        comname=rs.getString(1);
                selectuserdata="insert into compeny_bills (service_name,company_name,amount,date)values ('"+Ser_name.getText()+"','"+comname+"','"+amount.getText()+"','"+Date.getText()+"')";
                stmt.executeUpdate(selectuserdata);
                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("added sucssuflly");
                alert.setTitle("Done");
                alert.show();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void setBID(String BID) {
        this.BID = BID;
    }
}
