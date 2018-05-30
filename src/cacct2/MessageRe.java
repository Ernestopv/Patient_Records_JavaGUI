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
public class MessageRe extends JPanel {
    
    
    public MessageRe() throws Exception{
        
        DefaultTableModel modelo= new DefaultTableModel();
        JTable table=new JTable();
        JScrollPane js=new JScrollPane(table);
        
        setLayout(new GridLayout(0,1));
        
        JPanel uno =new JPanel(new BorderLayout());
        JPanel subuno=new JPanel(new GridBagLayout());
        JPanel dos=new JPanel(new BorderLayout());
        
        
        uno.setBackground(Color.white);
        dos.setBackground(Color.LIGHT_GRAY);
        
        // table creation 
        
        modelo.addColumn("Id");
        modelo.addColumn("Message");
        modelo.addColumn("Status");
        modelo.addColumn("Read at:");
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
        r=cons.getValuesMessage();
        
        int rowCounter=0;
        Object data[] = new Object[1000];
        while(r.next()){
            
            String id=r.getString("idMessages");
             data[0]=id;
             String message=r.getString("Message");
             data[1]=message;
             String status=r.getString("status");
             data[2]=status;
             
             String at=r.getString("Read_at");
             data[3]=at;
             
             String iddoctor=r.getString("idUsername");
             data[4]=iddoctor;
             
             String doctorname=r.getString("Username");
             data[5]=doctorname;
             
             String idpatient=r.getString("idPatient");
             data[6]=idpatient;
            
             String patientname=r.getString("name_P");
             data[7]=patientname;
             
              String date =r.getString("date");
             data[8]=date;
             
             String time =r.getString("time");
             data[9]=time;
            
             rowCounter++;
             
             modelo.addRow(data);
           
            
        }
        
        cons.closeConnection();
        
        TableColumnModel columnmodel=table.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(550);
        columnmodel.getColumn(2).setPreferredWidth(200);
        columnmodel.getColumn(3).setPreferredWidth(200);
        columnmodel.getColumn(4).setPreferredWidth(200);
        columnmodel.getColumn(5).setPreferredWidth(200);
        columnmodel.getColumn(6).setPreferredWidth(200);
        columnmodel.getColumn(7).setPreferredWidth(150);
        columnmodel.getColumn(8).setPreferredWidth(150);
        
        
        
        dos.add(js,BorderLayout.CENTER);
        validate();
        repaint();
        
        JButton delete =new JButton("Delete");
        delete.setFont(new java.awt.Font("Tahoma", 10, 16));
        
        dos.add(delete, BorderLayout.PAGE_END);
        
        //adding action to delete button 
        
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                  databaseconection conec=new databaseconection();
               try {
                   conec.MySQLConnection();
                   
               } catch (Exception ex) {
                   Logger.getLogger(Tabla_patient.class.getName()).log(Level.SEVERE, null, ex);
               }
               
               
               
               try {
                   int row=table.getSelectedRow();
                  
                   
                   if(row>=0){
                   int n=JOptionPane.showConfirmDialog(null, "Are you sure that \n you want to delete??", "Warning!!!!",JOptionPane.YES_NO_OPTION);
                   
                    if(n==0){
                   String idtext=table.getValueAt(row, 0).toString();
                   int idp= Integer.parseInt(idtext);
                   conec.deleteRecordMessage("messages", idp);
                   modelo.removeRow(row);}
                    }
                    
                    else{
                      JOptionPane.showMessageDialog(null,"Please select row To Delete..");
                    }
                    conec.closeConnection();
                    
               } catch (ClassNotFoundException ex) {
                   Logger.getLogger(Tabla_patient.class.getName()).log(Level.SEVERE, null, ex);
               } catch (SQLException ex) {
                   Logger.getLogger(Tabla_patient.class.getName()).log(Level.SEVERE, null, ex);
               }
               
    
               
           }
               
            
        });
        
        
        
        
        
        add(dos);
        
        
        //adding buttons to one
        
        //uno with borderlayout this is for the  order formulario 
        JPanel subuno1=new JPanel(new GridBagLayout());
        JLabel inMessage=new JLabel("Insert Message :");
        inMessage.setFont(new java.awt.Font("Tahoma", 10, 14));
        GridBagConstraints c=new GridBagConstraints();
        c.gridx=0;
        subuno1.add(inMessage,c);
        JTextField insertmessage=new JTextField(40);
        insertmessage.setFont(new java.awt.Font("Tahoma", 10, 14));
        c.gridx=1;
        subuno1.add(insertmessage,c);
        JLabel indoctor=new JLabel("            Doctor :");
        indoctor.setFont(new java.awt.Font("Tahoma", 10, 14));
        c.gridx=0;
        c.gridy=1;
        subuno1.add(indoctor,c);
         String [] doct={"Ernesto Prado","Jhony Rubbert"};
        
        JComboBox docto=new JComboBox(doct);
        docto.setFont(new java.awt.Font("Tahoma", 10, 14));
       
        
        c.gridx=1;
        c.gridy=1;
        subuno1.add(docto,c);
         JLabel inpatient=new JLabel("          Patient id :");
        inpatient.setFont(new java.awt.Font("Tahoma", 10, 14));
        c.gridx=0;
        c.gridy=2;
        subuno1.add(inpatient,c);
        JTextField insertidpati=new JTextField(8);
        insertidpati.setFont(new java.awt.Font("Tahoma", 10, 14));
        c.gridx=1;
        c.gridy=2;
        subuno1.add(insertidpati,c);
        
        JButton searchPatient =new JButton("Search id");
        searchPatient.setFont(new java.awt.Font("Tahoma", 10, 14));
        c.gridx=3;
        c.gridy=2;
        subuno1.add(searchPatient,c);
        
        JLabel date=new JLabel("               date :");
        date.setFont(new java.awt.Font("Tahoma", 10, 14));
        c.gridx=0;
        c.gridy=3;
        subuno1.add(date,c);
         JTextField insertdate=new JTextField(8);
        insertdate.setFont(new java.awt.Font("Tahoma", 10, 14));
        c.gridx=1;
        c.gridy=3;
        subuno1.add(insertdate,c);
        JLabel time=new JLabel("             time :");
        time.setFont(new java.awt.Font("Tahoma", 10, 14));
        c.gridx=2;
        c.gridy=3;
        subuno1.add(time,c);
         JTextField inserttime=new JTextField(8);
        inserttime.setFont(new java.awt.Font("Tahoma", 10, 14));
        c.gridx=3;
        c.gridy=3;
        subuno1.add(inserttime,c);
        
        subuno1.setBackground(Color.LIGHT_GRAY);
        
        
        //this is for the back button 
        
        JPanel subuno2=new JPanel(new BorderLayout());
        JButton backs=new JButton("Back");
        backs.setFont(new java.awt.Font("Tahoma", 10, 16));
        JButton saves=new JButton("Add Message");
        saves.setFont(new java.awt.Font("Tahoma", 10, 16));
        saves.setBackground(Color.CYAN);
        
              
        
        
        subuno2.add(saves, BorderLayout.LINE_END);
        subuno2.add(backs, BorderLayout.LINE_START);
        
        // adding both paneles in uno 
        uno.add(subuno1,BorderLayout.CENTER);
        uno.add(subuno2,BorderLayout.PAGE_END);
        
        
        
        
        add(uno);
     
        // adding just numerical values in one jtextfiel id patient 
        
        insertidpati.addKeyListener(new KeyAdapter() {
            
           
           public void keyTyped(KeyEvent e) {
char c = e.getKeyChar();
if (!(Character.isDigit(c) ||
(c == KeyEvent.VK_BACK_SPACE) ||
(c == KeyEvent.VK_DELETE))) {
getToolkit().beep();
e.consume();
}}
            
            
});
        
        // addign action to back button 
        
        backs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        
        // adding action to saves button 
        
        saves.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                String msn=insertmessage.getText().trim();
                String idp=insertidpati.getText().trim();
                int idPatients=Integer.parseInt(idp);
                String datem=insertdate.getText().trim();
                String timem=inserttime.getText().trim();
                String statuss="unread";
                 String doc=(String) docto.getSelectedItem();
               int iddoc=1;
               int iddoc2=4;
               int valor=0;
               if (doc.contains("Ernesto")){
                   
                   valor=iddoc;
                   
               }
               else if(doc.contains("Jhony")){
                   valor=iddoc2;
               } 
               
             
              
               
                databaseconection con=new databaseconection();
                try {
                    con.MySQLConnection();
                } catch (Exception ex) {
                    Logger.getLogger(MessageRe.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                con.insertMessage(msn, statuss, datem, timem, valor, idPatients);
                 while(modelo.getRowCount()>0){
                        modelo.removeRow(0);
                    }
                
                con.closeConnection();
                
                insertdate.setText(null);
                insertidpati.setText(null);
                insertmessage.setText(null);
                inserttime.setText(null);
                
          
        
        databaseconection cons=new databaseconection();
        ResultSet r= null;
                try {
                    cons.MySQLConnection();
                } catch (Exception ex) {
                    Logger.getLogger(MessageRe.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    r=cons.getValuesMessage();
                } catch (SQLException ex) {
                    Logger.getLogger(MessageRe.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        int rowCounter=0;
        Object data2[] = new Object[1000];
                try {
                    while(r.next()){
                        
                        String id=r.getString("idMessages");
                        data2[0]=id;
                        String message=r.getString("Message");
                        data2[1]=message;
                        String status=r.getString("status");
                        data2[2]=status;
                        
                        String at=r.getString("Read_at");
                        data2[3]=at;
                        
                        String iddoctor=r.getString("idUsername");
                        data2[4]=iddoctor;
                        
                        String doctorname=r.getString("Username");
                        data2[5]=doctorname;
                        
                        String idpatient=r.getString("idPatient");
                        data2[6]=idpatient;
                        
                        String patientname=r.getString("name_P");
                        data2[7]=patientname;
                        
                        String date =r.getString("date");
                        data2[8]=date;
                        
                        String time =r.getString("time");
                        data2[9]=time;
                        
                        rowCounter++;
                        
                        modelo.addRow(data2);
                        
                        
                    }       } catch (SQLException ex) {
                    Logger.getLogger(MessageRe.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        cons.closeConnection();
        
        TableColumnModel columnmodel=table.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(550);
        columnmodel.getColumn(2).setPreferredWidth(200);
        columnmodel.getColumn(3).setPreferredWidth(200);
        columnmodel.getColumn(4).setPreferredWidth(200);
        columnmodel.getColumn(5).setPreferredWidth(200);
        columnmodel.getColumn(6).setPreferredWidth(200);
        columnmodel.getColumn(7).setPreferredWidth(150);
        columnmodel.getColumn(8).setPreferredWidth(150);
        
        
        
        dos.add(js,BorderLayout.CENTER);
        validate();
        repaint();
                
                
                
               
               
                
                
            }
        });
        
        
        searchPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame hola=new JFrame();
                hola.setTitle("Patient");
                GridLayout order=new GridLayout(4,0);
                hola.setLayout(order);
                hola.setSize(600,500);
                hola.setVisible(true);
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
        
        
        
        
       hola.add(js);
        validate();
        repaint();
           
        
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
         or3.add(subor3,BorderLayout.PAGE_START);
        
        
        or3.add(search,BorderLayout.PAGE_END);
        JButton reset=new JButton("Reset");
        reset.setFont(new java.awt.Font("Tahoma", 10, 16));
        subor3.add(reset);
        
        
        hola.add(or3);
        
        
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
        
        
        
        
       hola.add(js);
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
        
        
        
        
       hola.add(js);
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
        });
        
        
        
        
        
        
        
        
    }
    
    
    
}
