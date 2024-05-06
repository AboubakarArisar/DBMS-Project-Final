import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Manage_Funds extends Menu implements ActionListener {

    DBConnection con = new DBConnection();
    Connection conn = con.getConnection();

    JTextField mbt;  // items
    JTextField nict;// paid amount
    JTextField emt; // Remaining amount
    JButton btn;    // save button
    JComboBox name;
    JComboBox llname; // last name
    JLabel save;
    JButton back;
    JButton re; // enter other

    String db;
    String date;

    JTextField tt; // time
    JTextField datt;// date
    String time;
    JTextField wpp; // purchase by
    JComboBox cass;

    public Manage_Funds() throws SQLException {

        jframe.setTitle("Manage funds");
        jframe.setBounds(50, 50, 500, 580);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(null);
        jframe.setVisible(true);
        Container c = jframe.getContentPane();
        c.setBackground(new Color(20, 3, 66));

        JLabel fname = new JLabel("First_Name");
        fname.setBounds(100, 20, 100, 50);
        fname.setForeground(Color.white);
        jframe.add(fname);

        ImageIcon icon = new ImageIcon("Iconpro.jpg");
        jframe.setIconImage(icon.getImage());

        JLabel lname = new JLabel("Last_Name");
        lname.setForeground(Color.white);
        lname.setBounds(100, 50, 100, 50);
        jframe.add(lname);

        JLabel cast = new JLabel("Caste");
        cast.setForeground(Color.white);
        cast.setBounds(100, 90, 100, 50);
        jframe.add(cast);

        JLabel mbn = new JLabel("Items name");
        mbn.setForeground(Color.white);
        mbn.setBounds(100, 130, 150, 50);
        jframe.add(mbn);

        mbt = new JTextField();
        mbt.setBounds(280, 140, 100, 20);
        jframe.add(mbt);

        JLabel nic = new JLabel("Paid Amount");
        nic.setForeground(Color.white);
        nic.setBounds(100, 170, 150, 50);
        jframe.add(nic);

        nict = new JTextField();
        nict.setBounds(280, 180, 100, 20);
        jframe.add(nict);

        JLabel em = new JLabel("Remaining Amount ");
        em.setBounds(100, 210, 200, 50);
        em.setForeground(Color.white);
        jframe.add(em);

        emt = new JTextField();
        emt.setBounds(280, 220, 100, 20);
        jframe.add(emt);

        JLabel dat = new JLabel("Date");
        dat.setBounds(100, 250, 100, 50);
        dat.setForeground(Color.white);
        jframe.add(dat);

        datt = new JTextField();
        datt.setBounds(280, 260, 100, 20);
        jframe.add(datt);

        JLabel tim = new JLabel("Time ");
        tim.setForeground(Color.white);
        tim.setBounds(100, 300, 100, 50);
        jframe.add(tim);

        tt = new JTextField();
        tt.setBounds(280, 310, 100, 20);
        jframe.add(tt);

        JLabel wp = new JLabel("Purchase by");
        wp.setForeground(Color.white);
        wp.setBounds(100, 350, 200, 50);
        jframe.add(wp);

        wpp = new JTextField();
        wpp.setText("Himself / HerSelf");
        wpp.setBounds(280, 360, 100, 20);
        jframe.add(wpp);

        int a = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT id FROM client_list";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                a = id;
            }

            System.out.println("Total debit: " + a);

            rs.close();
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        String[] g = new String[a];
        String[] lln = new String[a];
        String[] cas = new String[a];

        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from client_list");

            int b = 0;
            while (resultSet.next()) {
                g[b] = resultSet.getString("first_name");
                lln[b] = resultSet.getString("last_name");
                cas[b] = resultSet.getString("Caste");
                b++;
            }

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        cass = new JComboBox(cas);
        cass.setBounds(280, 100, 100, 20);
        jframe.add(cass);

        name = new JComboBox(g);
        name.setBounds(280, 30, 100, 20);
        jframe.add(name);

        llname = new JComboBox(lln);
        llname.setBounds(280, 60, 100, 20);
        jframe.add(llname);

        btn = new JButton("Save");
        btn.setBounds(190, 400, 100, 20);
        btn.setFocusable(false);
        jframe.add(btn);

        re = new JButton("Re-Enter");
        re.setBounds(320, 400, 100, 20);
        re.setFocusable(false);
        jframe.add(re);

        back = new JButton("Back");
        back.setBounds(70, 400, 100, 20);
        back.setFocusable(false);
        jframe.add(back);

        re.addActionListener(this);
        back.addActionListener(this);
        save = new JLabel("You have successfully entered the Details ");
        save.setForeground(Color.white);
        save.setBounds(130, 430, 300, 50);
        jframe.add(save);
        save.setVisible(false);
        btn.addActionListener(this);
        jframe.setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btn) {
            String f_n = (String) name.getSelectedItem();
            String l_n = (String) llname.getSelectedItem();
            String c_s = (String) cass.getSelectedItem();
            String m_n = mbt.getText();
            String df = nict.getText();
            int ci_n = Integer.parseInt(df);
            String gh = emt.getText();
            int a_s = Integer.parseInt(gh);
            String table = f_n + "_" + l_n + "_" + c_s;
            date = datt.getText();
            time = tt.getText();
            String wpt = wpp.getText();

            try {
                String sql = "INSERT INTO " + table + " ( Items ,Purchase_by, Paid_Amount, Remaining_Amount, Time, Date) VALUES ( ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, m_n);
                pst.setString(2, wpt);
                pst.setInt(3, ci_n);
                pst.setInt(4, a_s);
                pst.setString(5, time);
                pst.setString(6, date);
                pst.executeUpdate();
                pst.close();

                save.setVisible(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == dashbord) {
            jframe.dispose();
        }

        if (e.getSource() == home) {
            jframe.dispose();
            try {
                new H();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == logout) {
            jframe.dispose();
            try {
                new Login_page();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == exit) {
            System.exit(0);
        }

        if (e.getSource() == re) {
            jframe.dispose();
            try {
                new Manage_Funds();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == back) {
            jframe.dispose();
            try {
                new H();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Manage_Funds();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
