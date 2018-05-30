/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacct2;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Netho
 */
public class doctor_window  extends JFrame{
    
    public doctor_window() throws ClassNotFoundException, SQLException, Exception{
     setSize(1000,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Doctor");
        setLocationRelativeTo(null);
         setLayout(new BorderLayout());
        JMenuBar barramenu=new JMenuBar();
       barramenu.setLayout(new GridLayout(1,4));
      
       
         this.setJMenuBar(barramenu);
               JMenu file=new JMenu("File");
               JMenu options=new JMenu("options");
               options.setFont(new java.awt.Font("Tahoma", 10, 15));
               file.setFont(new java.awt.Font("Tahoma", 10, 15));
               
               barramenu.add(file);
               barramenu.add(options);
               JMenuItem close=new JMenuItem("Close");
               close.setFont(new java.awt.Font("Tahoma", 10, 15));
               JMenuItem checkm=new JMenuItem("check Messages");
               checkm.setFont(new java.awt.Font("Tahoma", 10, 15));
               JMenuItem patidata=new JMenuItem("Patient Database");
               patidata.setFont(new java.awt.Font("Tahoma", 10, 15));
                JMenuItem apo=new JMenuItem("Appointment");
               apo.setFont(new java.awt.Font("Tahoma", 10, 15));
               
               file.add(close);
               options.add(checkm);
               options.add(patidata);
               options.add(apo);
              
               
             
               
               add(barramenu, BorderLayout.BEFORE_FIRST_LINE);
        
        
        //cardlayout
        CardLayout c= new CardLayout(4,4);
        JPanel card=new JPanel(c);
        JPanel mainmenupremium=new JPanel(new GridLayout(2,0));
        JPanel mainmenu=new JPanel(new GridLayout(0,3));
        JPanel menu= new JPanel(new GridLayout(5,0));
        JButton checkM=new JButton("Check Messages");
        JButton patientsDatabase=new JButton("Patients Database");
        JButton appointments=new JButton("Appointments ");
        JButton logout=new JButton("logout");
        JLabel welcome=new JLabel("Welcome To Doctor department ");
        welcome.setHorizontalAlignment(JLabel.CENTER);
        Border border = LineBorder.createGrayLineBorder();
        welcome.setBorder(border);
        checkM.setFont(new java.awt.Font("Tahoma", 0, 18));
        patientsDatabase.setFont(new java.awt.Font("Tahoma", 0, 18));
        appointments.setFont(new java.awt.Font("Tahoma", 0, 18));
        logout.setFont(new java.awt.Font("Tahoma", 0, 18));
        welcome.setFont(new java.awt.Font("Tahoma", 23, 18));
        JPanel space=new JPanel();
       
        
        
               
        
        
        
        menu.add(checkM);
        menu.add(patientsDatabase);
        menu.add(appointments);
        menu.add(logout);
      
        mainmenu.add(space);
        mainmenu.add(menu);
        mainmenupremium.add(welcome);
        mainmenupremium.add(mainmenu);
        //
        
         
        card.add("0",mainmenupremium);
       /* MessageDo msndo=new MessageDo();
        card.add("1", msndo); */
        Appointment_Doctor apodo=new Appointment_Doctor();
        card.add("2", apodo);
        
        
        
      
        
       
        
        
        
        JPanel mainconten=new JPanel(new GridLayout(0,2));
        JPanel cont1=new JPanel();
        JPanel cont2=new JPanel();
        
        cont2.setBackground(Color.WHITE);
        cont1.setBackground(Color.gray);
        mainconten.add(cont1);
        mainconten.add(cont2);
        
        
        
        
        
        add(card);
        
        
        
        
        //check messages Action 
        
        checkM.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             validate();
             repaint();
             try {
                 new MessageDo();
             } catch (Exception ex) {
                 Logger.getLogger(doctor_window.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
     });
        
        checkm.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             
               validate();
             repaint();
             try {
                 new MessageDo();
             } catch (Exception ex) {
                 Logger.getLogger(doctor_window.class.getName()).log(Level.SEVERE, null, ex);
             }
             
         }
     });
        
        
        
        //action to logout 
        
        logout.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             dispose();
             new LoginUser();
         }
     });
        
        
        patientsDatabase.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             
             
             
             new PatientDo();
            
         }
     });
        
        
        patidata.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        
             new PatientDo();
         }
     });
        
        
        //action to appointment
        
        appointments.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            
             c.show(card,"2");
             
         }
     });
        
        
        apo.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        
             c.show(card,"2");
             
         }
     });
        
        
        
        close.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             System.exit(0);
         }
     });
        
        
       
    }
    
}
