package software_project;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.sql.*;

public class CompanyHistory {
    ObservableList<BillInformations> List ;
    int reply =-1;
    Stage stage1;

     String uniform_resource_locator= "jdbc:mysql://localhost:3306/e-wallet?useSSL=true"; 
    String user = "Ezz_Addin_kukhun"; 
    String pass = "1092000waz"; 
    @FXML
    private TableView<BillInformations> table;
    @FXML
    private JFXComboBox<String> companies;
    @FXML
    private Label bankid;
    @FXML
    private Label bankid1;
    @FXML
    private TableColumn<BillInformations, String> ID;
    @FXML
    private TableColumn<BillInformations, String> Date;
    @FXML
    private TableColumn<BillInformations, String> Service;
    @FXML
    private TableColumn<BillInformations, String> Company;
    @FXML
    private TableColumn<BillInformations, String> Amount;

    @FXML
    private javafx.scene.control.Label Label;
    private String BID ;


    public void refresh(MouseEvent mouseEvent) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(this.uniform_resource_locator, user, pass);
            Statement stmt = connection.createStatement();


            List = FXCollections.observableArrayList();



            String select = "SELECT  name ,bank_id,money FROM companies cp where  id ='"+BID+"' ;";


            ResultSet rs1 = stmt.executeQuery(select);
            rs1.next();
            String ss=rs1.getString(1);
            String ss1=rs1.getString(2);
            int cc12=rs1.getInt(3);

            bankid.setText(ss1);
            bankid1.setText(""+cc12);
            select = "SELECT  * FROM bills cp where  company_name ='"+ss+"' ;";
            ResultSet rs = stmt.executeQuery(select);
            while (rs.next()) {
                List.add(new BillInformations("" + rs.getInt(1), rs.getString(6), rs.getString(5), rs.getString(3), rs.getString(2)));
                this.ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
                this.Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
                this.Service.setCellValueFactory(new PropertyValueFactory<>("Service"));
                this.Company.setCellValueFactory(new PropertyValueFactory<>("Company"));
                this.Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));

                this.table.setItems(List);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

    public void setBID(String BID) {
        this.BID = BID;
    }

}
