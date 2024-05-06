import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class Login_page extends JFrame implements ActionListener {
    JPasswordField p1;// password field for password
    JTextField t1; //text field for user name
    JButton log;
    JCheckBox box;
    JFrame jframe = new JFrame("Login page");
    JButton sinup;
    DBConnection con = new DBConnection();
    Connection conn = con.getConnection();

    Login_page() throws SQLException {

        /*
        * this block of code initilize the frame
        *  also set the fram icon
        * set label with project name
        * set label with project means
         *
        * */


        jframe.setBounds(0,0,800,600);
        jframe.setVisible(true);
        Container c = jframe.getContentPane();
        c.setLayout(null);
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        JLabel intro = new JLabel ("Let's Digitlize");
        intro.setBounds(300,40,500,50);
        Font f1 = new Font("Times new Roman",Font.BOLD,34);
        intro.setFont(f1);
        ImageIcon icon = new ImageIcon("Iconpro.jpg");
        jframe.setIconImage(icon.getImage());
        c.add(intro);
        JLabel proj = new JLabel("Expense Management System");
        proj.setBounds(180,100,1000,50);
        proj.setFont(f1);
        c.add(proj);


        /*
        * Jlabel for user name
        * JTextField to get the user name
        *
        * */


        Font f2 = new Font("Times new Roman",Font.BOLD,36);
        JLabel user = new JLabel ("User Name");
        user.setBounds(300,170,500,50);
        user.setFont(f2);
        user.setForeground(Color.black);
        jframe.add(user);

        // JTextField for user

        t1 = new JTextField();
        t1.setBounds(300,230,200,40);
        c.add(t1);

        // JLabel for displaying the password
        JLabel pass = new JLabel ("Password");
        pass.setForeground(Color.BLACK);
        pass.setBounds(300,270,300,50);
        pass.setFont(f2);
        jframe.add(pass);

        // jpassword field for geting the password from the user

        p1 = new JPasswordField();
        p1.setEchoChar('*');
        p1.setBounds(300,330,200,40);
        c.add(p1);

        // login button to login account
        log = new JButton("Login");
        log.setBounds(300,470,100,50);
        log.setBackground(Color.orange);
        log.setFocusable(false);
        c.add(log);

        // signup button to create a new account if he is new user
        sinup = new JButton("Signup");
        sinup.setBounds(450,470,100,50);
        sinup.setBackground(Color.orange);
        sinup.setFocusable(false);
        c.add(sinup);

        // check box for showing and hiding the password
        JLabel lbe = new JLabel("Show Password");
        lbe.setForeground(Color.BLACK);
        lbe.setLocation(340,400);
        lbe.setFont(f2);

        c.add(lbe);
        box = new JCheckBox();
        box.setBounds(300,400,40,50);
        box.setFont(f2);
        box.setOpaque(false);
        c.add(box);
        box.setForeground(Color.BLACK);

        // image icon and label for seting the backgroung image of fram
        ImageIcon icon0 = new ImageIcon("abcde.png");
        JLabel lb = new JLabel();
        lb.setIcon(icon0);
        lb.setBounds(0,0,800,580);
        jframe.add(lb);

        // seting the frame size fix and adding action on buttons
        jframe.setResizable(false);
        log.addActionListener( this);
        sinup.addActionListener( this);
        box.addActionListener( this);

    }
    public void actionPerformed(ActionEvent e){
        // action performed in login button
        if (e.getSource() ==log){
//            JOptionPane.showMessageDialog(this,"invalid password and user name try again");
            try {
                String username = t1.getText();
                String pwd = String.valueOf(p1.getPassword());

                // checking  the username and password
                PreparedStatement pst = (PreparedStatement) conn.prepareStatement("select*from signup where username=? and password=?");
                pst.setString(1, username);
                pst.setString(2, pwd);
                ResultSet r = pst.executeQuery();
                if (r.next()) {
                // if username and password found then moving toward dashboard
                    jframe.dispose();
                     new D();

                } else {
                    JOptionPane.showMessageDialog(null, "incorrect id or password");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        // adding action in signup button clicking movetoward  signup page
        if (e.getSource()==sinup){
            jframe.dispose();
            new Signup_page ();
        }
        // checking if checkbox if checked the show password else hide password
        if(e.getSource()==box){

            if (box.isSelected()){
                p1.setEchoChar((char)0);}
            else {
                p1.setEchoChar('*');
            }
        }


    }

}

