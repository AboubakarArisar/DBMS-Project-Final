import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.border.Border;
public class Welcome implements ActionListener {

    JFrame jfram;
    JButton log;
    JButton sinup;
    Welcome(){
        //Creating JFrame  and initialize the vales and attributes
        jfram = new JFrame("Welcome Page");
        jfram.setVisible(true);
        jfram.setDefaultCloseOperation(jfram.EXIT_ON_CLOSE);
        jfram.setBounds(0,0,1000,600);
        jfram.setLayout(null);

        //creating a font  and in labels
        Font font = new Font ("Times new Roman " , Font.BOLD,15);
        // creating a font for intro labels
        Font f1 = new Font("Times new Roman",Font.BOLD,34);

        // creating a JLabel for displaying program name
        JLabel intro = new JLabel ("Let's Digitlize ");
        intro.setBounds(800,50,500,50);
        intro.setFont(f1);
        jfram.add(intro);

        // JLabel for showing the project name
        JLabel proj = new JLabel("Expense Management System");
        proj.setBounds(640,100,800,50);
        proj.setFont(f1);
        jfram.add(proj);

        // creating a label , pannel and imageicon to show the image in side of frame adding border effect.

        JPanel panel2 = new JPanel();
        panel2.setBounds(400,200,970,180);
        Border border2 = BorderFactory.createLineBorder(new Color(225,246,255),40);
        ImageIcon icon2 = new ImageIcon("asd1.jpg");
        JLabel pic = new JLabel(icon2);
        pic.setBounds(0,0,400,720);
        pic.setBackground(new Color(225,246,255));
        pic.setBorder( border2);
        jfram.add(pic);
        jfram.add(panel2);
        Border border = BorderFactory.createLineBorder(new Color(204,239,255),10);
        panel2.setBorder(border);

        panel2.setBackground(new Color(255,251,251));


        //adding Jlabel to show the information related the project

        JLabel kl = new JLabel("The purpose of Let's Digitlize is to provide salespeople a chance to manage their");
        JLabel lm = new JLabel("customers, costs, and sales.Given how challenging it is for an individual to manage their finances and maintain");
        JLabel jk = new JLabel ("manually, which leaves them open to losing or misplacing their records.  Moreover, estimating earnings and producing");
        JLabel mn = new JLabel("Time-consuming jobs that leave little opportunity for error are being reported. This programme offers several");
        JLabel mh = new JLabel("choices that make it possible to manage finances effectively.Let's start off having a nice time.");
        mn.setBounds (0,200,1000,100);
        mn.setFont(font);
        mh.setBounds (0,200,1000,100);
        mh.setFont(font);
        kl.setBounds (0,200,1000,100);
        panel2.add(kl);
        kl.setFont(font);
        lm.setBounds (0,250,1000,100);
        panel2.add(lm);
        lm.setFont(font);
        jk.setBounds (0,300,1000,100);
        panel2.add(jk);
        jk.setFont(font);
        panel2.add(mn);
        panel2.add(mh);

        // creating a conatiner object to set the background of frame
        Container c = jfram.getContentPane();
        c.setBackground(new Color(3, 184, 255));


        // create a jbutton for signup and login  also adding the action
        log = new JButton("Login");
        log.setBounds (850,430,100,40);
        log.setBackground(Color.orange);
        jfram.add(log);
        log.setFocusable(false);
        sinup = new JButton("Signup ");
        sinup.setBounds (980,430,100,40);
        jfram.add(sinup);
        sinup.setFocusable(false);
        sinup.setBackground(Color.orange);
        log.addActionListener(this);
        sinup.addActionListener(this);


        jfram.setExtendedState(jfram.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource()==log){
            jfram.dispose();
            try {
                new Login_page();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource()==sinup){
            jfram.dispose();
            try {
                new Create_signupTable();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new Signup_page ();


        }
    }
}





/*
 *
 *
 *
 * create a signup table in jdbc if it is not exist in our program
 *
 *
 *
 * */

class Create_signupTable{
    Create_signupTable() throws SQLException {
try{
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();

        // SQL statement to create a table in MySQL database
        String createTableSQL = "CREATE TABLE IF NOT EXISTS signup ("
                + "id INT(11) NOT NULL AUTO_INCREMENT,"
                + "first_name VARCHAR(250),"

                +"last_name VARCHAR(252),"
                +"username VARCHAR(252),"
                +"cnic VARCHAR(20),"
                + "password  VARCHAR(250) ,"
                + "address VARCHAR(252),"
                + "Date_of_Birth VARCHAR(252),"
                +"Gender VARCHAR(252),"
                + "PRIMARY KEY (id)"
                + ")";

        stmt.executeUpdate(createTableSQL);
        stmt.close();
        conn.close();

            System.out.println("SIGNUP Table created successfully.");
        } catch (SQLException ex) {
            System.out.println("Error creating table: " + ex.getMessage());
        }}
}

