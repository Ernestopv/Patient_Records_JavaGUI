/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacct2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Netho
 */
public class PendientBilling  extends JPanel{
     int click=0;
    int nextidpa=0;
    public PendientBilling() throws Exception{
        
          DefaultTableModel modelo= new DefaultTableModel();
        JTable table=new JTable();
        JScrollPane js=new JScrollPane(table);
       
       setLayout(new GridLayout(0,1));
      
       JPanel panelde1=new JPanel(new GridLayout(2,0));
      
       JPanel panel2=new JPanel(new BorderLayout());
       
       //order in panel 1
     
       
       
       
       panel2.setBackground(Color.gray);
       // welcome messaje
       
       JLabel welcome= new JLabel("Pendient  Bill ");
       welcome.setFont(new java.awt.Font("Tahoma", 0, 18));
     
       
       
       //buttons 
       
       JButton back=new JButton("back");
       back.setFont(new java.awt.Font("Tahoma", 10, 18));
       JButton paid=new JButton("Mark Bill as Paid");
       paid.setFont(new java.awt.Font("Tahoma", 10, 18));
       
       
       JPanel orderbuttons=new JPanel(new GridLayout(0,2));
       JPanel orderbuttons1=new JPanel(new BorderLayout());
       JPanel orderbuttons2=new JPanel(new BorderLayout());
       orderbuttons1.add(back, BorderLayout.CENTER);
       orderbuttons2.add(paid,BorderLayout.CENTER);
       
       orderbuttons.add(orderbuttons1);
       orderbuttons.add(orderbuttons2);
       
       
       
       
      
       
       
       
    
      
       //in the middle first panel 
       
         
         

        
        
     
        
        
     
       
       JPanel pane=new JPanel(new FlowLayout());
       pane.setBackground(Color.white);
       pane.add(welcome);
       panelde1.add(pane);
       panelde1.add(orderbuttons);
       add(panelde1);
       
       
       
       // panel 2 using now 
       
       JButton delete=new JButton("Delete row");
       delete.setFont(new java.awt.Font("Tahoma", 10, 18));
       panel2.add(delete, BorderLayout.PAGE_END);
       
       
       
      
       
       
       
       
       //table creation 
       
       
        modelo.addColumn("idB");
        modelo.addColumn("Total £");
        modelo.addColumn("status");
        modelo.addColumn("idP");
        modelo.addColumn("Patient Name");
        modelo.addColumn("Lastname");
          modelo.addColumn("Doctor");
          modelo.addColumn("D.id.");
        
        table.setModel(modelo);

       databaseconection dato=new databaseconection();
       ResultSet r;
       
       dato.MySQLConnection();
       r=dato.getValuesBilling();
       int rowCounter=0;
        Object data[] = new Object[1000];
        
        while(r.next()){
            
             String id=r.getString("idBilling");
             data[0]=id;
             String name=r.getString("Charge");
             data[1]=name;
             String lastname=r.getString("Status");
             data[2]=lastname;
             
             String doctor=r.getString("idPatient");
             data[3]=doctor;
             
             String date=r.getString("name_P");
             data[4]=date;
             
             String time=r.getString("Lastname_P");
             data[5]=time;
             
             String user=r.getString("Username");
             data[6]=user;
              String userid=r.getString("idUsername");
             data[7]=userid;
            
            
          
            
             rowCounter++;
             
             modelo.addRow(data);
           
            
        }
        
        TableColumnModel columnmodel=table.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(150);
        columnmodel.getColumn(4).setPreferredWidth(250);
        columnmodel.getColumn(2).setPreferredWidth(150);
         columnmodel.getColumn(5).setPreferredWidth(150);
          columnmodel.getColumn(6).setPreferredWidth(150);
       
        
        paid.setActionCommand("click");
        
        
        panel2.add(js,BorderLayout.NORTH);
        validate();
        repaint();

       add(panel2);
       dato.closeConnection();
       
      // action button adding appointment 
     
      
      paid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                databaseconection co=new databaseconection();
                try {
                    co.MySQLConnection();
                    int row=table.getSelectedRow();
                    String idpay=table.getValueAt(row, 0).toString();
                    int idpay2=Integer.parseInt(idpay);
                    co.UpdatePayment(idpay2);
                    co.closeConnection();
                    table.getModel().setValueAt("paid", row, 2);
                    validate();
                    repaint();
                    
                    
                    
                    
                } catch (Exception ex) {
                    Logger.getLogger(PendientBilling.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
               
            }
        });
       
       
       
       
       back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 setVisible(false);
            }
        });
       
       
       
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              databaseconection cone=new databaseconection();
              
                try {
                    cone.MySQLConnection();
                } catch (Exception ex) {
                    Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                   int row=table.getSelectedRow();
                  
                   
                   if(row>=0){
                   int n=JOptionPane.showConfirmDialog(null, "Are you sure that \n you want to delete??", "Warning!!!!",JOptionPane.YES_NO_OPTION);
                   
                    if(n==0){
                   String idtext=table.getValueAt(row, 0).toString();
                   int idp= Integer.parseInt(idtext);
                   cone.deleteRecordBilling("billing", idp);
                   modelo.removeRow(row);}
                    }
                    
                    else{
                      JOptionPane.showMessageDialog(null,"Please select row To Delete..");
                    }
                    cone.closeConnection();
                    
               } catch (ClassNotFoundException ex) {
                   Logger.getLogger(Tabla_patient.class.getName()).log(Level.SEVERE, null, ex);
               } catch (SQLException ex) {
                   Logger.getLogger(Tabla_patient.class.getName()).log(Level.SEVERE, null, ex);
               }
               
    
               
           
              
            }
            
        });
        
        
   
      
              
       
      
        
        
       
        
        
        
       
        
        
        
        
                
                
                
                
            }
     
      
        
        
        
        
        
        
        
        
        
    }
    
    
    

