/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_project;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP2020
 */
public class Bills implements Initializable {
    ObservableList <BillInformations> List ;
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
    String BID ="";
    /**
     * Initializes the controller class.
     */
    
    public void setBankID (String BankID) throws ClassNotFoundException, SQLException
    {
        this.bankid.setText(BankID);
        BID = BankID; 
        
         
            Class.forName("com.mysql.cj.jdbc.Driver");
              Connection connection = DriverManager.getConnection(this.uniform_resource_locator,user,pass); 
             Statement stmt = connection.createStatement();   
             String selectuserdata = "SELECT  * FROM compeny_bills cp where  company_name not in (SELECT  company_name FROM bills b where b.sender_id="+this.bankid.getText()+");";
             ResultSet rs = stmt.executeQuery(selectuserdata);
                       List = FXCollections.observableArrayList();

           while (rs.next())
         {
         List.add(new BillInformations (""+rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
         this.ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
         this.Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
         this.Service.setCellValueFactory(new PropertyValueFactory<>("Service"));
         this.Company.setCellValueFactory(new PropertyValueFactory<>("Company"));
         this.Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
         this.Select.setCellValueFactory(new PropertyValueFactory<>("Select"));
         this.table.setItems(List);
         }
            
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(this.uniform_resource_locator,user,pass);
            Statement stmt = connection.createStatement();
            String selectuserdata ="select name from companies";

            ResultSet rs = stmt.executeQuery(selectuserdata);
            while (rs.next()){this.companies.getItems().add(rs.getString(1));}


            
            
            
        }
        catch (Exception ex) {
            Logger.getLogger(Bills.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
    }


    @FXML
    private void Pay(MouseEvent event) throws IOException {
            
     
       FXMLLoader loader = new FXMLLoader (getClass().getResource("Accept.fxml"));  
        Parent root = loader.load(); 
            Accept accept = loader.getController(); 
             accept.setInfo(this);
             stage1 = new Stage (); 
             stage1.setScene (new Scene (root)); 
             stage1.show(); 
           


    }
    
    public void setReply (int x) throws SQLException, ClassNotFoundException
    {
        this.reply=x;
        stage1.close();
        
         Class.forName("com.mysql.cj.jdbc.Driver");
              Connection connection = DriverManager.getConnection(this.uniform_resource_locator,user,pass); 
             Statement stmt = connection.createStatement();   
           
        if (reply == 0)
        {
            
        }
        
        else if (reply ==1)
        {
          
         ObservableList <BillInformations> RemoveList = FXCollections.observableArrayList();
             
     for (BillInformations bean : List)
     {
         if(bean.getSelect().isSelected())
         {
             RemoveList.add(bean); 
         }
     }
     
     
     for (int i=0; i<RemoveList.size() ; i++)
     {
        
         String ID = RemoveList.get(i).ID;      
         int currentamount = Integer.parseInt(RemoveList.get(i).getAmount()); 
         
         String select_person = "select * from user where BankID='"+this.bankid.getText()+"'"; 
         ResultSet rs = stmt.executeQuery(select_person); 
         rs.next(); 
         
         int databaseamount = rs.getInt(15);
         
         if (currentamount > databaseamount)
         {
              Alert alert = new Alert (Alert.AlertType.ERROR);
             alert.setTitle("Failed");
             alert.setContentText(    "you haven't enough money in your account to pay this bill "       ); 
             alert.showAndWait(); 
              
             
         }
         
         else
         {
             for (int jj=0;jj<RemoveList.size();jj++){
           int cc= Integer.parseInt(RemoveList.get(jj).getAmount());
           String cc1=RemoveList.get(jj).getCompany();
           String update = " update companies set money ='"+cc+"' where name='"+cc1+"'";
                 stmt.executeUpdate(update);
             }
             Alert alert = new Alert (Alert.AlertType.INFORMATION);
             alert.setTitle("done");
             alert.setContentText(    "you have  paid this bill successfully "       );
             alert.showAndWait();
             int newvalue =  databaseamount -  currentamount;

         String update = "update user set amount='"+newvalue+"' where BankID='"+this.bankid.getText()+"'"; 
         stmt.executeUpdate(update);
             SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
             java.util.Date date1 = new Date();



             String deleteTask = "insert into bills(date,amount,company_name,sender_id,service_name) values ('";
             deleteTask=deleteTask+formatter.format(date1)+"','"+RemoveList.get(i).getAmount()+"','"+RemoveList.get(i).getCompany()+"','"+this.bankid.getText()+"','"+RemoveList.get(i).getService()+"')";
             stmt.executeUpdate(deleteTask);



         
         }
     }
     
        List.removeAll(RemoveList); 
        List = FXCollections.observableArrayList();
            String select = "SELECT  * FROM compeny_bills cp where  company_name not in (SELECT  company_name FROM bills b where b.sender_id="+this.bankid.getText()+");";


         ResultSet rs = stmt.executeQuery(select);

            while (rs.next())
            {
                List.add(new BillInformations (""+rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
                this.ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
                this.Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
                this.Service.setCellValueFactory(new PropertyValueFactory<>("Service"));
                this.Company.setCellValueFactory(new PropertyValueFactory<>("Company"));
                this.Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
                this.Select.setCellValueFactory(new PropertyValueFactory<>("Select"));
                this.table.setItems(List);
            }
     
     
     
     
     
        }
        

    }

   
    @FXML
    private void search(ActionEvent event) throws ClassNotFoundException, SQLException {
         Class.forName("com.mysql.cj.jdbc.Driver");
              Connection connection = DriverManager.getConnection(this.uniform_resource_locator,user,pass); 
             Statement stmt = connection.createStatement();   
          
        
         List = FXCollections.observableArrayList();
         String way = this.companies.getValue();

        String select = "SELECT  * FROM compeny_bills cp where  company_name='"+way+"'and  company_name not in (SELECT  company_name FROM bills b where b.sender_id="+this.bankid.getText()+");";

            int jj=0;
         ResultSet rs = stmt.executeQuery(select);

         while (rs.next())
         {
             jj=1;
             List.add(new BillInformations (""+rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
             this.ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
             this.Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
             this.Service.setCellValueFactory(new PropertyValueFactory<>("Service"));
             this.Company.setCellValueFactory(new PropertyValueFactory<>("Company"));
             this.Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
             this.Select.setCellValueFactory(new PropertyValueFactory<>("Select"));
             this.table.setItems(List);
         }
         if (jj==0)  Label.setText("this search is empty");

         }

    @FXML
    private void refresh(MouseEvent event) throws SQLException, ClassNotFoundException {
        Label.setText("");
          Class.forName("com.mysql.cj.jdbc.Driver");
              Connection connection = DriverManager.getConnection(this.uniform_resource_locator,user,pass); 
             Statement stmt = connection.createStatement();   
          
        
         List = FXCollections.observableArrayList();
         String way = this.companies.getValue();


        String select = "SELECT  * FROM compeny_bills cp where  company_name not in (SELECT  company_name FROM bills b where b.sender_id="+this.bankid.getText()+");";


        ResultSet rs = stmt.executeQuery(select);

        while (rs.next())
        {
            List.add(new BillInformations (""+rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            this.ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            this.Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
            this.Service.setCellValueFactory(new PropertyValueFactory<>("Service"));
            this.Company.setCellValueFactory(new PropertyValueFactory<>("Company"));
            this.Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
            this.Select.setCellValueFactory(new PropertyValueFactory<>("Select"));
            this.table.setItems(List);
        }


    }
        
    
    
}


