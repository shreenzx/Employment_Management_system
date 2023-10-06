/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Employee_Management;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Employee_attendance extends JFrame implements ActionListener {
    
    JLabel l1,l2,l3,l4,l5;
    Choice c1,c2,c3;
    JButton bt1,bt2;
    JTextField tf1,tf2;
    Font f;
    JPanel p;
    
    Employee_attendance() throws SQLException{
        super("Employee Attendance");
        setSize(450,300);
        setLocation(300,200);
        setResizable(false);
        f = new Font("senserif",Font.BOLD,15);
        
        l1 = new JLabel("Select Employee ID");
        l2 = new JLabel("First half");
        l3 = new JLabel("Second half");
        l4 = new JLabel("Name");
        l5 = new JLabel("Email");
        
        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        l4.setFont(f);
        l5.setFont(f);
        
        tf1 = new JTextField();
        tf2 = new JTextField();
        
        tf1.setFont(f);
        tf2.setFont(f);
        
        tf1.setEditable(false);
        tf2.setEditable(false);
        
        c2 = new Choice();
        c2.add("Present");
        c2.add("Absent");
        
        c3 = new Choice();
        c3.add("Present");
        c3.add("Absent");
        
        c2.setFont(f);
        c3.setFont(f);
        
        bt1 = new JButton("Submit");
        bt2 = new JButton("Close");
        
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.WHITE);
        bt2.setBackground(Color.BLACK);
        bt2.setForeground(Color.WHITE);
        
        bt1.setFont(f);
        bt2.setFont(f);
        
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        c1 = new Choice();
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            while(rs.next()) {
                c1.add(rs.getString("empId"));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        p = new JPanel();
        p.setLayout(new GridLayout(6,2,10,10));
        
       
        p.add(l1);
        p.add(c1);
        p.add(l4);
        p.add(tf1);
        p.add(l5);
        p.add(tf2);
        p.add(l2);
        p.add(c2);
        p.add(l3);
        p.add(c3);
        p.add(bt1);
        p.add(bt2);
        add(p);
        
        c1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent arg){
                try{
                    Conn c = new Conn();
                    String eid = c1.getSelectedItem();
                    String query = "select * from employee where empId = '"+eid+"'";
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()){
                        tf1.setText(rs.getString("name"));
                        tf2.setText(rs.getString("email"));
                        
                    }
                } catch(Exception exx){
                    exx.printStackTrace();
                }
            }
        });
            
    }
    public void actionPerformed(ActionEvent ev){
            if(ev.getSource()== bt1){
              String ch_eid = c1.getSelectedItem();
              String ch_first_half = c2.getSelectedItem();
              String ch_second_half = c3.getSelectedItem();
              String name = tf1.getText();
              String email = tf2.getText();
              String dt = new java.util.Date().toString();
              try{
                Conn conn = new Conn();
                String query = "insert into attendance values('"+ch_eid+"','"+name+"','"+email+"','"+ch_first_half+"','"+ch_second_half+"',  '"+dt+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                setVisible(false);
              } 
              catch(Exception ex1){
                  ex1.printStackTrace();
              }
              
            }
            if(ev.getSource()== bt2){
                JOptionPane.showMessageDialog(null, "are you sure to close");
                setVisible(false);
                new Home();
            }
    }
    public static void main(String args[]){
        try {
            new Employee_attendance().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Employee_attendance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
