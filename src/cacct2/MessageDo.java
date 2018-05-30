/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacct2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Netho
 */
public class MessageDo extends JFrame{
    
   
    
    public MessageDo() throws Exception{
        setTitle("Messages");
        int idnumber=0;
        setVisible(true);
        setSize(700, 500);
        setLocationRelativeTo(null);
        
         DefaultTableModel modelo= new DefaultTableModel();
        JTable table=new JTable();
        JScrollPane js=new JScrollPane(table);
        
        setLayout(new GridLayout(0,1));
        
        JPanel uno =new JPanel(new BorderLayout());
        
        JPanel dos=new JPanel(new BorderLayout());
        
        
        uno.setBackground(Color.white);
        dos.setBackground(Color.LIGHT_GRAY);
        
        // table creation 
        
        modelo.addColumn("Id");
        modelo.addColumn("Message");
        modelo.addColumn("doctor_Id:");
        modelo.addColumn("To doctor: ");
        modelo.addColumn("Patient_Id");
        modelo.addColumn("From Patient:");
        modelo.addColumn("Date");
        modelo.addColumn("Time");
        table.setModel(modelo);
        
        databaseconection cons=new databaseconection();
        ResultSet r= null;
        cons.MySQLConnection();
        // we use buffereader to read the doctor id in the text file because we need the userid variable 
        
        Scanner reader=new Scanner(new File("iduser.txt"));
        
        
        String texto=reader.nextLine();
    
        
        idnumber=Integer.parseInt(texto);
        
           
       
        
      
 
        r=cons.getValuesMessageByDoctor(idnumber);
        
        
        int rowCounter=0;
        Object data[] = new Object[1000];
        while(r.next()){
            
            String id=r.getString("idMessages");
             data[0]=id;
             String message=r.getString("Message");
             data[1]=message;
         
             
             String iddoctor=r.getString("idUsername");
             data[2]=iddoctor;
             
             String doctorname=r.getString("Username");
             data[3]=doctorname;
             
             String idpatient=r.getString("idPatient");
             data[4]=idpatient;
            
             String patientname=r.getString("name_P");
             data[5]=patientname;
             
              String date =r.getString("date");
             data[6]=date;
             
             String time =r.getString("time");
             data[7]=time;
            
             rowCounter++;
             
             modelo.addRow(data);
           
            
        }
        
        cons.closeConnection();
        
        TableColumnModel columnmodel=table.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(550);
        columnmodel.getColumn(2).setPreferredWidth(250);
        columnmodel.getColumn(3).setPreferredWidth(200);
        columnmodel.getColumn(4).setPreferredWidth(200);
        columnmodel.getColumn(5).setPreferredWidth(200);
        columnmodel.getColumn(6).setPreferredWidth(200);
        columnmodel.getColumn(7).setPreferredWidth(150);
        
        
        
        
        dos.add(js,BorderLayout.CENTER);
        validate();
        repaint();
        
        
        JButton back =new JButton("Back");
        back.setFont(new java.awt.Font("Tahoma", 10, 16));
        
        dos.add(back, BorderLayout.PAGE_END);
        
       
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
             
        
        
       
                setVisible(false);
                
                
                
               
                
                
                
            }
        });
        
        
        
        
        add(dos);
        
        
        
        // adding listener a table 
        
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                int row=table.getSelectedRow();
                
                
                 if(row>-1){
                     
                     String messageid=table.getValueAt(row, 0).toString();
                     String messager=table.getValueAt(row, 1).toString();
                     String patientId=table.getValueAt(row, 4).toString();
                     String patientnames=table.getValueAt(row, 5).toString();
                     String datepa=table.getValueAt(row, 6).toString();
                     String timepat=table.getValueAt(row, 7).toString();
                     
                     
                     
                     int val=JOptionPane.showConfirmDialog(null,"Patient ID: "+patientId+" Name:"+patientnames+"\n"+ messager+"\n"+"Date:"+datepa+" Time:"+timepat,"Mark as read?",JOptionPane.YES_NO_OPTION);
                     
                     if(val==0){
                         
                         try {
                             cons.MySQLConnection();
                             
                             int idme=Integer.parseInt(messageid);
                             
                            DateFormat df = new SimpleDateFormat("HH:mm dd/MM/yy");
                            Calendar calobj = Calendar.getInstance();
                            String setdate=df.format(calobj.getTime());
                            
                            cons.ModifyMessage(idme,setdate);
                             cons.closeConnection();
                     
                             modelo.removeRow(row);
                             // updating table 
                            
                                  
                              
                             
                             
                             
                         } catch (Exception ex) {
                             Logger.getLogger(MessageDo.class.getName()).log(Level.SEVERE, null, ex);
                         }
                         
                         
                         
                     }
                     
                     else{
                         System.out.print("wrong!");
                     }
                     
                 }
                
                

            }
        });
        
        
        
        
    }
    
  
      
    
    
    
    
}
