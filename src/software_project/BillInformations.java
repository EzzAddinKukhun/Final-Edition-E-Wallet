/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_project;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author HP2020
 */
public class BillInformations {
    
   
    public String ID ; 
    private String Date ; 
    private String servicename ; 
    private String companyname ; 
    private String amount; 
    
    CheckBox checkbox; 
    
    public BillInformations(String ID ,String servicename,String companyname, String amount ,String Date) {
        this.ID = ID; 
        this.Date = Date ; 
        this.servicename = servicename;
        this.companyname = companyname;
        this.amount = amount ; 
        checkbox = new CheckBox (); 
    }
    
    
    public String getID ()
    {
        return  this.ID; 

    }
    
    public String getDate ()
    {
        return  this.Date; 

    }
    
    
    public String getCompany ()
    {
        return  this.companyname; 

    }
    
    public String getService ()
    {
        return  this.servicename; 

    }
    
    
    public String getAmount ()
    {
        return this.amount; 
    }
    
    public CheckBox getSelect ()
    {
       return this.checkbox;  
    }
    
    
    
}
