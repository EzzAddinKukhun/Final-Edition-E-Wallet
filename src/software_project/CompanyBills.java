package software_project;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CompanyBills implements Initializable {

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
    private javafx.scene.control.Label bankid;
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
    private TableColumn<BillInformations, String> Select;
    @FXML
    private Label Label;
    private String BID ;

    public void refresh(MouseEvent mouseEvent)  {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(this.uniform_resource_locator, user, pass);
            Statement stmt = connection.createStatement();


            List = FXCollections.observableArrayList();



            String select = "SELECT  name ,bank_id FROM companies cp where  id ='"+BID+"' ;";


            ResultSet rs1 = stmt.executeQuery(select);
            rs1.next();
            String ss=rs1.getString(1);
            String ss1=rs1.getString(2);
            bankid.setText(ss1);
            select = "SELECT  * FROM compeny_bills cp where  company_name ='"+ss+"' ;";
            ResultSet rs = stmt.executeQuery(select);
            while (rs.next()) {
                List.add(new BillInformations("" + rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                this.ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
                this.Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
                this.Service.setCellValueFactory(new PropertyValueFactory<>("Service"));
                this.Company.setCellValueFactory(new PropertyValueFactory<>("Company"));
                this.Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
                this.Select.setCellValueFactory(new PropertyValueFactory<>("Select"));
                this.table.setItems(List);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void New(MouseEvent mouseEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader (getClass().getResource("NewBill.fxml"));
        Parent root = loader.load();
        NewBill accept = loader.getController();
        accept.setBID(BID);
        stage1 = new Stage ();
        stage1.setScene (new Scene(root));
        stage1.show();




    }

    public void setBID(String BID) {
        this.BID = BID;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(this.uniform_resource_locator, user, pass);
            Statement stmt = connection.createStatement();
            String selectuserdata = "SELECT  * FROM compeny_bills cp where  id ='"+BID+"' ;";
            ResultSet rs = stmt.executeQuery(selectuserdata);
            List = FXCollections.observableArrayList();

            while (rs.next()) {
                List.add(new BillInformations("" + rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                this.ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
                this.Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
                this.Service.setCellValueFactory(new PropertyValueFactory<>("Service"));
                this.Company.setCellValueFactory(new PropertyValueFactory<>("Company"));
                this.Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
                this.Select.setCellValueFactory(new PropertyValueFactory<>("Select"));
                this.table.setItems(List);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delet(MouseEvent mouseEvent) {
        ObservableList <BillInformations> RemoveList = FXCollections.observableArrayList();

        for (BillInformations bean : List)
        {
            if(bean.getSelect().isSelected())
            {
                RemoveList.add(bean);
               try{
                   Class.forName("com.mysql.cj.jdbc.Driver");
                   Connection connection = DriverManager.getConnection(this.uniform_resource_locator, user, pass);
                   Statement stmt = connection.createStatement();
                   String selectuserdata = "DELETE FROM compeny_bills WHERE (`id` ='"+bean.getID()+"') ;";
                   stmt.executeUpdate(selectuserdata);


               } catch (SQLException e) {
                   e.printStackTrace();
               } catch (ClassNotFoundException e) {
                   e.printStackTrace();
               }
            }
        }

    }
}
