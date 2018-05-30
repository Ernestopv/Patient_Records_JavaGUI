/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacct2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Netho
 */
public class PrintBill  extends JFrame{
    
    public PrintBill() throws Exception{
        
         setTitle("Billing History");
        int idnumber=0;
        setVisible(true);
        setSize(900, 600);
        setLocationRelativeTo(null);
        
        
         DefaultTableModel modelo= new DefaultTableModel();
        JTable table=new JTable();
        JScrollPane js=new JScrollPane(table);
        
        setLayout(new GridLayout(3,0));
        
       
        
        
      
       
        
        // table creation 
        
        modelo.addColumn("Id.B");
        modelo.addColumn("Charge");
        modelo.addColumn("status");
        modelo.addColumn("Id.P");
        modelo.addColumn("Patient Name");
        modelo.addColumn("lastname");
        modelo.addColumn("Phone");
        modelo.addColumn("date");
        modelo.addColumn("time");
        modelo.addColumn("Prescription");
        modelo.addColumn("Doctor");
        modelo.addColumn("Id.D");
        table.setModel(modelo);
        TableColumnModel columnmodel=table.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(150);
        columnmodel.getColumn(2).setPreferredWidth(150);
        
        columnmodel.getColumn(4).setPreferredWidth(170);
        columnmodel.getColumn(5).setPreferredWidth(200);
        columnmodel.getColumn(6).setPreferredWidth(200);
        columnmodel.getColumn(7).setPreferredWidth(150);
        columnmodel.getColumn(8).setPreferredWidth(200);
        columnmodel.getColumn(9).setPreferredWidth(200);
        columnmodel.getColumn(10).setPreferredWidth(200);
        
        //conexion started 
        databaseconection cons=new databaseconection();
        ResultSet r= null;
        cons.MySQLConnection();
        
        r=cons.getValuesBillinghistory();
        
        int rowCounter=0;
        Object data[] = new Object[1000];
        
        while (r.next()){
            
            String idbi=r.getString("idBilling");
            data[0]=idbi;
            
            String charges=r.getString("charge");
            data[1]=charges;
            
            String status=r.getString("Status");
            data[2]=status;
            
            String namep=r.getString("idPatient");
            data[3]=namep;
            
            String name=r.getString("name_P");
            data[4]=name;
            
            String lastnamep=r.getString("Lastname_P");
            data[5]=lastnamep;
            
            String phone=r.getString("Phone");
            data[6]=phone;
            
            String date=r.getString("date");
            data[7]=date;
            
            String time=r.getString("time");
            data[8]=time;
            
            String prescription=r.getString("Medication");
            data[9]=prescription;
            
            String doctors=r.getString("Username");
            data[10]=doctors;
            
            String iddoctors=r.getString("idUsername");
            data[11]=iddoctors;
            
            rowCounter++;
            
            modelo.addRow(data);
            
            
            
        }
        
        
        
        
        
        
        add(js);
        validate();
        repaint();
        
        
        JPanel panelenmedio=new JPanel(new BorderLayout());
        JButton back =new JButton("back");
        back.setFont(new java.awt.Font("Tahoma", 15, 15));
        JButton print =new JButton("Print Bill");
        print.setFont(new java.awt.Font("Tahoma", 15, 20));
        add(print,BorderLayout.CENTER);
        panelenmedio.add(back,BorderLayout.PAGE_END);
        JPanel panelsubenmedio=new JPanel(new FlowLayout());
        JLabel searchbyname=new JLabel("Search by Patient Name");
        searchbyname.setFont(new java.awt.Font("Tahoma", 10, 15));
        JTextField searchbyname1=new JTextField(20);
        searchbyname1.setFont(new java.awt.Font("Tahoma", 10, 15));
        panelsubenmedio.add(searchbyname);
        panelsubenmedio.add(searchbyname1);
        JButton search=new JButton("Search");
        search.setFont(new java.awt.Font("Tahoma", 10, 15));
        panelsubenmedio.add(search);
         JButton reset=new JButton("Reset");
        reset.setFont(new java.awt.Font("Tahoma", 10, 15));
        panelsubenmedio.add(search);
        panelsubenmedio.add(reset);
        panelenmedio.add(panelsubenmedio);
        add(panelenmedio);
        
        
      // back adding action 
      
      back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
      
      
      // adding search to action 
      
      
      search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                databaseconection co=new databaseconection();
                try {
                       
                    co.MySQLConnection();
                    String nameps=searchbyname1.getText().trim();
                    
                     if (nameps.isEmpty()){
                                 JOptionPane.showMessageDialog(null, "Plese fill the field is empty!");
                            }
                            
                            else{
                         while (modelo.getRowCount()>0){
                                modelo.removeRow(0);
                               
                            }
                            
                            
                            nameps.equalsIgnoreCase(nameps);
                            searchbyname1.setText(null);
                    
                    ResultSet rs=null;
                    int rowCounter=0;
                    rs=co.searchPatientinbill(nameps);
                    
                    while(rs.next()){
                        
                        
                        String idbi=rs.getString("idBilling");
            data[0]=idbi;
            
            String charges=rs.getString("charge");
            data[1]=charges;
            
            String status=rs.getString("Status");
            data[2]=status;
            
            String namep=rs.getString("idPatient");
            data[3]=namep;
            
            String name=rs.getString("name_P");
            data[4]=name;
            
            String lastnamep=rs.getString("Lastname_P");
            data[5]=lastnamep;
            
            String phone=rs.getString("Phone");
            data[6]=phone;
            
            String date=rs.getString("date");
            data[7]=date;
            
            String time=rs.getString("time");
            data[8]=time;
            
            String prescription=rs.getString("Medication");
            data[9]=prescription;
            
            String doctors=rs.getString("Username");
            data[10]=doctors;
            
            String iddoctors=rs.getString("idUsername");
            data[11]=iddoctors;
            
            rowCounter++;
            
            modelo.addRow(data);
                        
                    }
                    
                    
                    
                    
                    
                    
                    
                    
                } 
                
           
            
            
            }   catch (ClassNotFoundException ex) {
                    Logger.getLogger(PatientsHistoryBilling.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(PatientsHistoryBilling.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(PatientsHistoryBilling.class.getName()).log(Level.SEVERE, null, ex);
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
                        
                        try {
                            int rowCounter=0;
                              ResultSet rss=null;
                        rss=co2.getValuesBillinghistory();
                            
                            
                            while(rss.next()){
                                
             String idbi=rss.getString("idBilling");
            data[0]=idbi;
            
            String charges=rss.getString("charge");
            data[1]=charges;
            
            String status=rss.getString("Status");
            data[2]=status;
            
            String namep=rss.getString("idPatient");
            data[3]=namep;
            
            String name=rss.getString("name_P");
            data[4]=name;
            
            String lastnamep=rss.getString("Lastname_P");
            data[5]=lastnamep;
            
            String phone=rss.getString("Phone");
            data[6]=phone;
            
            String date=rss.getString("date");
            data[7]=date;
            
            String time=rss.getString("time");
            data[8]=time;
            
            String prescription=rss.getString("Medication");
            data[9]=prescription;
            
            String doctors=rss.getString("Username");
            data[10]=doctors;
            
            String iddoctors=rss.getString("idUsername");
            data[11]=iddoctors;
            
            rowCounter++;
            
            modelo.addRow(data);
            TableColumnModel columnmodel=table.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(150);
        columnmodel.getColumn(2).setPreferredWidth(150);
        
        columnmodel.getColumn(4).setPreferredWidth(170);
        columnmodel.getColumn(5).setPreferredWidth(200);
        columnmodel.getColumn(6).setPreferredWidth(200);
        columnmodel.getColumn(7).setPreferredWidth(150);
        columnmodel.getColumn(8).setPreferredWidth(200);
        columnmodel.getColumn(9).setPreferredWidth(200);
        columnmodel.getColumn(10).setPreferredWidth(200);
                                
                                
                                
                                
                            }
                            
       
        validate();
        repaint();
                            
                            
                        } catch (SQLException ex) {
                    Logger.getLogger(PatientsHistoryBilling.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //adding action to print button 
        
        print.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 
                 int row=table.getSelectedRow();
                 if(row>=0){
                 
                 
                 
                 String idbill=table.getValueAt(row, 0).toString();
                 String charge=table.getValueAt(row, 1).toString();
                 String status=table.getValueAt(row, 2).toString();
                 String idpatient=table.getValueAt(row, 3).toString();
                 String paname=table.getValueAt(row, 4).toString();
                 String lasnamep=table.getValueAt(row, 5).toString();
                 String date =table.getValueAt(row, 7).toString();
                 String time=table.getValueAt(row,8).toString();
                 String medication=table.getValueAt(row, 9).toString();
                 String doctor=table.getValueAt(row, 10).toString();
                 String iddoctor=table.getValueAt(row, 11).toString();
                 String descriptioncost="";
                 if(charge.equals("50")){
                     descriptioncost=" General Doctors Visit";
                 }
                 
                 if(charge.equals("30")){
                     descriptioncost=" Prescription Request";
                 }
                 
                 
                 
                 try {
                     BufferedWriter bw=new BufferedWriter(new FileWriter(new File("invoice.txt")));
                     bw.write("*******************  Hospital Rodriguez  *****************");
                     bw.newLine();
                     bw.write( "           Invoice                  id:"+idbill+"    ");
                     bw.newLine();
                     bw.write("-----date:"+date+"                     Time:"+time+"--");
                     bw.newLine();
                     bw.write("Patient id:"+idpatient+"        Name:"+paname+"  "+lasnamep);
                     bw.newLine();
                     bw.write("                         Doctor:"+doctor+" idD:"+iddoctor);
                     bw.newLine();
                     bw.write("  Description:"+descriptioncost+" Total: £"+charge);
                     bw.newLine();
                     bw.write("     Prescription="+medication);
                     bw.newLine();
                     bw.write("Status:"+status);
                     bw.newLine();
                     bw.write("***********************************************************");
                     bw.newLine();
                     bw.write("********************thank you for your visit **************");
                     bw.newLine();
                     bw.write("***********************************************************");
                     
                     bw.close();
                     
                    
                     
                     JFrame invoicecustomer=new JFrame();
                     invoicecustomer.setVisible(true);
                     invoicecustomer.setSize(300, 300);
                     invoicecustomer.setLocationRelativeTo(null);
                     
                     JTextArea area=new JTextArea();
                     area.setEditable(false);
                     
                     
                     File file=new File("invoice.txt");
                    Path path= Paths.get(file.getAbsolutePath());
                    String retorn=new String(Files.readAllBytes(path));
                    area.setText(retorn);
                    
                     
                     
                      
                     
                     
                      
                      
                      
                      
                      
                      
                      invoicecustomer.add(area);
                      
                     
                     
                     
                     
                     
                 } catch (IOException ex) {
                     Logger.getLogger(PrintBill.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 }
                 else{
                     JOptionPane.showMessageDialog(null, "Please select a row!");
                 }
                 
                 
                 
             }
         });
        
    }
    
    
    
}
