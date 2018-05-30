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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;




public class Tabla_patient extends JPanel  {
       int nextid=0;
        
   public Tabla_patient() throws Exception{
       
        DefaultTableModel modelo= new DefaultTableModel();
        JTable table=new JTable();
        JScrollPane js=new JScrollPane(table);
       
       setLayout(new GridLayout(0,2));
      
       JPanel panelde1=new JPanel(new GridLayout(3,0));
       JPanel panel1=new JPanel(new GridBagLayout());
       JPanel panel2=new JPanel(new BorderLayout());
       
       //order in panel 1
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
       GridBagConstraints c=new GridBagConstraints();
       c.gridx=1;
       c.gridy=1;
       c.weightx=0.01;
       panel1.add(patientname,c);
      c.gridx=4;
       c.gridy=1;
       panel1.add(insername,c);
       c.gridx=1;
       c.gridy=2;
       panel1.add(lastnamep,c);
       c.gridx=4;
       c.gridy=2;
       panel1.add(lastinsert,c);
        c.gridx=1;
       c.gridy=3;
       panel1.add(tel,c);
       c.gridx=4;
       c.gridy=3;
       panel1.add(insertTel,c);
        c.gridx=1;
       c.gridy=4;
       panel1.add(email,c);
       c.gridx=4;
       c.gridy=4;
       panel1.add(insertEmail,c);
        c.gridx=1;
       c.gridy=5;
       panel1.add(birthyear,c);
       c.gridx=4;
       c.gridy=5;
       panel1.add(insertBirth,c);
       
       
       
       panel1.setBackground(Color.white);
       panel2.setBackground(Color.gray);
       // welcome messaje
       
       JLabel welcome= new JLabel("Welcome insert, Patient data ");
       welcome.setFont(new java.awt.Font("Tahoma", 0, 18));
       JPanel welcomeorder=new JPanel(new FlowLayout());
       
       
       //buttons 
       
       JButton back=new JButton("back");
       back.setFont(new java.awt.Font("Tahoma", 10, 18));
       JButton save=new JButton("Adding Patient");
       save.setFont(new java.awt.Font("Tahoma", 10, 18));
       
       
       JPanel orderbuttons=new JPanel(new GridLayout(0,2));
       JPanel orderbuttons1=new JPanel(new BorderLayout());
       JPanel orderbuttons2=new JPanel(new BorderLayout());
       orderbuttons1.add(back, BorderLayout.PAGE_END);
       orderbuttons2.add(save,BorderLayout.PAGE_END);
       orderbuttons.add(orderbuttons1);
       orderbuttons.add(orderbuttons2);
       
       
       
       welcomeorder.add(welcome);
       panelde1.add(welcomeorder);
       panelde1.add(panel1);
       panelde1.add(orderbuttons);
       add(panelde1);
       
       
       
       // panel 2 using now 
       
       JButton delete=new JButton("Delete row");
       delete.setFont(new java.awt.Font("Tahoma", 10, 18));
       panel2.add(delete, BorderLayout.PAGE_END);
       
       
       
       // Adding to avoid characters in numbers fields tel and birthyear
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
       
       
       
       
       
       //table creation 
       
       
        modelo.addColumn("id");
        modelo.addColumn("name");
        modelo.addColumn("lastname");
        modelo.addColumn("contact");
        modelo.addColumn("email");
        modelo.addColumn("year");
        
        table.setModel(modelo);

       databaseconection dato=new databaseconection();
       ResultSet r;
       
       dato.MySQLConnection();
       r=dato.getValuesPatient();
       int rowCounter=0;
        Object data[] = new Object[1000];
        
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
           
            
        }
        
        TableColumnModel columnmodel=table.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(150);
        columnmodel.getColumn(4).setPreferredWidth(250);
        columnmodel.getColumn(2).setPreferredWidth(150);
        columnmodel.getColumn(3).setPreferredWidth(150);
        
        
        
        
        panel2.add(js,BorderLayout.CENTER);
        validate();
        repaint();

       add(panel2);
       dato.closeConnection();
       
       
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
       
       
       // back action 
       
       back.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               setVisible(false);
               
               insername.setText(null);
               lastinsert.setText(null);
               insertTel.setText(null);
               insertEmail.setText(null);
               insertBirth.setText(null);
               
               
           }
       });
       
       
     int idi=Integer.parseInt((String)data[0]);
     nextid=idi;
       
       
       // save button starting 
       
       save.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               
               nextid=nextid+1;
               
               
               
               databaseconection co=new databaseconection();
               
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
              
              
              modelo.addRow(  new Object[]{nextid,name,lastnap,telc,em,biy});
               
                table. validate();
               table. repaint();
              
              
              
              
               insername.setText(null);
               lastinsert.setText(null);
               insertTel.setText(null);
               insertEmail.setText(null);
               insertBirth.setText(null);
               
               }
               else{
                   
                    
               
               
               
               
          
               
               try {
                   co.MySQLConnection();
               } catch (Exception ex) {
                   Logger.getLogger(Tabla_patient.class.getName()).log(Level.SEVERE, null, ex);
               }
               
              
               
               int biy=Integer.parseInt(birthy);
               
              
               
              co.insertData("patient", name, lastnap,telc, em, biy);
              modelo.addRow(new Object[]{nextid,name,lastnap,telc,em,biy});
               insername.setText(null);
               lastinsert.setText(null);
               insertTel.setText(null);
               insertEmail.setText(null);
               insertBirth.setText(null);
               
                   
               }
               
               
               

           }
           }}); 
       
       
       
       
       
           }
         
         
     
        
       
       
  
  
    }
              
             
    
        
   
        
       
      
       
    
  

    

