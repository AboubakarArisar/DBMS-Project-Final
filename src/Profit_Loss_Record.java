import  javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Profit_Loss_Record implements ActionListener{
    DBConnection con = new DBConnection();
    Connection conn = con.getConnection();
    JFrame jFrame;
    String db;
    JButton clear;
    JButton back;
    Profit_Loss_Record() throws SQLException {

        // Query the database for the data
        String sql = "SELECT * FROM Profit_Loss_History " ;
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

        jFrame = new JFrame("Profit Loss Record");
        jFrame.setBounds(0,0,880,450);
        JPanel p = new JPanel();
        p.setBounds(10,0,850,300);

        // Create the JTable using the DefaultTableModel
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);

//        table.setRowHeight(3, 60);

        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);
        table.getColumnModel().getColumn(7).setPreferredWidth(150);
        table.getColumnModel().getColumn(8).setPreferredWidth(150);



//        You need to customize the renderer. To center the first column you can do:

        Font f1 = new Font("Times new Roman",Font.BOLD,18);
        table.setFont(f1);
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
        table.getColumnModel().getColumn(8).setCellRenderer( centerRenderer );
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.setPreferredScrollableViewportSize(new Dimension(800, 200));
        table.setEnabled(false);
        table.setBackground(new Color(178, 178, 248));


        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        // Add the JTable to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jFrame.setLayout(null);
        jFrame.add(p);
        // Add the JScrollPane to the JFrame
        p.add(scrollPane);

        clear = new JButton("Clear Record ");
        clear.setFocusable(false);
        clear.setBounds(230,300,200,50);
        clear.addActionListener(this);
        jFrame.add(clear);

        back = new JButton("Back");
        back.setFocusable(false);
        back.setBounds(500,300,200,50);
        back.addActionListener(this);
        jFrame.add(back);

        ImageIcon icon = new ImageIcon("Iconpro.jpg");
        jFrame.setIconImage(icon.getImage());
        Container c = jFrame.getContentPane();
        p.setOpaque(false);
        c.setBackground(new Color(0x0C0C6B));

        jFrame.setVisible(true);


    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==back){
            jFrame.dispose();
            try {
                new H();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==clear){
            jFrame.dispose();

            try {
                Statement stmt = conn.createStatement();
                String truncateQuery = "TRUNCATE Profit_Loss_History" ;
//            stmt.executeUpdate(resetQuery);
                stmt.executeUpdate(truncateQuery);
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                new Profit_Loss_Record();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}


