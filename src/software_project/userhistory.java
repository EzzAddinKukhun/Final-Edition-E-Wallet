package software_project;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.*;

public class userhistory {

    ObservableList<BillInformations> List ;
    int reply =-1;
    Stage stage1;
    private String bank_id;

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
    private TableView<BillInformations> table1;
    @FXML
    private JFXComboBox<String> companies1;

    @FXML
    private TableColumn<BillInformations, String> ID1;
    @FXML
    private TableColumn<BillInformations, String> Date1;
    @FXML
    private TableColumn<BillInformations, String> Service1;
    @FXML
    private TableColumn<BillInformations, String> Company1;
    @FXML
    private TableColumn<BillInformations, String> Amount1;
    @FXML
    private RadioButton bills;
    @FXML
    private RadioButton transfer;

    public void refresh(MouseEvent mouseEvent) throws ClassNotFoundException, SQLException {
        bankid.setText(bank_id);
if (bills.isSelected()){
    table1.setVisible(true);
    table.setVisible(false);
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection(this.uniform_resource_locator,user,pass);
    Statement stmt = connection.createStatement();
    String selectuserdata = "SELECT  * FROM bills cp where sender_id='"+bank_id+"';";
    System.out.print(selectuserdata);
    ResultSet rs = stmt.executeQuery(selectuserdata);
    List = FXCollections.observableArrayList();

    while (rs.next())
    {
        List.add(new BillInformations (""+rs.getInt(1),rs.getString(6),rs.getString(4),rs.getString(3),rs.getString(2)));
        this.ID1.setCellValueFactory(new PropertyValueFactory<>("ID"));
        this.Date1.setCellValueFactory(new PropertyValueFactory<>("Date"));
        this.Service1.setCellValueFactory(new PropertyValueFactory<>("Service"));
        this.Company1.setCellValueFactory(new PropertyValueFactory<>("Company"));
        this.Amount1.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        this.table1.setItems(List);
    }



    }




else if (transfer.isSelected()){
    table1.setVisible(false);
    table.setVisible(true);
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection(this.uniform_resource_locator,user,pass);
    Statement stmt = connection.createStatement();
    String selectuserdata = "SELECT * FROM transfer where reciever_id ='"+bank_id+"' or sender_id ='"+ bank_id+ "';";
    System.out.print(selectuserdata);
    ResultSet rs = stmt.executeQuery(selectuserdata);
    List = FXCollections.observableArrayList();

    while (rs.next())
    {
        List.add(new BillInformations (""+rs.getInt(1),rs.getString(6),rs.getString(4),rs.getString(3),rs.getString(2)));
        this.ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        this.Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        this.Service.setCellValueFactory(new PropertyValueFactory<>("Service"));
        this.Company.setCellValueFactory(new PropertyValueFactory<>("Company"));
        this.Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        this.table1.setItems(List);
    }

}

    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }
}
