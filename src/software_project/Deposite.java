package software_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import java.net.URL;
import java.sql.*;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

public class Deposite implements Initializable {

     String uniform_resource_locator= "jdbc:mysql://localhost:3306/e-wallet?useSSL=true"; 
    String user = "Ezz_Addin_kukhun"; 
    String pass = "1092000waz"; 
    private long ad_no;
    private long userNo;
    private long userCredit;
    @FXML
    private Stage stage;
    @FXML
    private TextField userTextField;
    @FXML
    private TextField depTextField;

    @FXML
    private TextField withTeFie;

    @FXML
    private Label errorLabel;
    @FXML
    private Button depBut;
    @FXML
    private Button withBut;

    @FXML

    private void search() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(this.uniform_resource_locator, user, pass);
        Statement stmt = connection.createStatement();
        ResultSet rs = null;
        try {
             rs = stmt.executeQuery("select * from user where BankID =" + Integer.parseInt(this.userTextField.getText()));
            if (!rs.next()) throw new NoSuchElementException();
        } catch (NoSuchElementException ne) {
            errorLabel.setText("The id isn't found");
            errorLabel.setVisible(true);
            depBut.setDisable(true);
            withBut.setDisable(true);
            return;

        } catch (Exception e) {
            errorLabel.setText("The id isn't valid");
            errorLabel.setVisible(true);
            depBut.setDisable(true);
            withBut.setDisable(true);
            return;
        }try {

            rs = stmt.executeQuery("select * from user where BankID =" + Integer.parseInt(this.userTextField.getText()));
            rs.next();
            userNo = Integer.parseInt(this.userTextField.getText());
            userCredit = rs.getInt("amount");
            errorLabel.setVisible(false);
            depBut.setDisable(false);
            withBut.setDisable(false);
            connection.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "There's a problem");
        }
    }

    public void setAd_no(long id) {
        this.ad_no = id;
    }

    @FXML
    private void depFun(ActionEvent event) throws ClassNotFoundException, SQLException {
        int amount;
        try {
            amount = Integer.parseInt(this.depTextField.getText());
        }
    catch (Exception e){
        JOptionPane.showMessageDialog(null, "The amount isn't valid");
        return;
    }
        Connection connection =null;


            Class.forName("com.mysql.cj.jdbc.Driver");
             connection = DriverManager.getConnection(this.uniform_resource_locator, user, pass);
            connection.setAutoCommit(false);
        try {
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("insert into transactions values(?,?,?,?,?)");
            ps.setInt(1, amount);
            ps.setInt(2, (int)userNo);
            ps.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
            ps.setInt(4, (int) amount);
            ps.setBoolean(5, false);
            ps.executeUpdate();

            stmt.executeUpdate("UPDATE user " +
                    "SET amount = " + (userCredit + amount) +
                    " WHERE BankID =  " + userNo);
            connection.commit();
            JOptionPane.showMessageDialog(null,"Deposit succeeded");
            stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "There's a problem");
            connection.rollback();
        }
    }

    @FXML
    private void withFun(ActionEvent event)  throws ClassNotFoundException, SQLException {
            int amount;
            try {

                amount = Integer.parseInt(this.withTeFie.getText());
                if (amount > userCredit) throw new SecurityException();
            }
            catch (SecurityException e){
                JOptionPane.showMessageDialog(null, "The user's credit isn't enough");
                return;
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, "The amount isn't valid");
                return;
            }
            Connection connection =null;


            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(this.uniform_resource_locator, user, pass);
            connection.setAutoCommit(false);
            try {
                Statement stmt = connection.createStatement();
                PreparedStatement ps = connection.prepareStatement("insert into transactions values(?,?,?,?,?)");
                ps.setInt(1, amount);
                ps.setInt(2, (int)userNo);
                ps.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
                ps.setInt(4,  amount);
                ps.setBoolean(5, true);
                ps.executeUpdate();

                stmt.executeUpdate("UPDATE user " +
                        "SET amount = " + (userCredit - amount) +
                        " WHERE BankID =  " + userNo);
                connection.commit();
                JOptionPane.showMessageDialog(null,"Withdraw succeeded");
                stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.close();
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, "There's a problem");
                connection.rollback();
            }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.withBut.setDisable(true);
        this.depBut.setDisable(true);

    }
}
