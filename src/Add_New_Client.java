import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Add_New_Client extends Menu implements ActionListener {

    JTextField fnt; //first name
    JTextField lnt;// last name
    JTextField cst;// cast
    JTextField mbt;// mobile number
    JTextField nict;// cnic number
    JTextField emt;// email
    JTextField adrt;// address
    JButton btn;    // save button
    JLabel save;   // show message
    String db;
    JButton another;
    JButton back;
    DBConnection con = new DBConnection();
    Connection conn = con.getConnection();
    public void createClientListTable() {
        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS client_list ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "first_name VARCHAR(255),"
                    + "last_name VARCHAR(255),"
                    + "Caste VARCHAR(255),"
                    + "Mobile_number VARCHAR(255),"
                    + "CNIC VARCHAR(255),"
                    + "Address VARCHAR(255),"
                    + "email VARCHAR(255)"
                    + ")";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createTableSQL);
            System.out.println("Table 'client_list' created successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create table 'client_list'", e);
        }
    }

    Add_New_Client() throws SQLException {
        createClientListTable();
        jframe.setTitle("Create Client");
        jframe.setBounds(50,50,500,500);
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        jframe.setLayout(null);
        jframe.setVisible(true);
        Container c = jframe.getContentPane();
        c.setBackground(new Color(7, 45, 68));

        JLabel fname = new JLabel("First_Name");
        fname.setBounds(100,20,100,50);
        fname.setForeground(Color.white);
        jframe.add(fname);

        // set frame icon
        ImageIcon icon = new ImageIcon("Iconpro.jpg");
        jframe.setIconImage(icon.getImage());

        fnt = new JTextField();
        fnt.setBounds(280,30,100,20);
        jframe.add(fnt);

        JLabel lname = new JLabel("Last_Name");
        lname.setBounds(100,50,100,50);
        lname.setForeground(Color.white);
        jframe.add(lname);

        lnt = new JTextField();
        lnt.setBounds(280,60,100,20);
        jframe.add(lnt);

        JLabel cast = new JLabel("Caste");
        cast.setForeground(Color.white);
        cast.setBounds(100,90,100,50);
        jframe.add(cast);

        cst = new JTextField();
        cst.setBounds(280,100,100,20);
        jframe.add(cst);

        JLabel mbn = new JLabel("Mobile Number");
        mbn.setBounds(100,130,150,50);
        mbn.setForeground(Color.white);
        jframe.add(mbn);

        mbt = new JTextField();
        mbt.setBounds(280,140,100,20);
        jframe.add(mbt);

        JLabel nic = new JLabel("CNIC  Number");
        nic.setForeground(Color.white);
        nic.setBounds(100,170,150,50);
        jframe.add(nic);

        nict = new JTextField();
        nict.setBounds(280,180,100,20);
        jframe.add(nict);

        JLabel em = new JLabel("Email");
        em.setForeground(Color.white);
        em.setBounds(100,210,100,50);
        jframe.add(em);

        emt = new JTextField();
        emt.setBounds(280,220,100,20);
        jframe.add(emt);

        JLabel adr = new JLabel("Address");
        adr.setForeground(Color.white);
        adr.setBounds(100,250,100,50);
        jframe.add(adr);

        adrt = new JTextField();
        adrt.setBounds(280,260,100,20);
        jframe.add(adrt);

        btn = new JButton("Save");
        btn.setBounds(190,330,100,20);
        btn.setFocusable(false);
        jframe.add(btn);

        another = new JButton("Add another Client");
        another.setBounds(300,330,150,20);
        another.setFocusable(false);
        jframe.add(another);

        back = new JButton("Back");
        back.setBounds(80,330,100,20);
        back.setFocusable(false);
        jframe.add(back);
        another.addActionListener(this);
        back.addActionListener(this);

        save = new JLabel("You have successfully created a new Client");
        save.setBounds(130,360,300,50);
        save.setForeground(Color.white);
        jframe.add(save);
        save.setVisible(false);
        btn.addActionListener(this);
        jframe.setResizable(false);

    }
    public void actionPerformed(ActionEvent e ){
        if(e.getSource()==back){
            jframe.dispose();
            try {
                new H();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()== another){
            jframe.dispose();
            try {
                new Add_New_Client();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getSource()==btn){
            String f_n = fnt.getText();
            f_n=f_n.replace(" ","_");
            String l_n = lnt.getText();
            l_n=l_n.replace(" ","_");
            String c_s = cst.getText();
            c_s=c_s.replace(" ","_");
            String m_n = mbt.getText();
            String ci_n= nict.getText();
            String a_s=adrt.getText();
            String em= emt.getText();
            String table = f_n+"_"+l_n+"_"+c_s;

            try {

                String sql = "INSERT INTO client_list (first_name, last_name, Caste , Mobile_number, CNIC,Address, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);

                pst.setString(1,f_n);
                pst.setString(2,l_n);
                pst.setString(3,c_s);
                pst.setString(4,m_n);
                pst.setString(5,ci_n);
                pst.setString(6,a_s);
                pst.setString(7,em);
                pst.executeUpdate();
                conn.close();
                new Create_New_Client_Table(table);

                save.setVisible(true);

            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, ee);
            }

        }
        if(e.getSource()==dashbord)
        {
            jframe.dispose();
            try {
                new D();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        }
        if (e.getSource()==home){
            jframe.dispose();
            try {
                new H();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource()==logout){
            jframe.dispose();
            try {
                new Login_page();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==exit){
            System.exit(0);
        }
    }



}

/*
*
* Create table of client
*
* */

class Create_New_Client_Table {
    //     create new table


    // Connection details for MySQL database

    DBConnection con = new DBConnection();
    Connection conn = con.getConnection();

    String Tname = " ";
    Create_New_Client_Table(String n) throws SQLException {
        Tname = n;
        // SQL statement to create a table in MySQL database
        String createTableSQL = "CREATE TABLE IF NOT EXISTS "+Tname+" ("
                + "id INT(11) NOT NULL AUTO_INCREMENT,"
                + "Items VARCHAR(250),"

                +"Purchase_By VARCHAR(252),"
                +"Paid_Amount  INT(20),"
                +"Remaining_Amount INT(20),"
                + "Time  VARCHAR(50) ,"
                + "DATE VARCHAR(252),"

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



