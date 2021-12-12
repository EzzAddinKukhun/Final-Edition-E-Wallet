package software_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ezz {
    String uniform_resource_locator= "jdbc:mysql://localhost:3306/e-wallet?useSSL=true"; 
    String user = "Ezz_Addin_kukhun"; 
    String pass = "1092000waz"; 

    @FXML
    private TextField amountTf;

    @FXML
    private TextField dateTf;

    @FXML
    private Button exchangeBtn;

    @FXML
    private TextField fromcurrencyTf;

    @FXML
    private TextField idTf;

    @FXML
    private TextField timeTf;

    @FXML
    private TextField tocurrencyTf;

    @FXML
    private Label statusLabel;

    @FXML
    void exchangeBtnPressed(ActionEvent event) {
        String exchangetocurrency ;
        float convertedamountusd = 0, convertedamountjd = 0, convertedamountnis = 0;
        int databaseamountnis, databaseamountjd, databaseamountusd;
        int databaseamountusdnew=0, databaseamountjdnew=0, databaseamountnisnew=0;
        boolean converted = false;
        if (amountTf.getText().equals("") || dateTf.getText().equals("") ||
                fromcurrencyTf.getText().equals("")|| amountTf.getText().equals("0") || idTf.getText().equals("") ||
                tocurrencyTf.getText().equals("") || timeTf.getText().equals("")) {
            statusLabel.setText("Please make sure that all fields are filled");
        } else {


            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(this.uniform_resource_locator,user,pass);
                Statement stmt = connection.createStatement();
                String selectuserdata = "select * from user where bankID='"+this.idTf.getText()+"'";
                ResultSet rs = stmt.executeQuery(selectuserdata);

                if (!rs.next())
                    statusLabel.setText("Wrong BankID");


                else
                {
                    if(!((fromcurrencyTf.getText().equalsIgnoreCase("nis") || fromcurrencyTf.getText().equalsIgnoreCase("usd") ||
                    fromcurrencyTf.getText().equalsIgnoreCase("jd") )&&tocurrencyTf.getText().equalsIgnoreCase("nis") || tocurrencyTf.getText().equalsIgnoreCase("usd") ||
                    tocurrencyTf.getText().equalsIgnoreCase("jd"))){
                        statusLabel.setText("Wrong currency");
                    }

                    else
                    {
                        databaseamountnis = rs.getInt(17);
                        databaseamountjd = rs.getInt(16);
                        databaseamountusd = rs.getInt(15);
                        int hm = Integer.parseInt(amountTf.getText());
                        if(fromcurrencyTf.getText().equalsIgnoreCase("nis")){

                            if(databaseamountnis <  hm ){
                                statusLabel.setText("You don't have that amount");
                            }else{
                                exchangetocurrency = tocurrencyTf.getText();
                                if(exchangetocurrency.equalsIgnoreCase("usd")){
                                    convertedamountusd = (float) (Float.parseFloat(amountTf.getText()) *  0.32);
                                }
                                else if (exchangetocurrency.equalsIgnoreCase("jd")){
                                    convertedamountjd = (float) (Float.parseFloat(amountTf.getText()) *  0.23);
                                }converted = true;

                                convertedamountnis = (float) (Float.parseFloat(amountTf.getText()));


                                databaseamountusdnew = (int) (convertedamountusd + databaseamountusd);
                                databaseamountjdnew = (int) (convertedamountjd + databaseamountjd);
                                databaseamountnisnew = (int) (databaseamountnis - convertedamountnis) ;


                            }

                        }
                        else if(fromcurrencyTf.getText().equalsIgnoreCase("jd")){

                            if(databaseamountjd < hm ){
                                statusLabel.setText("You don't have that amount");
                            }else{
                                exchangetocurrency = tocurrencyTf.getText();
                                if(exchangetocurrency.equalsIgnoreCase("nis")){
                                    convertedamountnis = (float) (Float.parseFloat(amountTf.getText()) *  4.37);
                                }
                                else if (exchangetocurrency.equalsIgnoreCase("usd")){
                                    convertedamountusd = (float) (Float.parseFloat(amountTf.getText()) *  1.41);
                                }converted = true;

                                convertedamountjd = (float) (Float.parseFloat(amountTf.getText()));

                                databaseamountusdnew = (int) (convertedamountusd + databaseamountusd);
                                databaseamountjdnew = (int) ( databaseamountjd - convertedamountjd );
                                databaseamountnisnew = (int) (convertedamountnis + databaseamountnis ) ;
                            }

                        }
                        else if(fromcurrencyTf.getText().equalsIgnoreCase("usd")) {

                            if(databaseamountusd < hm ){
                                statusLabel.setText("You don't have that amount");
                            }else{
                                exchangetocurrency = tocurrencyTf.getText();
                                if(exchangetocurrency.equalsIgnoreCase("nis")){
                                    convertedamountnis = (float) (Float.parseFloat(amountTf.getText()) *  3.10);
                                }
                                else if (exchangetocurrency.equalsIgnoreCase("jd")){
                                    convertedamountjd = (float) (Float.parseFloat(amountTf.getText()) *  0.71);
                                }converted = true;

                                convertedamountusd = (float) (Float.parseFloat(amountTf.getText()));
                                databaseamountusdnew = (int) ( databaseamountusd - convertedamountusd );
                                databaseamountjdnew = (int) ( databaseamountjd + convertedamountjd );
                                databaseamountnisnew = (int) (convertedamountnis + databaseamountnis ) ;
                            }
                        } if(converted){

                        String updateUsertable = "update user set amountusd = '"+databaseamountusdnew+"' , amountjd = '"+databaseamountjdnew+"' , amountnis = '"+databaseamountnisnew+"' where bankid = '"+this.idTf.getText()+"'";
                        int updateUsertableRs = stmt.executeUpdate(updateUsertable);
                        String addtoExchangetable = "insert into exchanges (idexchanges , date_and_time, from_currency, to_currency, amount) values ('"+Integer.parseInt(idTf.getText())+"' , '"+timeTf.getText()+"//"+dateTf.getText()+"' , '"+fromcurrencyTf.getText()+"' , '"+tocurrencyTf.getText()+"' , '"+Integer.parseInt(amountTf.getText())+"')";
                        int addtoExchangetableRs = stmt.executeUpdate(addtoExchangetable);
                        statusLabel.setText("Done"); }

                    }
                }
            }
            catch (Exception e)
            {
                System.err.print(e);
            }
        }
    }
}


