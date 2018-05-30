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
public class reception_window extends JFrame  implements ActionListener{
       
       
    
    public reception_window() throws ClassNotFoundException, SQLException, Exception {
        
        setSize(1000,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Reception");
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
               JMenuItem pat=new JMenuItem("+patient");
               pat.setFont(new java.awt.Font("Tahoma", 10, 15));
               JMenuItem appoin=new JMenuItem("+ Appointment");
               appoin.setFont(new java.awt.Font("Tahoma", 10, 15));
                JMenuItem billi=new JMenuItem("Billing");
               billi.setFont(new java.awt.Font("Tahoma", 10, 15));
               JMenuItem mess=new JMenuItem("+ Message");
               mess.setFont(new java.awt.Font("Tahoma", 10, 15));
               file.add(close);
               options.add(pat);
               options.add(appoin);
               options.add(billi);
               options.add(mess);
               
             
               
               add(barramenu, BorderLayout.BEFORE_FIRST_LINE);
               
      
        //cardlayout
        CardLayout c= new CardLayout(4,4);
        JPanel card=new JPanel(c);
        JPanel mainmenupremium=new JPanel(new GridLayout(2,0));
        JPanel mainmenu=new JPanel(new GridLayout(0,3));
        JPanel menu= new JPanel(new GridLayout(5,0));
        JButton addPa=new JButton("Add Patient");
        JButton addApointment=new JButton("Add appointment");
        JButton addMessage=new JButton("Add message");
        JButton logout=new JButton("logout");
        JLabel welcome=new JLabel("Welcome To Reception department ");
        welcome.setHorizontalAlignment(JLabel.CENTER);
        Border border = LineBorder.createGrayLineBorder();
        welcome.setBorder(border);
        addPa.setFont(new java.awt.Font("Tahoma", 0, 18));
        addApointment.setFont(new java.awt.Font("Tahoma", 0, 18));
        addMessage.setFont(new java.awt.Font("Tahoma", 0, 18));
        logout.setFont(new java.awt.Font("Tahoma", 0, 18));
        welcome.setFont(new java.awt.Font("Tahoma", 23, 18));
        JPanel space=new JPanel();
        JButton billing=new JButton("Billing");
        billing.setFont(new java.awt.Font("Tahoma", 23, 18));
        
        
               
        
        
        
        menu.add(addPa);
        menu.add(addApointment);
        menu.add(billing);
        menu.add(addMessage);
        menu.add(logout);
      
        mainmenu.add(space);
        mainmenu.add(menu);
        mainmenupremium.add(welcome);
        mainmenupremium.add(mainmenu);
        //
        
        Tabla_patient addp=new Tabla_patient();
        card.add("0",mainmenupremium);
        card.add("1",addp);
        
        Appointment appointment=new Appointment();
        card.add("2",appointment);
        
        MessageRe messagere=new MessageRe();
        card.add("3",messagere);
        
        billingPatient bill=new billingPatient();
        card.add("4",bill);
        
        
        
       
        
        
        
        JPanel mainconten=new JPanel(new GridLayout(0,2));
        JPanel cont1=new JPanel();
        JPanel cont2=new JPanel();
        
        cont2.setBackground(Color.WHITE);
        cont1.setBackground(Color.gray);
        mainconten.add(cont1);
        mainconten.add(cont2);
        
        
        
        
        
        add(card);
        
        
        
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                new LoginUser();
            }
        });
        
        
        addPa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                
                c.show(card, "1");
              
            }
        });
        
        pat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.show(card, "1");
            }
        });
        
        
        addApointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                c.show(card, "2");
                
            }
        });
        
        appoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           c.show(card, "2");     
            }
        });
        
        addMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.show(card, "3");
            }
        });
        
        mess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             c.show(card, "3");     
            }
        });
        
        billing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                c.show(card, "4");
                
            }
        });
        
        billi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.show(card, "4");
            }
        });
        
       close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               System.exit(0);
            }
        });
        
        
               
        
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
        
       
        
        
    
    
    
    
}
