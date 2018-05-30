/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacct2;

import com.sun.istack.internal.Pool;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Netho
 */
public class databaseconection {
    
    public  Connection connection;
    public Object iduser;
    
 


public void MySQLConnection() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection =DriverManager.getConnection("jdbc:mysql://127.0.0.1/consultorio?" +
            	                                   "user=root&password=");
            System.out.println( "connection granted!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(databaseconection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(databaseconection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    


 public void closeConnection() {
        try {
            connection.close();
            System.out.println( "connection close");
        } catch (SQLException ex) {
            Logger.getLogger(databaseconection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
 
 public void insertData(String table_name, String name, String lastname, String contact,String email, int birthyear) {
        try {
            if(name.length()==0){
                System.out.print("is empty");
            
            }
            else{
            String Query = "INSERT INTO patient ( name_P , Lastname_P, Phone, email, birthyear) values ('"+name+"','"+lastname+"','"+contact+"','"+email+"','"+birthyear+"')";
            Statement st = connection.createStatement();
            st.executeUpdate(Query);}
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }
 
  public void deleteRecord( String table_name,int ID)throws  ClassNotFoundException, SQLException {
        try {
           String Query = "DELETE FROM " + table_name + " WHERE idPatient =  '"+ ID + "'";
            Statement st = connection.createStatement();
            st.executeUpdate(Query);
 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
        }
    }
  
   public void deleteRecordAppointment( String table_name,int ID)throws  ClassNotFoundException, SQLException {
        try {
           String Query = "DELETE FROM " + table_name + " WHERE idapointment =  '"+ ID + "'";
            Statement st = connection.createStatement();
            st.executeUpdate(Query);
 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error erasing specific regist");
        }
    }
   
   
     public void deleteRecordMessage( String table_name,int ID)throws  ClassNotFoundException, SQLException {
        try {
           String Query = "DELETE FROM " + table_name + " WHERE idMessages=  '"+ ID + "'";
            Statement st = connection.createStatement();
            st.executeUpdate(Query);
 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error erasing specific regist");
        }
    }
     
        public void deleteRecordBilling( String table_name,int ID)throws  ClassNotFoundException, SQLException {
        try {
           String Query = "DELETE FROM " + table_name + " WHERE idBilling=  '"+ ID + "'";
            Statement st = connection.createStatement();
            st.executeUpdate(Query);
 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error erasing specific regist");
        }
    }
        
      public void UpdatePayment(  int id ) {
        try {
           
            
            String Query = "update billing set Status='paid'   where idBilling ='"+id+"'";
       
            Statement st = connection.createStatement();
            st.executeUpdate(Query);
           
            JOptionPane.showMessageDialog(null, "Marked as Paid!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error of data Storage! ");
        }}
   
   
   
   public ResultSet  searchPatient(String nameP) throws ClassNotFoundException, SQLException{
       
     
       String Query="SELECT * FROM  patient  WHERE patient.name_P =  '"+ nameP + "'";
       Statement st=connection.createStatement();
       st.execute(Query);
       java.sql.ResultSet resultSet;
       resultSet = st.executeQuery(Query);
       ResultSet rs=resultSet;
       
       
       return rs;
  
       
   }
   
   
      public ResultSet  searchPatientbydoctor(String nameP) throws ClassNotFoundException, SQLException{
       
     
       String Query="select * from apointment inner join patient on apointment.Patient_idPatient=patient.idPatient inner join username on apointment.Username_idUsername=username.idUsername where patient.name_P='"+nameP+"'";
       Statement st=connection.createStatement();
       st.execute(Query);
       java.sql.ResultSet resultSet;
       resultSet = st.executeQuery(Query);
       ResultSet rs=resultSet;
       
       
       return rs;
  
       
   }
   
      
          public ResultSet  searchPatientinbill(String nameP) throws ClassNotFoundException, SQLException{
       
     
       String Query="select * from billing inner join patient on billing.Patient_idPatient=patient.idPatient INNER join apointment on apointment.idapointment=billing.Patient_idPatient inner join  username  on username.idUsername= apointment.Username_idUsername  where patient.name_P='"+nameP+"'";
       Statement st=connection.createStatement();
       st.execute(Query);
       java.sql.ResultSet resultSet;
       resultSet = st.executeQuery(Query);
       ResultSet rs=resultSet;
       
       
       return rs;
  
       
   }
   

       
       
       
   
  
 
          
         
    
         
           
           
         
           
       
            
            
            
   
  
  
  public ResultSet getValuesUsername(ResultSet r) throws SQLException {
        
            String Query = "SELECT * FROM  username" ;
            Statement st = connection.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            ResultSet rs=resultSet;
            
            
 
           return rs;
 
     
        
      
    }
  
  
  
    public ResultSet getValuesPatient() throws SQLException {
        
            String Query = "SELECT * FROM  patient" ;
            Statement st = connection.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            ResultSet rs=resultSet;
            
            
 
           return rs;
 
     
        
      
    }
    
    
        public ResultSet getValuesAppointment() throws SQLException {
        
            String Query = "select * from apointment inner join patient on apointment.Patient_idPatient=patient.idPatient inner join username on apointment.Username_idUsername=username.idUsername" ;
            Statement st = connection.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            ResultSet rs=resultSet;
            
            
 
           return rs;
 
     
        
      
    }
        
    public ResultSet getValuesMessage() throws SQLException {
        
            String Query = "Select * from messages inner join username on messages.Username_idUsername=username.idUsername inner join patient on patient.idPatient=messages.Patient_idPatient";
            Statement st = connection.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            ResultSet rs=resultSet;
            
            
 
           return rs;
 
     
        
      
    }
    
    
    
     public ResultSet getValuesMessageByDoctor(int id) throws SQLException {
        
            String Query = "Select * from messages inner join username on messages.Username_idUsername=username.idUsername inner join patient on patient.idPatient=messages.Patient_idPatient where username.idUsername='"+id+"'";
            Statement st = connection.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            ResultSet rs=resultSet;
            
            
 
           return rs;
 
     
        
      
    }
     
     
    public int logginValidation(String username , String password) throws ClassNotFoundException, Exception {
        int resultado=0;
        
        try {
           
            String Query = "select*from `username` where Username='"+username+"' and password='"+password+"'" ;
            
            Statement st = connection.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            if(resultSet.next()){
                resultado=1;
            }
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
        }
    return resultado;
    
    }
        
        
        

public void loggin(String username , String password) throws ClassNotFoundException, Exception {
        try {
            String Query = "select*from `username` where Username='"+username+"' and password='"+password+"'" ;
            Statement st = connection.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
           
           
            
            
            int row=0;
            
            while (resultSet.next()) {
               
               String depa=resultSet.getString("Department");
               String name=resultSet.getString("Username");
                iduser=resultSet.getString("idUsername");
               
               
                name.equalsIgnoreCase(username);
                 
               
                
                 if(depa.equals("doctor")){
                    
                    
                     new doctor_window();
                 }
                 
                 else if(depa.equals("reception")){
                     
                     new reception_window();
                     
                     
                 }
                 
                 else  if(depa.equals("billing")){
                     
                     new Billing_window();
                     
                     
                 }
                
               
                 
                 
                
                 
            
                 BufferedWriter bw=new BufferedWriter(new FileWriter("iduser.txt"));
                 bw.write((String) iduser);
                 bw.flush();
                 bw.close();
            
             
         
            }}
 
         catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
        }
       
  

}


      
        
 




 public void insertAppointment(  String date, String time, int idusername,int idpatient) {
        try {
           
            
            String Query = "INSERT INTO apointment ( date , time, Username_idUsername, Patient_idPatient) values ('"+date+"','"+time+"','"+idusername+"','"+idpatient+"');";
            Statement st = connection.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }}
    

  public void insertMessage(  String message,String status, String date, String time, int idusername,int idpatient) {
        try {
           
            
            String Query = "INSERT INTO messages ( Message,status,date,time, Username_idUsername, Patient_idPatient) values ('"+message+"','"+status+"','"+date+"','"+time+"','"+idusername+"','"+idpatient+"');";
            Statement st = connection.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "data Succesfull Storage !");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error of data Storage! ");
        }}
    


  public void ModifyMessage(  int msnid,String datetime ) {
        try {
           
            
            String Query = "update messages set status='read', Read_at='"+datetime+"'   where idmessages='"+msnid+"' ;";
       
            Statement st = connection.createStatement();
            st.executeUpdate(Query);
           
            JOptionPane.showMessageDialog(null, "Marked as read it!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error of data Storage! ");
        }}

  
    public void ModifyforMedication(  String medication, String date, String time, int id) {
        try {
           
            
            String Query = "update apointment set Medication='"+medication+"' where date='"+date+"' and time='"+time+"' and Patient_idPatient='"+id+"';";
       
            Statement st = connection.createStatement();
            st.executeUpdate(Query);
           
            JOptionPane.showMessageDialog(null, "Medication Approved!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error of data Storage! ");
        }}

    
      public ResultSet getValuesBillinghistory() throws SQLException {
        
            String Query = "select * from billing inner join patient on billing.Patient_idPatient=patient.idPatient INNER join apointment on apointment.idapointment=billing.Patient_idPatient inner join  username  on username.idUsername= apointment.Username_idUsername";
            Statement st = connection.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            ResultSet rs=resultSet;
            
            
 
           return rs;
 
     
        
      
    }
      
      
        public ResultSet getValuesBilling() throws SQLException {
        
            String Query = "select *from billing inner join patient on billing.Patient_idPatient=patient.idPatient inner join username on billing.Username_idUsername=username.idUsername";
            Statement st = connection.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            ResultSet rs=resultSet;
            
            
 
           return rs;
 
     
        
      
    }
        
        
        
        
        
        
          public void insertBill(  String charge, String status, String total,int idpatient,int idusername) {
        try {
           
            
            String Query = "INSERT INTO billing ( charge,Status,Total,Patient_idPatient,Username_idUsername) values ('"+charge+"','"+status+"','"+total+"','"+idpatient+"','"+idusername+"');";
            Statement st = connection.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "data Succesfull Storage !");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error of data Storage! ");
        }}
          
          
          
    
    
    
    

    
    
    
   
        
    }
     
     

 





    

