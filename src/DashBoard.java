import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


class D extends Menu implements ActionListener {


    private static final String TABLE_NAME = "Client_List";
DBConnection con = new DBConnection();
Connection conn = con.getConnection();
    D() throws SQLException {


        jframe.setTitle("Dashbord");
        jframe.setBounds(0,0,1300,750);
        ImageIcon icon = new ImageIcon("Iconpro.jpg");
        jframe.setIconImage(icon.getImage());
        JLabel logo = new JLabel();
        Container c = jframe.getContentPane();

        jframe.setLayout(null);


        Font f1 = new Font("  PGF Qualta Extra Bold",Font.BOLD,80);
        JLabel intro = new JLabel("Let's");
        intro.setFont(f1);
        intro.setBounds(280,70,1200,150);
        intro.setForeground(new Color(0x04044F));
        jframe.add(intro);

        JLabel intro2 = new JLabel("Digitalize");
        intro2.setFont(f1);
        intro2.setForeground(new Color(0x04044F));
        intro2.setBounds(220,170,1200,150);
        jframe.add(intro2);

        // user name  font and textfield for logo and una is username got through constructor
        Font font = new Font("Times New Roman",Font.BOLD,30);




        JLabel Ra = new JLabel("Client List");
        Ra.setFont(font);
        Ra.setForeground(new Color(0x04044F));
        Ra.setBounds(100, 350,500,50);
        jframe.add(Ra);

        // Connect to the database


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

        JPanel p = new JPanel();
        p.setBounds(20,400,620,300);

        // Create the JTable using the DefaultTableModel
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        table.setForeground(new Color(0x04044F));
        table.setBackground(new Color(121, 182, 241));
        Font f2 = new Font ("Times new Roman",Font.BOLD,18);
        table.setFont(f2);
//        table.setOpaque(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //       table.setOpaque(false);


        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);
        table.getColumnModel().getColumn(7).setPreferredWidth(150);


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(7).setCellRenderer( centerRenderer );


        table.setEnabled(false);
        table.setPreferredScrollableViewportSize(new Dimension(610, 200));

        // Add the JTable to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jframe.setLayout(null);
        jframe.add(p);
        p.setOpaque(false);
        p.add(scrollPane);
        ImageIcon icon2 = new ImageIcon("Untitled design (5).png");
        JLabel bg = new JLabel();
        bg.setBounds(0,0,icon2.getIconWidth(),icon2.getIconHeight());
        bg.setIcon(icon2);
        jframe.add(bg);

        jframe.setResizable(false);
        stmt.close();
        rs.close();

    }
    public void actionPerformed (ActionEvent e){
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
    }}

class Dashboard{
    public static void main (String arg []) throws SQLException {

        D d = new D();
    }
}
