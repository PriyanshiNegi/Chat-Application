package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.*;
import java.io.*;

public class Server extends JFrame implements ActionListener
{
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;

    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;

    Boolean typing;
    Server()
    {
        p1=new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7,97,84));
        p1.setBounds(0,0,400,55);
        add(p1);

        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("com/company/icons/3.png"));
        Image i2 =i1.getImage().getScaledInstance(25,20,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l1=new JLabel(i3);
        l1.setBounds(2,15,25,19);
        p1.add(l1);
        l1.addMouseListener(new MouseAdapter()
        {   public void mouseClicked(MouseEvent as) 
              {    System.exit(0);             
              }
        }) ;

        //DP
        ImageIcon n1=new ImageIcon(ClassLoader.getSystemResource("com/company/icons/gaitonde.jpeg"));
        Image n2=n1.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT);
        ImageIcon n3=new ImageIcon(n2);
        JLabel l2=new JLabel(n3);
        l2.setBounds(30,6,40,40);
        p1.add(l2);

        //VIDEOCALL
        ImageIcon v1=new ImageIcon(ClassLoader.getSystemResource("com/company/icons/video.png"));
        Image v2=v1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon v3=new ImageIcon(v2);
        JLabel l3=new JLabel(v3);
        l3.setBounds(290,15,26,28);
        p1.add(l3);

        //PHONECALL
        ImageIcon c1=new ImageIcon(ClassLoader.getSystemResource("com/company/icons/phone.png"));
        Image c2=c1.getImage().getScaledInstance(25,26,Image.SCALE_DEFAULT);
        ImageIcon c3=new ImageIcon(c2);
        JLabel l4=new JLabel(c3);
        l4.setBounds(330,17,26,28);
        p1.add(l4);

        //OPTIONS
        ImageIcon d1=new ImageIcon(ClassLoader.getSystemResource("com/company/icons/3icon.png"));
        Image d2=d1.getImage().getScaledInstance(12,24,Image.SCALE_DEFAULT);
        ImageIcon d3=new ImageIcon(d2);
        JLabel l5=new JLabel(d3);
        l5.setBounds(367,20,12,24);
        p1.add(l5);

        JLabel l6=new JLabel("Priyanshi");
        l6.setFont(new Font("SEN_SERIF", Font.PLAIN,16));//change the font can use BOLD too
        l6.setForeground(Color.WHITE);//change color
        l6.setBounds(85,10,100,15);
        p1.add(l6);

        JLabel l7=new JLabel("Active now");
        l7.setFont(new Font("SEN_SERIF", Font.PLAIN,12));
        l7.setForeground(Color.WHITE);
        l7.setBounds(87,30,150,10);
        p1.add(l7);
        Timer t=new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ee) 
            {
               if(typing)
               {
                   l7.setText("Active Now");
               }
            }
        });
        t.setInitialDelay(2000);//value in milliseconds 1 sec=1000 mmsec


        t1=new JTextField();
        t1.setBounds(5,550,320,40);
        t1.setFont(new Font("SAN_SERIF",Font.PLAIN,17));
        add(t1);
        t1.addKeyListener(new KeyAdapter()
        {
            @Override 
            public void keyPressed(KeyEvent ke)
            {
                l7.setText("typing...");
                t.stop();
                typing=true;
            }
            public void keyReleased(KeyEvent ke)
            {
               typing =false;
               if(t.isRunning())
                   t.start();
            }

        });

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
        setLocation(400,50);
        setUndecorated(true);
        setVisible(true);
    }




    public void actionPerformed(ActionEvent ae)
    {
        try {
            String out = t1.getText();
            a1.setText(a1.getText()+"\n\t\t\t"+out);
            dout.writeUTF(out);
            t1.setText("");
           }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
    public static void main( String[] args)
    {
        new Server().setVisible(true);
        String msginput="";
        try
        {
            skt =new ServerSocket(6001);
            s=skt.accept();
            din=new DataInputStream(s.getInputStream());
            dout=new DataOutputStream(s.getOutputStream());
            msginput=din.readUTF();//incoming data read
            a1.setText(a1.getText()+"\n"+msginput);

        }
        catch(Exception e)
        {

        }

    }
}
