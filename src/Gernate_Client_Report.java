import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Gernate_Client_Report implements ActionListener {
    JComboBox cass; // cast
    JComboBox name;
    JComboBox llname; //last name

    JButton gernate_report; // gernate report
    JButton back;
    String db =" "; // database
    JFrame jframe;

    JComboBox cnic;
    Gernate_Client_Report(String n) throws SQLException {
        db=n;    // setup the fram as well font and fram icon
        jframe = new JFrame("Gernate  Report");
        jframe.setBounds (20,30,500,300);
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        Container c = jframe.getContentPane();
        c.setBackground(new Color(153,153,255));
        c.setLayout(null);
        Font f1 = new Font("Times new Roman",Font.BOLD,16);
        ImageIcon icon = new ImageIcon("Iconpro.jpg");
        jframe.setIconImage(icon.getImage());


        // first name label
        JLabel fname = new JLabel("First_Name");
        fname.setBounds(100,20,100,50);
        jframe.add(fname);


        JLabel lname = new JLabel("Last_Name");
        lname.setBounds(100,50,100,50);
        jframe.add(lname);


        JLabel cast = new JLabel("Caste");
        cast.setBounds(100,90,100,50);
        jframe.add(cast);

        // id  to use as array limit
        int a=0;
        DBConnection con = new DBConnection();
        Connection conn = con.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT id FROM client_list";
            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()) {
                int id = rs.getInt("id");
                a=id;
            }

            // Print total
            System.out.println("Total debit: " + a);


        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // first name displaying in   combo box
        int b =0;
        String g []= new String [a];
        try{

            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from client_list");


            while (resultSet.next()) {
                g[b]= (resultSet.getString("first_name"));
                b++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        ///   last name  show  in combo box
        String lln[]=new String[a];

        try{

            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from client_list");
            b=0;

            while (resultSet.next()) {

                lln[b]= (resultSet.getString("last_name"));
                b++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        //caste show in combo box
        String cas[]=new String [a];
        try{

            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from client_list");
            b=0;

            while (resultSet.next()) {

                cas[b]= (resultSet.getString("Caste"));
                System.out.println(cas[b]);
                b++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }




        // caste
        cass= new JComboBox(cas);
        cass.setBounds(280,100,100,20);
        jframe.add(cass);


        // first name
        name = new JComboBox(g);
        name.setBounds(280,30,100,20);
        jframe.add(name);

        // last name
        llname = new JComboBox(lln);
        llname.setBounds(280,60,100,20);
        jframe.add(llname);

        gernate_report = new JButton("Generate Report");
        gernate_report.setBounds(130,200,130,30);
        gernate_report.setFocusable(false);
        jframe.add(gernate_report);

        back = new JButton("Back");
        back.setBounds(270,200,100,30);
        back.setFocusable(false);
        jframe.add(back);



        back.addActionListener(this);
        gernate_report.addActionListener(this);

        jframe.setResizable(false);

    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==back){
            jframe.dispose();
            try {
                new H();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==gernate_report){
            // passing the first name , last name  and caste to gernate report class
            String f_n = (String) name.getSelectedItem();
            String l_n = (String) llname.getSelectedItem();
            String c_s = (String) cass.getSelectedItem();
            String table = f_n+"_"+l_n+"_"+c_s;
            jframe.dispose();
            try {
                new Gernate_Report(table,f_n);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
/*
*
*
*
*
* Gernate report into table form and show total paid amount , remaning ,difference ammmount
*
*
*
*
* */

class Gernate_Report implements ActionListener {


    JFrame jFrame;

    JButton clear;

    DBConnection con = new DBConnection();
    Connection conn = con.getConnection();
    String TABLE_NAME = " ";

    JButton back;
    String dbb;
    String First_name;

    public Gernate_Report(String t, String first_name) throws SQLException {
        First_name = first_name;


        TABLE_NAME = t;


        // Query the database for the data
        String sql = "SELECT * FROM " + TABLE_NAME;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create the DefaultTableModel to hold the data
        DefaultTableModel model = new DefaultTableModel();

        // Add the columns to the model
        try {
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                model.addColumn(rs.getMetaData().getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add the rows to the model
        try {
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jFrame = new JFrame("Generate Report");
        jFrame.setBounds(50, 30, 500, 580);
        JPanel p = new JPanel();
        p.setBounds(0, 0, 500, 300);

        // Create the JTable using the DefaultTableModel
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);


        //customize the hight of  rows of table
        table.setRowHeight(20);

        // coustomize the width of columns of table
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);

        // coustomize the text of table in center of column
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.setPreferredScrollableViewportSize(new Dimension(400, 200));
        table.setBackground(new Color(99, 159, 225));

        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        // Add the JTable to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jFrame.setLayout(null);
        jFrame.add(p);
        p.add(scrollPane);
        // Set the size and visibility of the JFrame
        jFrame.setVisible(true);

        // remaining amount calling method
        String D = Remaining_Amount();
        p.setBackground(new Color(19, 78, 124));

        // label and font for remaining amount
        Font f1 = new Font("Arial", Font.BOLD, 20);
        JLabel debit = new JLabel(" Total Remaining  ");
        debit.setFont(f1);
        debit.setForeground(Color.white);
        debit.setBounds(100, 300, 200, 50);
        jFrame.add(debit);

        // showing the Remaining_Amount value  frame
        JLabel debt = new JLabel("= " + D);
        debt.setForeground(Color.white);
        debt.setBounds(300, 300, 200, 50);
        debt.setFont(f1);
        jFrame.add(debt);

        // showing the total paid text in fram
        JLabel credit = new JLabel(" Total Paid ");
        credit.setBounds(100, 350, 200, 50);
        credit.setFont(f1);
        credit.setForeground(Color.white);
        jFrame.add(credit);


        // showing the total paid amount in frame
        String c = Paid_Amount();
        JLabel cd = new JLabel("= " + c);
        cd.setBounds(300, 350, 200, 50);
        cd.setFont(f1);
        cd.setForeground(Color.white);
        jFrame.add(cd);

        //setting the frame
        ImageIcon icon = new ImageIcon("Iconpro.jpg");
        jFrame.setIconImage(icon.getImage());
        Container cc = jFrame.getContentPane();
        cc.setBackground(new Color(9, 22, 65));

        // calculating the difference amount and displaying it in frame
        Font f2 = new Font("Arial", Font.BOLD, 16);
        int difference = Integer.parseInt(c) - Integer.parseInt(D);
        JLabel di = new JLabel("");
        di.setBounds(50, 450, 400, 50);
        jFrame.add(di);
        di.setForeground(Color.white);
        di.setFont(f2);

        if (difference < 0) {
            difference = difference * (-1);
            String T_dif = String.valueOf(difference);
            di.setText(first_name + " has to pay Rs " + T_dif + " Rupees to you.");

        } else if (difference > 0) {
            String T_dif = String.valueOf(difference);
            di.setText("You  have to pay Rs " + T_dif + "Rupees to him.");

        } else {
            di.setText("nothing Remaining");
        }

        JLabel td = new JLabel(" Total Difference ");
        td.setBounds(100, 400, 200, 50);
        td.setFont(f1);
        td.setForeground(Color.white);
        jFrame.add(td);
        String T_dif = String.valueOf(difference);
        JLabel tdif = new JLabel("= " + T_dif);
        tdif.setBounds(300, 400, 200, 50);
        tdif.setFont(f1);
        tdif.setForeground(Color.white);
        jFrame.add(tdif);

        back = new JButton("Back");
        back.setBounds(240, 500, 100, 30);
        back.setFocusable(false);
        Container ccd = jFrame.getContentPane();
        ccd.add(back);


        clear = new JButton("Clear Records ");
        clear.setBounds(100, 500, 120, 30);
        clear.setFocusable(false);
        ccd.add(clear);
        clear.addActionListener(this);

        back.addActionListener(this);

        jFrame.setResizable(false);

    }

    String Remaining_Amount() {
        String abc = " ";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT Remaining_Amount FROM " + TABLE_NAME;
            rs = stmt.executeQuery(sql);

            // Calculate sum
            int sum = 0;
            while (rs.next()) {
                int debit = rs.getInt("Remaining_Amount");
                sum += debit;
            }

            abc = String.valueOf(sum);

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            // Close resources properly
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return abc;
    }

    String Paid_Amount() {
        String abc = " ";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT Paid_Amount FROM " + TABLE_NAME;
            rs = stmt.executeQuery(sql);

            // Calculate sum
            int sum = 0;
            while (rs.next()) {
                int credit = rs.getInt("Paid_Amount");
                sum += credit;
            }

            abc = String.valueOf(sum);

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            // Close resources properly
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return abc;
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            jFrame.dispose();
            try {
                new H();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == clear) {
            jFrame.dispose();
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                String truncateQuery = "TRUNCATE " + TABLE_NAME;
                stmt.executeUpdate(truncateQuery);
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle the error as per your application's requirements
            } finally {
                // Close the statement
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            try {
                // Attempt to create a new instance of Gernate_Report
                new Gernate_Report(TABLE_NAME, First_name);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}