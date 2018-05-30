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


public class billingPatient extends JPanel{
     int click=0;
    int nextidpa=0;
    public billingPatient() throws Exception{
         DefaultTableModel modelo= new DefaultTableModel();
        JTable table=new JTable();
        JScrollPane js=new JScrollPane(table);
       
       setLayout(new GridLayout(0,2));
      
       JPanel panelde1=new JPanel(new GridLayout(3,0));
       JPanel panel1=new JPanel(new GridBagLayout());
       JPanel panel2=new JPanel(new BorderLayout());
       
       //order in panel 1
     
       
       
       panel1.setBackground(Color.white);
       panel2.setBackground(Color.gray);
       // welcome messaje
       
       JLabel welcome= new JLabel("Add Bill ");
       welcome.setFont(new java.awt.Font("Tahoma", 0, 18));
       JPanel welcomeorder=new JPanel(new FlowLayout());
       
       
       //buttons 
       
       JButton back=new JButton("back");
       back.setFont(new java.awt.Font("Tahoma", 10, 18));
       JButton save=new JButton("Add Bill");
       save.setFont(new java.awt.Font("Tahoma", 10, 18));
       
       
       JPanel orderbuttons=new JPanel(new GridLayout(0,2));
       JPanel orderbuttons1=new JPanel(new BorderLayout());
       JPanel orderbuttons2=new JPanel(new BorderLayout());
       orderbuttons1.add(back, BorderLayout.PAGE_END);
       orderbuttons2.add(save,BorderLayout.PAGE_END);
       orderbuttons.add(orderbuttons1);
       orderbuttons.add(orderbuttons2);
       
       
       
       welcomeorder.add(welcome);
       JButton search=new JButton("search Patient");
       
       search.setFont(new java.awt.Font("Tahoma", 10, 18));
       
       welcomeorder.add(search);
       panelde1.add(welcomeorder);
       //in the middle first panel 
       GridBagConstraints mundi=new GridBagConstraints();
       JLabel patienid=new JLabel("Patient ID:");
       patienid.setFont(new java.awt.Font("Tahoma", 10, 16));
       JTextField patid=new JTextField(8);
       patid.setFont(new java.awt.Font("Tahoma", 10, 16));
       mundi.gridx=1;
       panel1.add(patienid,mundi);
        mundi.gridx=2;
        panel1.add(patid,mundi);
       JLabel doc=new JLabel("Doctor:");
       doc.setFont(new java.awt.Font("Tahoma", 10, 16));
       mundi.gridy=1;
       mundi.gridx=1;
       panel1.add(doc,mundi);
        mundi.gridx=2;
       
        String [] doct={"Ernesto","Jhony"};
        
        JComboBox docto=new JComboBox(doct);
        docto.setFont(new java.awt.Font("Tahoma", 10, 16));
         panel1.add(docto,mundi);
        
         JLabel total=new JLabel("Total:" );
         total.setFont(new java.awt.Font("Tahoma", 10, 16));
         mundi.gridy=2;
         mundi.gridx=1;
         panel1.add(total,mundi);
          String [] invoice={"50","30"};
          JComboBox invo=new JComboBox(invoice);
          
         invo.setFont(new java.awt.Font("Tahoma", 10, 16));
          mundi.gridy=2;
         mundi.gridx=2;
         panel1.add(invo,mundi);
         JLabel status=new JLabel("Status:" );
         String [] billing={"Unpaid","Paid"};
         status.setFont(new java.awt.Font("Tahoma", 10, 16));
         mundi.gridy=3;
         mundi.gridx=1;
         panel1.add(status,mundi);
          mundi.gridy=3;
         mundi.gridx=2;
        
        JComboBox bills=new JComboBox(billing);
         bills.setFont(new java.awt.Font("Tahoma", 10, 16));
         
          panel1.add(bills,mundi);
         
         
        patid.addKeyListener(new KeyAdapter() {
           
           public void keyTyped(KeyEvent e) {
char c = e.getKeyChar();
if (!(Character.isDigit(c) ||
(c == KeyEvent.VK_BACK_SPACE) ||
(c == KeyEvent.VK_DELETE))) {
getToolkit().beep();
e.consume();
}}
           
});
        
        
     
        
        
     
       
       
       panelde1.add(panel1);
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
       
        
        save.setActionCommand("click");
        
        
        panel2.add(js,BorderLayout.CENTER);
        validate();
        repaint();

       add(panel2);
       dato.closeConnection();
       
      // action button adding appointment 
      int idi=Integer.parseInt((String)data[0]);
      click=idi;
      
      save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
               String idpaa= patid.getText().trim();
               if(idpaa.isEmpty()){
               JOptionPane.showMessageDialog(null, "Insert idPatient!!");
               }
               else{
               String invoi=invo.getSelectedItem().toString();
               String bi=(String) bills.getSelectedItem();
               String doc=(String) docto.getSelectedItem();
               int iddoc=1;
               int iddoc2=4;
               int valor=0;
               String valorpaid="";
               
               String paid="paid";
               String unpaid="unpaid";
               
               if(bi.contains("Paid")){
                   
                   valorpaid=paid;
               }
               
               if(bi.contains("Unpaid")){
                   
                   valorpaid=unpaid;
               }
               
               if (doc.contains("Ernesto")){
                   
                   valor=iddoc;
                   
               }
               else if(doc.contains("Jhony")){
                   valor=iddoc2;
               } 
               
               
               databaseconection cors=new databaseconection();
                try {
                    int idppi=Integer.parseInt(idpaa);
                    int rowCounter=0;
                    cors.MySQLConnection();
                    cors.insertBill(invoi,valorpaid,invoi,idppi,valor);
                    while(modelo.getRowCount()>0){
                        modelo.removeRow(0);
                    }
                    cors.closeConnection();
                    
                    cors.MySQLConnection();
                    ResultSet r=null;
                    
                    r=cors.getValuesBilling();
                    
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
             patid.setText(null);
            
             
                     
           
            
        }
        
        TableColumnModel columnmodel=table.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(150);
        columnmodel.getColumn(4).setPreferredWidth(250);
        columnmodel.getColumn(2).setPreferredWidth(150);
        columnmodel.getColumn(3).setPreferredWidth(150);    
        
        
                        
                        
                    
                    
                    
                    
                    
                    
                } catch (Exception ex) {
                    Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                     
         
               
                        click=click+1;
                       
                        
                 
             
               
               
               
             
               
               
             
            }  }
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
        
        
   
        search.addActionListener(new ActionListener() {
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
        
        JPanel or=new JPanel(new GridBagLayout());
       
        
         JLabel patientname=new JLabel("Name:");
       patientname.setHorizontalTextPosition(JLabel.CENTER);
       JTextField insername=new JTextField(20);
       patientname.setFont(new java.awt.Font("Tahoma", 10, 15));
       insername.setFont(new java.awt.Font("Tahoma", 10, 15));
       JLabel lastnamep=new JLabel("    lastname:");
       JTextField lastinsert=new JTextField(20);
       lastnamep.setFont(new java.awt.Font("Tahoma", 10, 15));
       lastinsert.setFont(new java.awt.Font("Tahoma", 10, 15));
       JLabel tel=new JLabel("Tel:");
       JTextField insertTel=new JTextField(20);
       tel.setFont(new java.awt.Font("Tahoma", 10, 15));
       insertTel.setFont(new java.awt.Font("Tahoma", 10, 15));
       //email 
        JLabel email=new JLabel("email:");
       JTextField insertEmail=new JTextField(20);
       email.setFont(new java.awt.Font("Tahoma", 10, 15));
       insertEmail.setFont(new java.awt.Font("Tahoma", 10, 15));
       // birthyear
        JLabel birthyear=new JLabel("Birthyear:");
       JTextField insertBirth=new JTextField(20);
       birthyear.setFont(new java.awt.Font("Tahoma", 10, 15));
       insertBirth.setFont(new java.awt.Font("Tahoma", 10, 15));
       
       // order 
       
       GridBagConstraints b=new GridBagConstraints();

              insertBirth.addKeyListener(new KeyAdapter() {
           
           public void keyTyped(KeyEvent e) {
char c = e.getKeyChar();
if (!(Character.isDigit(c) ||
(c == KeyEvent.VK_BACK_SPACE) ||
(c == KeyEvent.VK_DELETE))) {
getToolkit().beep();
e.consume();
}}
           
});
              
                     
       insertTel.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
          char c = e.getKeyChar();
if (!(Character.isDigit(c) ||
(c == KeyEvent.VK_BACK_SPACE) ||
(c == KeyEvent.VK_DELETE))) {
getToolkit().beep();
e.consume();
}} 
           
           
});
              
       
       b.gridx=1;
       b.gridy=1;
      
       or.add(patientname,b);
      b.gridx=4;
       b.gridy=1;
       or.add(insername,b);
       b.gridx=1;
       b.gridy=2;
       or.add(lastnamep,b);
       b.gridx=4;
       b.gridy=2;
       or.add(lastinsert,b);
        b.gridx=1;
       b.gridy=3;
       or.add(tel,b);
       b.gridx=4;
       b.gridy=3;
       or.add(insertTel,b);
        b.gridx=1;
       b.gridy=4;
       or.add(email,b);
       b.gridx=4;
       b.gridy=4;
       or.add(insertEmail,b);
        b.gridx=1;
       b.gridy=5;
       or.add(birthyear,b);
       b.gridx=4;
       b.gridy=5;
       or.add(insertBirth,b);
      
        
       
        
        hola.add(or);
        
        JPanel or2=new JPanel(new FlowLayout());
       
        
        JButton save=new JButton("Add Patient");
        save.setFont(new java.awt.Font("Tahoma", 10, 16));
        or2.add(save);
       JButton delete=new JButton("delete");
        delete.setFont(new java.awt.Font("Tahoma", 10, 16));
        or2.add(delete);
        
        
        
        hola.add(or2);
        
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
        
        
        
        int idipp=Integer.parseInt((String)data[0]);
        nextidpa=idipp;
        
        
        save.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               
               databaseconection co=new databaseconection();
               nextidpa=nextidpa+1;
                String name=insername.getText().trim();
               String lastnap=lastinsert.getText().trim();
               String telc=insertTel.getText().trim();
               String em=insertEmail.getText().trim();      
               String birthy=insertBirth.getText().trim();
               
               
               if (name.isEmpty()&&lastnap.isEmpty()&&telc.isEmpty()&&em.isEmpty()&&birthy.isEmpty()){
               
                JOptionPane.showMessageDialog(null, "Pleas fill all the fields, one or more are missing!");
               
               }
               
               else{
                   
                   
              
                if(data[0]==null){
               
               
               
               
               
          
               
               try {
                   co.MySQLConnection();
               } catch (Exception ex) {
                   Logger.getLogger(Tabla_patient.class.getName()).log(Level.SEVERE, null, ex);
               }
               
             
               
              
               
               int biy=Integer.parseInt(birthy);
               
              
               
           
            
              co.insertData("patient", name, lastnap,telc, em, biy);
              
              
              modelo.addRow(new Object[]{nextidpa,name,lastnap,telc,em,biy});
              
              
              
              
              
               insername.setText(null);
               lastinsert.setText(null);
               insertTel.setText(null);
               insertEmail.setText(null);
               insertBirth.setText(null);
                    try {
                        co.MySQLConnection();
                    } catch (Exception ex) {
                        Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
                    }
               
               }
               else{
                   
                    
               
               
               
            
          
               
               try {
                   co.MySQLConnection();
               } catch (Exception ex) {
                   Logger.getLogger(Tabla_patient.class.getName()).log(Level.SEVERE, null, ex);
               }
               
              
               
               int biy=Integer.parseInt(birthy);
               
              
               
              co.insertData("patient", name, lastnap,telc, em, biy);
              modelo.addRow(new Object[]{nextidpa,name,lastnap,telc,em,biy});
               insername.setText(null);
               lastinsert.setText(null);
               insertTel.setText(null);
               insertEmail.setText(null);
               insertBirth.setText(null);
               
                   
               }
               
               co.closeConnection();
               

           }}
       });
        
        
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
                   conec.deleteRecord("patient", idp);
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
       
        
        
        
        
                
                
                
                
            }
        });
      
        
        
        
        
    }
    
    
}
