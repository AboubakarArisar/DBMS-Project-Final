import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.lang.String;

class Signup_page extends JFrame implements ActionListener {
    JCheckBox con;// checkbox for terms and conditions
    JButton btn;//login button
    JCheckBox box;//checkbox for show password
    JTextField namel1;// textField for lastname
    JRadioButton  female;
    JTextField name1;// textfield for username

    JButton cac;// signup button
    TextArea tal;// text area for address
    JTextField usn1;// field for username
    JTextArea screen;
    JComboBox day;
    JComboBox month;


    JComboBox year;
    JTextField cnic;
    JPasswordField pass1;// password
    JFrame jframe;
    Signup_page(){


        jframe = new JFrame ("Signup page");
        jframe.setBounds (0,0,820,650);
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        Container c = jframe.getContentPane();
        c.setLayout(null);

        // font used in set font of signup
        Font f1 = new Font("Times new Roman",Font.BOLD,20);

        // setup the fram icon
        ImageIcon icon = new ImageIcon("Iconpro.jpg");
        jframe.setIconImage(icon.getImage());

        // setup font for other labels like first name etc
        Font f2 = new Font("Times new Roman",Font.BOLD,34);
        JLabel Intro = new JLabel ("Signup Form ");
        Intro.setBounds(300,0,300,50);
        Intro.setFont(f2);
        Intro.setForeground(new Color(0xF4F4F6));
        jframe.add(Intro);

        // label and textfield of first name
        JLabel name = new JLabel("First Name");
        name.setFont(f1);
        name.setForeground(Color.white);
        name.setBounds(150,70,200,50);
         c.add(name);
        name1 = new JTextField();
        name1.setBounds(490,80,110,20);
        c.add(name1);

        // label and textfield of last name
        JLabel namel = new JLabel("Last Name");
        namel.setFont(f1);
        namel.setForeground(Color.white);
        namel.setBounds(150,100,200,50);
        c.add(namel);
        namel1 = new JTextField();
        namel1.setBounds(490,110,110,20);
        c.add(namel1);

        // lebel and textfield of username
        JLabel usn = new JLabel("Username");
        usn.setFont(f1);
        usn.setForeground(Color.white);
        usn.setBounds(150,142,150,50);
        c.add(usn);
        usn1 = new JTextField();
        usn1.setBounds(490,155,110,20);
        c.add(usn1);

        // label and password field for password
        JLabel pass = new JLabel("Password");
        pass.setFont(f1);
        pass.setForeground(Color.white);
        pass.setBounds(150,180,150,50);
        c.add(pass);
        pass1 = new JPasswordField();
        pass1.setBounds(490,195,110,20);
        pass1.setEchoChar('*');
        c.add(pass1);

        //label and text area for cnic number
        JLabel Cnic = new JLabel ("CNIC Number");
        Cnic.setBounds(150,220,200,50);
        Cnic.setFont(f1);
        Cnic.setForeground(Color.white);
        c.add(Cnic);
        cnic = new JTextField ();
        cnic.setBounds(490,235,110,20);
        c.add(cnic);

        // label and radio button for gender
        JLabel gender = new JLabel("Gender");
        gender.setBounds (150,250,100,50);
        gender.setFont(f1);
        gender.setForeground(Color.white);
        c.add(gender);
        JRadioButton male = new JRadioButton("Male");
        male.setBounds(390,260,100,30);
        male.setFont(f1);
        male.setForeground(Color.white);
        c.add(male);
        male.setOpaque(false);
        male.setSelected(true);

        female = new JRadioButton("Female");
        female.setBounds(530,260,100,30);
        female.setFont(f1);
        female.setForeground(Color.white);
        c.add(female);
        female.setOpaque(false);

        // grouping the male and female radio buttons
        ButtonGroup gen = new ButtonGroup();
        gen.add(male);
        gen.add(female);

        // label and combo box for date of birth
        JLabel dob = new JLabel ("Date of Birth");
        dob.setBounds(150,300,300,30);
        dob.setForeground(Color.white);
        dob.setBackground(new Color(0xFAFAFD));
        dob.setFont(f1);
        c.add(dob);
        String d[]={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String m[]={"01","02","03","04","05","06","07","08","09","10","11","12"};
        String y[]={"1940","1941","1942","1943","1944","1945","1946","1947","1948","1949","1950","1951","1952","1953","1954","1955","1956","1957","1958","1959","1960","1961","1962","1963","1964","1965","1966","1967","1968","1969","1970","1971","1972","1973","1974","1975","1976","1977","1978","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023"};
        day = new JComboBox(d);
        day.setBounds(320,315,80,30);
        c.add(day);
        month = new JComboBox(m);
        month.setBounds(430,315,80,30);
        c.add(month);
        year = new JComboBox(y);
        year.setBounds(520,315,80,30);
        c.add(year);

       // label and text area for address
        JLabel adre = new JLabel("Address");
        adre.setBounds(150,340,100,50);
        adre.setForeground(Color.white);
        adre.setFont(f1);
        c.add(adre);
        tal = new TextArea();
        tal.setBounds(330,360,250,50);
        c.add(tal);

        // label and check box for terms and conditions
        con = new JCheckBox("Please accept All terms and conditions");
        con.setBounds(150,445,350,30);
        con.setFont(f1);
        con.setForeground(Color.white);
        con.setOpaque(false);
        c.add(con);

        // button for signup
        cac=new JButton("Signup");
        cac.setBounds(280,490,100,50);
        cac.setFont(f1);
        cac.setFocusable(false);
        c.add(cac);

       // button for login
        btn = new JButton("Login");
        btn.setBounds(400,490,100,50);
        btn.setFont(f1);
        btn.setFocusable(false);
        c.add(btn);

         // check box for unhiding the password
        box = new JCheckBox("Show Password");
        box.setBounds(500,445,200,30);
        c.add(box);
        box.setForeground(Color.white);
        box.setOpaque(false);

        //adding the background of fram
        ImageIcon pic =new  ImageIcon("Untitled design (3).png");
        JLabel picc = new JLabel();
        picc.setIcon(pic);
        picc.setBounds(0,0,pic.getIconWidth(),pic.getIconHeight());
         jframe.add(picc);


        jframe.setResizable(false);
        cac.addActionListener( this);
        btn.addActionListener(this);
        box.addActionListener(this);


    }
    public void actionPerformed(ActionEvent ee){
        String fname = name1.getText();
        String lname = namel1.getText();
        String user = usn1.getText();
        String nic = cnic.getText();
        String passw = pass1.getText();
        String gen = "Male";

        // action on check box to show password
        if (ee.getSource()==box){
            if (box.isSelected()){
                pass1.setEchoChar((char)0);}
            else {
                pass1.setEchoChar('*');
            }
        }
        // action on login page
        if (ee.getSource()==btn){
            jframe.dispose();
            try {
                new Login_page();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // action on signup page
        if (ee.getSource()==cac){

            if (con.isSelected()){

                if (female.isSelected()){
                    gen = "Female";
                }
                String dob =day.getSelectedItem()+"\\"+month.getSelectedItem()+"\\"+year.getSelectedItem();
                String adress = tal.getText();
DBConnection con = new DBConnection();
                Connection conn = null;
                try {
                    conn = con.getConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {

                    String sql = "INSERT INTO signup (first_name, last_name, username,  cnic, password,address,Date_of_Birth,Gender) VALUES (?,?,?, ?, ?, ?, ?, ?)";
                    PreparedStatement pst = conn.prepareStatement(sql);

                    pst.setString(1,fname);
                    pst.setString(2,lname);
                    pst.setString(3,user);
                    pst.setString(4,nic);
                    pst.setString(5,passw);
                    pst.setString(6,adress);
                    pst.setString(7,dob);
                    pst.setString(8,gen);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "your Signup form is submited sucessfully\n Click login Button to login your account");
                    conn.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

                try {
                    new Create_Client_List_Table();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    new Create_ProfitLoss_Table();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
            else {
                JOptionPane.showMessageDialog(null, "Please first accept the terms and condition");
            }

        }

    }

}
/*
 *
 * Create a default profit loss table
 * during signup
 *
 * code
 *
 *
 * */
class Create_ProfitLoss_Table {
    //     create new table
    DBConnection con = new DBConnection();
    Connection conn = con.getConnection();

    // Connection details for MySQL database

    String Tname = "Profit_Loss_History";
    Create_ProfitLoss_Table() throws SQLException {

        // SQL statement to create a table in MySQL database
        String createTableSQL = "CREATE TABLE IF NOT EXISTS "+Tname+" ("
                + "id INT(11) NOT NULL AUTO_INCREMENT,"
                + "Monthly_Investment VARCHAR(250),"
                +"Monthly_Sales VARCHAR(250),"
                +"Calculating_Date VARCHAR(255),"
                +"Additional_Details VARCHAR(255),"
                +"Total_Profit VARCHAR(255),"
                +"Percentage_Profit VARCHAR(255),"
                + "Total_Loss VARCHAR(250),"
                +"Percentage_Loss VARCHAR(255),"
                + "PRIMARY KEY (id)"
                + ")";

        try {

            // Create a Statement object
            Statement stmt = conn.createStatement();

            // Execute the SQL statement to create the table
            stmt.executeUpdate(createTableSQL);

            // Close the Statement and Connection objects
            stmt.close();
            conn.close();

            System.out.println("Table created successfully.");
        } catch (SQLException ex) {
            System.out.println("Error creating table: " + ex.getMessage());
        }}
}
/*
* creating a databese of user name
* and use this databese to store the user clients information
*
* */






/*
*
* create a default table will bi create in user database to store his clients list
* */

 class Create_Client_List_Table {
    //     create new table


    // Connection details for MySQL database
DBConnection con = new DBConnection();
Connection conn = con.getConnection();
    String Tname = "Client_List ";
    Create_Client_List_Table() throws SQLException {

        // SQL statement to create a table in MySQL database
        String createTableSQL = "CREATE TABLE IF NOT EXISTS "+Tname+" ("
                + "id INT(11) NOT NULL AUTO_INCREMENT,"
                + "first_name VARCHAR(250),"
                +"last_name VARCHAR(250),"
                +"Caste VARCHAR(255),"
                +"Mobile_number VARCHAR(12),"
                +"CNIC VARCHAR(20),"
                +"Address VARCHAR(255),"
                + "email VARCHAR(250),"
                + "PRIMARY KEY (id)"
                + ")";

        try {
            // Connect to MySQL database using JDBC driver

            // Create a Statement object
            Statement stmt = conn.createStatement();

            // Execute the SQL statement to create the table
            stmt.executeUpdate(createTableSQL);

            // Close the Statement and Connection objects
            stmt.close();
            conn.close();

            System.out.println("Table created successfully.");
        } catch (SQLException ex) {
            System.out.println("Error creating table: " + ex.getMessage());
        }}
}

