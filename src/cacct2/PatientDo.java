/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacct2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
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
public class PatientDo extends JFrame{
    
    public PatientDo(){
        
        
        
        
                setTitle("Patient");
                GridLayout order=new GridLayout(2,0);
                setLayout(order);
                setSize(600,500);
                setVisible(true);
                 DefaultTableModel modelo= new DefaultTableModel();
        JTable table=new JTable();
        JScrollPane js=new JScrollPane(table);
        
                
                 modelo.addColumn("id");
        modelo.addColumn("name");
        modelo.addColumn("lastname");
        modelo.addColumn("contact");
        modelo.addColumn("email");
        modelo.addColumn("year");
        
        table.setModel(modelo);

       databaseconection dato=new databaseconection();
       ResultSet r = null;
       
                try {
                    dato.MySQLConnection();
                } catch (Exception ex) {
                    Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    r=dato.getValuesPatient();
                } catch (SQLException ex) {
                    Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
                }
       int rowCounter=0;
        Object data[] = new Object[1000];
        
                try {
                    while(r.next()){
                        
                        String id=r.getString("idPatient");
                        
                        
                        data[0]=id;
                        String name=r.getString("name_P");
                        data[1]=name;
                        String lastname=r.getString("lastname_P");
                        data [2]=lastname;
                        String contact=r.getString("phone");
                        data [3]=contact;
                        String emai=r.getString("email");
                        data[4]=emai;
                        int birth=r.getInt("birthyear");
                        data[5]=birth;
                        
                        rowCounter++;
                        
                        modelo.addRow(data);
                        
                        
                    }       } catch (SQLException ex) {
                    Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        TableColumnModel columnmodel=table.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(150);
        columnmodel.getColumn(4).setPreferredWidth(250);
        columnmodel.getColumn(2).setPreferredWidth(150);
        columnmodel.getColumn(3).setPreferredWidth(150);
        
        
        
        
       add(js);
        validate();
        repaint();
        
      
       
        
         JLabel patientname=new JLabel("Name:");
       patientname.setHorizontalTextPosition(JLabel.CENTER);
       JTextField insername=new JTextField(20);
       patientname.setFont(new java.awt.Font("Tahoma", 10, 15));
       insername.setFont(new java.awt.Font("Tahoma", 10, 15));
       JLabel lastnamep=new JLabel("    lastname:");
       JTextField lastinsert=new JTextField(20);
       lastnamep.setFont(new java.awt.Font("Tahoma", 10, 15));
       lastinsert.setFont(new java.awt.Font("Tahoma", 10, 15));
       
   
       
       // order 
       
    

 
              
       
      
      
        
       
        
    
        
   
        
        
        
        
        JPanel or3=new JPanel(new BorderLayout());
        JButton search=new JButton("Search");
        search.setFont(new java.awt.Font("Tahoma", 10, 16));
        JPanel subor3=new JPanel(new FlowLayout());
        JLabel patientNam=new JLabel("Patient Name :");
        patientNam.setFont(new java.awt.Font("Tahoma", 10, 16));
        subor3.add(patientNam);
        JTextField napatie=new JTextField(20);
         napatie.setFont(new java.awt.Font("Tahoma", 10, 16));
         subor3.add(napatie);
         or3.add(subor3,BorderLayout.CENTER);
        
        
        or3.add(search,BorderLayout.PAGE_END);
        JButton reset=new JButton("Reset");
        reset.setFont(new java.awt.Font("Tahoma", 10, 16));
        subor3.add(reset);
        
        
        add(or3);
        
        
        
        reset.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        databaseconection co2=new databaseconection();
                        try {
                            
                            while (modelo.getRowCount()>0){
                                modelo.removeRow(0);}
                            
                            co2.MySQLConnection();
                        } catch (Exception ex) {
                            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        ResultSet rs=null;
                        try {
                            rs=co2.getValuesPatient();
                            
                            int rowCounter2=0;
                            
                            
                             while(rs.next()){
                        
                        String id=rs.getString("idPatient");
                        
                        
                        data[0]=id;
                        String name=rs.getString("name_P");
                        data[1]=name;
                        String lastname=rs.getString("lastname_P");
                        data [2]=lastname;
                        String contact=rs.getString("phone");
                        data [3]=contact;
                        String emai=rs.getString("email");
                        data[4]=emai;
                        int birth=rs.getInt("birthyear");
                        data[5]=birth;
                        
                        rowCounter2++;
                        
                        modelo.addRow(data);}
                             
                              TableColumnModel columnmodel=table.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(150);
        columnmodel.getColumn(4).setPreferredWidth(250);
        columnmodel.getColumn(2).setPreferredWidth(150);
        columnmodel.getColumn(3).setPreferredWidth(150);
        
        
        
        
    
        validate();
        repaint();
                            
                            
                            
                            
                            
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        
                        
                    
                     
                    }   
                });
        
        
         search.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        
                        databaseconection conexion2=new databaseconection();
                     
                            
                            
                            
                        try {
                            conexion2.MySQLConnection();
                        } catch (Exception ex) {
                            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            String nap2=napatie.getText().trim();
                            
                            if (nap2.isEmpty()){
                                 JOptionPane.showMessageDialog(null, "Pleas fill the field is empty!");
                            }
                            
                            else{
                            
                            
                            nap2.equalsIgnoreCase(nap2);
                            napatie.setText(null);
                           
                        try {
                            
                            
                              while (modelo.getRowCount()>0){
                                modelo.removeRow(0);
                               
                            }
                            
                            ResultSet r=null;
                            int rowCounter2=0;
                            r=conexion2.searchPatient(nap2);
                            
                             while(r.next()){
                        
                        String id=r.getString("idPatient");
                        
                        
                        data[0]=id;
                        String name=r.getString("name_P");
                        data[1]=name;
                        String lastname=r.getString("lastname_P");
                        data [2]=lastname;
                        String contact=r.getString("phone");
                        data [3]=contact;
                        String emai=r.getString("email");
                        data[4]=emai;
                        int birth=r.getInt("birthyear");
                        data[5]=birth;
                        
                        rowCounter2++;
                        
                        modelo.addRow(data);}
                             
                              TableColumnModel columnmodel=table.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(150);
        columnmodel.getColumn(4).setPreferredWidth(250);
        columnmodel.getColumn(2).setPreferredWidth(150);
        columnmodel.getColumn(3).setPreferredWidth(150);
        
        
        
        
       
        validate();
        repaint();
                            
                            
                            
                          
                            
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
                        }
                           
                        
                      
            
        
                        
                                
                                
                            }}
                            
                            
                            
                 
                        
                        
                        
                        
                    
                });
         
         
         
         
        
       
        
        
    }
    
    
    
    
    
}
