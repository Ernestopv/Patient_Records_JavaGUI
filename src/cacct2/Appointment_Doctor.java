/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacct2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
public class Appointment_Doctor  extends JPanel{
    
    
    public Appointment_Doctor(){
        
          
                GridLayout order=new GridLayout(3,0);
                setLayout(order);
                setSize(600,500);
                setVisible(true);
                 DefaultTableModel modelo= new DefaultTableModel();
        JTable table=new JTable();
        JScrollPane js=new JScrollPane(table);
        
                
                 modelo.addColumn("id");
        modelo.addColumn("date");
        modelo.addColumn("time");
        modelo.addColumn("Patient id");
        modelo.addColumn("Patient name");
        modelo.addColumn("Patient lastname");
        modelo.addColumn("medication");
        
        modelo.addColumn("Prescription by Doctor:");
        modelo.addColumn("doctor Id:");
        table.setModel(modelo);

       databaseconection dato=new databaseconection();
       ResultSet r = null;
       
                try {
                    dato.MySQLConnection();
                } catch (Exception ex) {
                    Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    r=dato.getValuesAppointment();
                } catch (SQLException ex) {
                    Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
                }
       int rowCounter=0;
        Object data[] = new Object[1000];
        
                try {
                    while(r.next()){
                        
                        String id=r.getString("idapointment");
                        
                        
                        data[0]=id;
                        String date=r.getString("date");
                        data[1]=date;
                        String time=r.getString("time");
                        data [2]=time;
                        int idpatient=r.getInt("idPatient");
                        data [3]=idpatient;
                        String namep=r.getString("name_P");
                        data[4]=namep;
                        String lastnamep=r.getString("lastname_P");
                        data[5]=lastnamep;
                        String medication=r.getString("Medication");
                        data[6]=medication;
                        String doctoid=r.getString("Username");
                        data[7]=doctoid;
                        String prescriptionby=r.getString("IdUsername");
                        data[8]=prescriptionby;
                        
                        rowCounter++;
                        
                        modelo.addRow(data);
                        
                        
                    }       } catch (SQLException ex) {
                    Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        TableColumnModel columnmodel=table.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(150);
        columnmodel.getColumn(4).setPreferredWidth(150);
        columnmodel.getColumn(2).setPreferredWidth(150);
        
        columnmodel.getColumn(7).setPreferredWidth(150);
        
        
        JPanel orders=new JPanel(new BorderLayout());
        
        orders.add(js,BorderLayout.NORTH);
        add(orders);
        validate();
        repaint();
        
        JPanel panel2=new JPanel(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        JLabel searchpa=new JLabel("Search Patient : ");
        searchpa.setFont(new java.awt.Font("Tahoma", 10, 15));
        JTextField insearch=new JTextField(20);
        insearch.setFont(new java.awt.Font("Tahoma", 10, 15));
        JButton search=new JButton("Search");
        search.setFont(new java.awt.Font("Tahoma", 10, 15));
        JButton reset=new JButton("Reset");
         reset.setFont(new java.awt.Font("Tahoma", 10, 18));
        c.gridx=0;
        panel2.add(searchpa,c);
        c.gridx=1;
        panel2.add(insearch,c);
        c.gridx=2;
        panel2.add(search,c);
        c.gridx=3;
        panel2.add(reset,c);
        
        // creating insert options 
         JLabel inmedication=new JLabel("Insert Medication : ");
        inmedication.setFont(new java.awt.Font("Tahoma", 10, 15));
        JTextField inmedi=new JTextField(20);
        inmedi.setFont(new java.awt.Font("Tahoma", 10, 15));
        c.gridx=0;
        c.gridy=1;
        panel2.add(inmedication,c);
        c.gridx=1;
        c.gridy=1;
        panel2.add(inmedi,c);
        JButton insert=new JButton("Insert");
         insert.setFont(new java.awt.Font("Tahoma", 10, 15));
        c.gridx=2;
        c.gridy=1;
        panel2.add(insert,c);
        
        
        
        
        
        add(panel2);
        
        
        
        
        
        
        // adding back button 
        JPanel panel3= new JPanel(new BorderLayout());
        JButton back =new JButton("Back");
        back.setFont(new java.awt.Font("Tahoma", 10, 16));
          JButton listm =new JButton("List of Medications");
        listm.setFont(new java.awt.Font("Tahoma", 15, 25));
           JButton externalconsul =new JButton("External Consult");
        externalconsul.setFont(new java.awt.Font("Tahoma", 15, 25));
       JPanel panelconsult=new JPanel(new GridLayout(0,2));
       panelconsult.setBackground(Color.white);
       
       panelconsult.add(listm);
       panelconsult.add(externalconsul);
        panel3.add(panelconsult, BorderLayout.CENTER);
        panel3.add(back,BorderLayout.PAGE_END);
        add(panel3);
        
        
        // action to back button 
        
        back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setVisible(false);
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
                            String nap2=insearch.getText().trim();
                            
                            if (nap2.isEmpty()){
                                 JOptionPane.showMessageDialog(null, "Plese fill the field is empty!");
                            }
                            
                            else{
                            
                            
                            nap2.equalsIgnoreCase(nap2);
                            insearch.setText(null);
                           
                        try {
                            
                            
                              while (modelo.getRowCount()>0){
                                modelo.removeRow(0);
                               
                            }
                            
                            ResultSet r=null;
                            int rowCounter2=0;
                            r=conexion2.searchPatientbydoctor(nap2);
                            
                             while(r.next()){
                                 
                                  String id=r.getString("idapointment");
                        
                        
                        data[0]=id;
                        String date=r.getString("date");
                        data[1]=date;
                        String time=r.getString("time");
                        data [2]=time;
                        int idpatient=r.getInt("idPatient");
                        data [3]=idpatient;
                        String namep=r.getString("name_P");
                        data[4]=namep;
                        String lastnamep=r.getString("lastname_P");
                        data[5]=lastnamep;
                        String medication=r.getString("Medication");
                        data[6]=medication;
                        String doctoid=r.getString("Username");
                        data[7]=doctoid;
                        String prescriptionby=r.getString("IdUsername");
                        data[8]=prescriptionby;
                        
                        rowCounter2++;
                        
                        modelo.addRow(data);
                             }
                             
                              TableColumnModel columnmodel=table.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(150);
        columnmodel.getColumn(4).setPreferredWidth(250);
        columnmodel.getColumn(2).setPreferredWidth(150);
        columnmodel.getColumn(3).setPreferredWidth(150);
        columnmodel.getColumn(5).setPreferredWidth(150);
        
        
        
      
        validate();
        repaint();
                                 
                                 
                             
                                 
                             } catch (ClassNotFoundException ex) {
                                  Logger.getLogger(Appointment_Doctor.class.getName()).log(Level.SEVERE, null, ex);
                              } catch (SQLException ex) {
                                  Logger.getLogger(Appointment_Doctor.class.getName()).log(Level.SEVERE, null, ex);
                              }
                        
                        
                        
                        
                        
                        
                        
                        
                            }}
                });
        
        
        
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
                            rs=co2.getValuesAppointment();
                            
                            int rowCounter2=0;
                            
                             while(rs.next()){
                                 
                               String id=rs.getString("idapointment");
                        
                        
                        data[0]=id;
                        String date=rs.getString("date");
                        data[1]=date;
                        String time=rs.getString("time");
                        data [2]=time;
                        int idpatient=rs.getInt("idPatient");
                        data [3]=idpatient;
                        String namep=rs.getString("name_P");
                        data[4]=namep;
                        String lastnamep=rs.getString("lastname_P");
                        data[5]=lastnamep;
                        String medication=rs.getString("Medication");
                        data[6]=medication;
                        String doctoid=rs.getString("Username");
                        data[7]=doctoid;
                        String prescriptionby=rs.getString("IdUsername");
                        data[8]=prescriptionby;
                        
                        rowCounter2++;
                        
                        modelo.addRow(data);
                             }
                             
                              TableColumnModel columnmodel=table.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(150);
        columnmodel.getColumn(4).setPreferredWidth(250);
        columnmodel.getColumn(2).setPreferredWidth(150);
        columnmodel.getColumn(3).setPreferredWidth(150);
        columnmodel.getColumn(5).setPreferredWidth(150);
        
        
        
      
        validate();
        repaint();
                            
                        
                        
                    }   catch (SQLException ex) {
                            Logger.getLogger(Appointment_Doctor.class.getName()).log(Level.SEVERE, null, ex);
                        }}
                });
        
        
        
        // action to insert medication 
        
        
        insert.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                         int row=table.getSelectedRow();
                         String datea=modelo.getValueAt(row, 1).toString();
                         String timea=modelo.getValueAt(row, 2).toString();
                         String patientids=modelo.getValueAt(row, 3).toString();
                         
                         int id=Integer.parseInt(patientids);
                         
                         String prescription=inmedi.getText().trim();
                         
                         databaseconection cone=new databaseconection();
                         
                        try {
                            cone.MySQLConnection();
                        } catch (Exception ex) {
                            Logger.getLogger(Appointment_Doctor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                         
                         cone.ModifyforMedication(prescription, datea, timea, id);
                         cone.closeConnection();
                         
                         inmedi.setText(null);
                         
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
                            rs=co2.getValuesAppointment();
                            
                            int rowCounter2=0;
                            
                             while(rs.next()){
                                 
                               String ids=rs.getString("idapointment");
                        
                        
                        data[0]=ids;
                        String date=rs.getString("date");
                        data[1]=date;
                        String time=rs.getString("time");
                        data [2]=time;
                        int idpatient=rs.getInt("idPatient");
                        data [3]=idpatient;
                        String namep=rs.getString("name_P");
                        data[4]=namep;
                        String lastnamep=rs.getString("lastname_P");
                        data[5]=lastnamep;
                        String medication=rs.getString("Medication");
                        data[6]=medication;
                        String doctoid=rs.getString("Username");
                        data[7]=doctoid;
                        String prescriptionby=rs.getString("IdUsername");
                        data[8]=prescriptionby;
                        
                        rowCounter2++;
                        
                        modelo.addRow(data);
                             }
                             
                              TableColumnModel columnmodel=table.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(150);
        columnmodel.getColumn(4).setPreferredWidth(250);
        columnmodel.getColumn(2).setPreferredWidth(150);
      
        columnmodel.getColumn(5).setPreferredWidth(160);
        
        
        
      
        validate();
        repaint();
                         
                          
                         
                         
                         
                        
                        
                        
                       
                    }   catch (SQLException ex) {
                            Logger.getLogger(Appointment_Doctor.class.getName()).log(Level.SEVERE, null, ex);
                        }}
                });
        
         //adding action to list of medications
         listm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       
                        JFrame medicationlist=new JFrame();
                        medicationlist.setVisible(true);
                        medicationlist.setSize(600,200);
                        
                         DefaultTableModel modelo2= new DefaultTableModel();
        JTable tableMedication=new JTable();
        JScrollPane js2=new JScrollPane(tableMedication);
        
                
        modelo2.addColumn("Category A");
        modelo2.addColumn("Category B");
        modelo2.addColumn("category C");
       
        tableMedication.setModel(modelo2);
        
          medicationlist.add(js2);
                        
                        modelo2.addRow(new Object[]{"VND1","X34","543H"});
                         modelo2.addRow(new Object[]{"XXV2","HH5","344BB"});
                          modelo2.addRow(new Object[]{"HNF232","DDF23","JUY9"});
                           modelo2.addRow(new Object[]{"GB334","JHH7","232B"});
                        
                    }
                });
         
         
         externalconsul.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                     try {

            Desktop.getDesktop().browse(new URI("http://www.rxlist.com/drugs/alpha_a.htm"));

        } catch (URISyntaxException ex) {

            System.out.println(ex);

        }catch(IOException es){

            System.out.println(es);

        }
      
 }                       
                });
         
    
}
    
    

    
   
    
    
    
    
}
