/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacct2;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Netho
 */
public class LoginUser  extends JFrame implements ActionListener{
    
    String un=null;
    String pw=null;
    String sid=null;
    String pass=null;
     JPasswordField passwo=new JPasswordField(20);
     JTextField userinsert=new JTextField(20);
     Object idus;
     
   
     
    
    public LoginUser(){
        setTitle("Hospital Rodriguez (V.2.31)");
        setSize(900,700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         JMenuBar barramenu=new JMenuBar();
               this.setJMenuBar(barramenu);
               JMenu file=new JMenu("File");
               file.setFont(new java.awt.Font("Tahoma", 10, 15));
               
               barramenu.add(file);
               JMenuItem close=new JMenuItem("Close");
               close.setFont(new java.awt.Font("Tahoma", 10, 15));
               file.add(close);
               
             
               
               add(barramenu);
               
        
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.lightGray);
        JPanel content=new JPanel();
        content.setSize(900,700);
        content.setBackground(Color.white);
        
        content.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        GridBagConstraints con2 = new GridBagConstraints();
        
        
        userinsert.setSize(40,50);
        userinsert.setFont(new java.awt.Font("Tahoma",0,20));
       
        userinsert.setSize(50,40);
        JLabel usern=new JLabel("UserName: ");
        usern.setFont(new java.awt.Font("Tahoma",0,20));
        
        JLabel passwordu=new JLabel("Password");
       
        passwordu.setFont(new java.awt.Font("Tahoma",0,20));
        passwo.setFont(new java.awt.Font("Tahoma",0,20));
        
        JLabel welcome=new JLabel(" Hospital Rodriguez");
        welcome.setFont(new java.awt.Font("Consolas",10,20));
       
        con.gridx=-3;
        con.gridy=0;
        
        
        content.add(welcome,con);
        
        
        
        con.fill=GridBagConstraints.CENTER;
        con.gridx=1;
        con.gridy=2;
        content.add(usern,con);
        con.fill=GridBagConstraints.CENTER;
        con.gridx=2;
        con.gridy=2;
        content.add(userinsert,con);
        con.fill=GridBagConstraints.CENTER;
        con.gridx=1;
        con.gridy=3;
        content.add(passwordu,con);
        con.fill=GridBagConstraints.CENTER;
        con.gridx=2;
        con.gridy=3;
        content.add(passwo,con);
        
        JButton signin=new JButton("Sign in!");
        signin.setFont(new java.awt.Font("Tahoma", 0, 20));
        con.fill=GridBagConstraints.CENTER;
        con.gridx=2;
        con.gridy=5;
        
        
        content.add(signin,con);
        
        signin.addActionListener(this);
        signin.setActionCommand("consultorio");
        
        userinsert.addActionListener(this);
        userinsert.setActionCommand("otro");
        
        
        add(content);
        
        
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
       
        
        
        
        if(e.getActionCommand().equals("consultorio")){
            
           
            
           un=userinsert.getText().trim();
           pass=passwo.getText().trim();
           
           if (un.isEmpty()& pass.isEmpty()){
               
               JOptionPane.showMessageDialog(null, "please fill all the fields !");
               
           }
           else{
            try {
                 databaseconection con=new databaseconection();
                con.MySQLConnection();
                if(con.logginValidation(un, pass)==1){
                con.loggin(un, pass);
                
                
                    
                    
                    
                    
                    
                    
                    
                    con.closeConnection();
                    
                    idus=con.iduser;
                    dispose();
                }
                
                else{
                    JOptionPane.showMessageDialog(null, "Password or Username Incorrect!");
                    passwo.setText(null);
                    userinsert.setText(null);
                }
                    
                    
                    
                    
                    
                } catch (Exception ex) {
                   Logger.getLogger(LoginUser.class.getName()).log(Level.SEVERE, null, ex);
               }
            
            
              
               
                
              
                
            
                
            
                
                
                
              
                
                
                
                
                
                
         } 
            
          
        
                   
       
    }}}
    
    
    
 
      
