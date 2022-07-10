package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;

public class Client extends JFrame implements ActionListener
{   JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;

    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    Client()
    {
        p1=new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7,97,84));
        p1.setBounds(0,0,400,50);
        add(p1);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("com/company/icons/3.png"));
        Image i2 =i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l1=new JLabel(i3);
        l1.setBounds(5,15,20,20);
        p1.add(l1);
        l1.addMouseListener(new MouseAdapter()
        {   public void mouseClicked(MouseEvent as)
           {    System.exit(0);
           }
        }) ;

        ImageIcon n1=new ImageIcon(ClassLoader.getSystemResource("com/company/icons/bunty.jpeg"));
        Image n2=n1.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT);
        ImageIcon n3=new ImageIcon(n2);
        JLabel l2=new JLabel(n3);
        l2.setBounds(35,5,40,40);
        p1.add(l2);

        ImageIcon v1=new ImageIcon(ClassLoader.getSystemResource("com/company/icons/video.png"));
        Image v2=v1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon v3=new ImageIcon(v2);
        JLabel l3=new JLabel(v3);
        l3.setBounds(290,15,20,20);
        p1.add(l3);

        ImageIcon c1=new ImageIcon(ClassLoader.getSystemResource("com/company/icons/phone.png"));
        Image c2=c1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon c3=new ImageIcon(c2);
        JLabel l4=new JLabel(c3);
        l4.setBounds(330,15,20,20);
        p1.add(l4);

        ImageIcon d1=new ImageIcon(ClassLoader.getSystemResource("com/company/icons/3icon.png"));
        Image d2=d1.getImage().getScaledInstance(5,30,Image.SCALE_DEFAULT);
        ImageIcon d3=new ImageIcon(d2);
        JLabel l5=new JLabel(d3);
        l5.setBounds(360,15,10,20);
        p1.add(l5);

        JLabel l6=new JLabel("Prachi");
        l6.setFont(new Font("SEN_SERIF", Font.PLAIN,15));//change the font can use BOLD too
        l6.setForeground(Color.WHITE);//change color
        l6.setBounds(85,10,100,15);
        p1.add(l6);

        JLabel l7=new JLabel("Active now");
        l7.setFont(new Font("SEN_SERIF", Font.PLAIN,10));
        l7.setForeground(Color.WHITE);
        l7.setBounds(90,30,150,10);
        p1.add(l7);

        t1=new JTextField();
        t1.setBounds(5,550,320,40);
        t1.setFont(new Font("SAN_SERIF",Font.PLAIN,17));
        add(t1);

        b1= new JButton("Send");
        b1.setBounds(323,550,72,39);
        b1.setBackground(new Color(7,97,84));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF",Font.PLAIN,12));
        b1.addActionListener(this);
        add(b1);

        a1=new JTextArea();
        a1.setBounds(3,53,395,493);
        a1.setFont(new Font("SAN_SERIF",Font.PLAIN,15));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        add(a1);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(400,600);
        setLocation(900,50);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        try {
        String out = t1.getText();
        a1.setText(a1.getText()+"\n\t\t\t"+out);
        dout.writeUTF(out);
        t1.setText(" ");
            }
        catch(Exception e)
        {

        }
    }
    public static void main( String[] args) {
        new Client().setVisible(true);
        try {
            s = new Socket("127.0.0.1", 6001);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            String msginput = "";//to print the message
            msginput = din.readUTF();
            a1.setText(a1.getText() + "\n" + msginput);
        } catch (Exception e) {

        }
    }
}
