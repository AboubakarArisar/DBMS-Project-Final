
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Delete_Client implements ActionListener {
DBConnection con = new DBConnection();
Connection conn = con.getConnection();
    JComboBox cass; //caste
    JComboBox name;//name
    JComboBox llname;// last name
    JLabel del; // message
    JButton btn;
    JButton back;
    String db =" ";

    JComboBox cnic;
    JFrame jframe;
    Delete_Client() throws SQLException {

        // set the  jframe values
        jframe = new JFrame("Delete Client");
        jframe.setBounds (20,30,500,350);
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        Container c = jframe.getContentPane();
        c.setBackground(new Color(153,153,255));
        c.setLayout(null);
        Font f1 = new Font("Times new Roman",Font.BOLD,16);

//        set the fram icon
        ImageIcon icon = new ImageIcon("Iconpro.jpg");
        jframe.setIconImage(icon.getImage());

        JLabel fname = new JLabel("First_Name");
        fname.setBounds(100,20,100,50);
        jframe.add(fname);


        JLabel lname = new JLabel("Last_Name");
        lname.setBounds(100,50,100,50);
        jframe.add(lname);

        JLabel cast = new JLabel("Caste");
        cast.setBounds(100,90,100,50);
        jframe.add(cast);

        JLabel nic = new JLabel("CNIC");
        nic.setBounds(100,130,100,50);
        jframe.add(nic);


        int a=0;// reading the id number to fetch the name , last name and cine and cast of clients
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

            rs.close();
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int b =0;

        // fetching the first name of clients and displaying the firstname in combo box
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


        ///  fetching the last name of clients and displaying the lastname in combo box
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

        //fetching the caste of clients and displaying the caste in combo box
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


        String lcsn[]=new String[a];
//        fetching the cnic number of clients and displaying the cnic number  in combo box
        try{

            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from client_list");
            b=0;

            while (resultSet.next()) {

                lcsn[b]= (resultSet.getString("CNIC"));
                b++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        // displaying  cnic  through combo box
        cnic= new JComboBox(lcsn);
        cnic.setBounds(280,140,100,20);
        jframe.add(cnic);

        // displaying the cate through combo box
        cass= new JComboBox(cas);
        cass.setBounds(280,100,100,20);
        jframe.add(cass);


        // displaying the name through combo box
        name = new JComboBox(g);
        name.setBounds(280,30,100,20);
        jframe.add(name);

        // displaying the last name through combo box
        llname = new JComboBox(lln);
        llname.setBounds(280,60,100,20);
        jframe.add(llname);

        btn = new JButton("Delete Client");
        btn.setBounds(130,200,130,30);
        btn.setFocusable(false);
        jframe.add(btn);

        back = new JButton("Back");
        back.setBounds(270,200,100,30);
        back.setFocusable(false);
        jframe.add(back);

        del = new JLabel("You have successfully deleted this client ");
        del.setBounds(100,240,400,50);
        del.setFont(f1);
        jframe.add(del);
        del.setForeground(new Color (243,55,75));
        del.setVisible(false);

        back.addActionListener(this);
        btn.addActionListener(this);

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
        if(e.getSource()==btn){
            String f_n = (String) name.getSelectedItem();
            String l_n = (String) llname.getSelectedItem();
            String c_s = (String) cass.getSelectedItem();
            String table = f_n+"_"+l_n+"_"+c_s;

            String row = (String) cnic.getSelectedItem();
            try {
                new DeleteRow(row);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                new DeleteTable(table);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            del.setVisible(true);


        }

    }
    class DeleteRow{
        DBConnection con = new DBConnection();
        Connection conn = con.getConnection();
        DeleteRow(String row) throws SQLException {
            String tableName = "client_list";
            //int rowId = 1; // identify the row to delete by its primary key value

            try  {
                String sql = "DELETE FROM " + tableName + " WHERE CNIC = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, row);
                    int numRowsDeleted = stmt.executeUpdate();

                    if (numRowsDeleted == 1) {
                        System.out.println("Row with ID " + row + " has been deleted.");

                    } else {
                        System.out.println("No rows were deleted.");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error deleting row from table " + tableName + ": " + e.getMessage());
            }
        }


    }
    public static void main(String[] args) throws SQLException {
        new Delete_Client();
    }
}
class DeleteTable {
    DBConnection con = new DBConnection();
    Connection conn = con.getConnection();

    DeleteTable(String table) throws SQLException {
        String tableName = table;

        try {
            String sql = "DROP TABLE " + tableName;
            try (
                    Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql);
                System.out.println("Table " + tableName + " has been deleted.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting table " + tableName + ": " + e.getMessage());
        }
    }
}
