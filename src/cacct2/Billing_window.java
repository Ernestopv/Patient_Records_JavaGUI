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
public class Billing_window extends JFrame  {
    
    
    public Billing_window() throws Exception{
        
        setSize(1000,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Billing");
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
               JMenuItem databasebilling=new JMenuItem("DatabaseBilling");
               databasebilling.setFont(new java.awt.Font("Tahoma", 10, 15));
               JMenuItem chargess=new JMenuItem("Charges");
               chargess.setFont(new java.awt.Font("Tahoma", 10, 15));
                JMenuItem billi=new JMenuItem("Print Bill");
               billi.setFont(new java.awt.Font("Tahoma", 10, 15));
               
               file.add(close);
               options.add(databasebilling);
               options.add(chargess);
               options.add(billi);
              
               
             
               
               add(barramenu, BorderLayout.BEFORE_FIRST_LINE);
        
         CardLayout c= new CardLayout(4,4);
        JPanel card=new JPanel(c);
        JPanel mainmenupremium=new JPanel(new GridLayout(2,0));
        JPanel mainmenu=new JPanel(new GridLayout(0,3));
        JPanel menu= new JPanel(new GridLayout(5,0));
        
        JButton patientsDatabase=new JButton("Patients Database billing");
        JButton charges=new JButton("Charges ");
        JButton logout=new JButton("logout");
        JLabel welcome=new JLabel("Welcome To Billing department ");
        welcome.setHorizontalAlignment(JLabel.CENTER);
        Border border = LineBorder.createGrayLineBorder();
        welcome.setBorder(border);
        
        patientsDatabase.setFont(new java.awt.Font("Tahoma", 0, 18));
        charges.setFont(new java.awt.Font("Tahoma", 0, 18));
        logout.setFont(new java.awt.Font("Tahoma", 0, 18));
        welcome.setFont(new java.awt.Font("Tahoma", 23, 18));
        JPanel space=new JPanel();
        JButton printb=new JButton("Print bill");
        printb.setFont(new java.awt.Font("Tahoma", 23, 18));
        
        
               
        
        
        
       
        menu.add(patientsDatabase);
        menu.add(charges);
        menu.add(printb);
        menu.add(logout);
      
        mainmenu.add(space);
        mainmenu.add(menu);
        mainmenupremium.add(welcome);
        mainmenupremium.add(mainmenu);
        //
        
         
        card.add("0",mainmenupremium);
        
        PendientBilling pebilling=new PendientBilling();
        card.add("1",pebilling);
      
        
        
        
      
        
       
        
        
        
        JPanel mainconten=new JPanel(new GridLayout(0,2));
        JPanel cont1=new JPanel();
        JPanel cont2=new JPanel();
        
        cont2.setBackground(Color.WHITE);
        cont1.setBackground(Color.gray);
        mainconten.add(cont1);
        mainconten.add(cont2);
        
        
        
        
        
        add(card);
        
        
        
        //adding action to logout 
        
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginUser();
            }
        });
        
        
        //adding action to patient history
        
        databasebilling.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
           try {
                    new PatientsHistoryBilling();
                } catch (Exception ex) {
                    Logger.getLogger(Billing_window.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            
            
            }
        });
        
        patientsDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                try {
                    new PatientsHistoryBilling();
                } catch (Exception ex) {
                    Logger.getLogger(Billing_window.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
        });
        
       
        
        
        charges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                c.show(card,"1");
                
            }
        });
        chargess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 c.show(card,"1");
            }
        });
        
        
        
        
        
        //adding action to print!!
        
        
        
        printb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new PrintBill();
                } catch (Exception ex) {
                    Logger.getLogger(Billing_window.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        billi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
                  try {
                    new PrintBill();
                } catch (Exception ex) {
                    Logger.getLogger(Billing_window.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        
        
        
        
        
    }
    
    
    
}
