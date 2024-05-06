import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.*;

class Menu implements  ActionListener{
    DBConnection con = new DBConnection();
    Connection conn = con.getConnection();
    JMenuItem dashbord ;
    JMenuItem home ;
    JMenuItem logout;
    JMenuItem exit;
    JFrame jframe;
    String dbn;
    Menu() throws SQLException {
        jframe = new JFrame("menu bar");
        jframe.setBounds(0, 0, 700, 400);
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        Container c = jframe.getContentPane();

        // creating a menu bar
        JMenuBar menu = new JMenuBar();
        JMenu fileMenu= new JMenu();
        ImageIcon Me = new ImageIcon("menu icon.png");
        fileMenu.setIcon(Me);
        menu.add(fileMenu);

//        adding image icons
        ImageIcon das = new ImageIcon("dashbord icon.png");
        ImageIcon hom = new ImageIcon("home icon.png");
        ImageIcon ex = new ImageIcon("exit icon.png");
        ImageIcon log = new ImageIcon("logout icon.png");

        dashbord = new JMenuItem("Dashboard");
        dashbord.setIcon(das);

        home = new JMenuItem("Home");
        home.setIcon(hom);
        logout = new JMenuItem("Logout");

        logout.setIcon(log);


        exit = new JMenuItem("exit");
        exit.setIcon(ex);
        dashbord.addActionListener(this);
        home.addActionListener(this);
        exit.addActionListener(this);
        logout.addActionListener(this);
        fileMenu.add(dashbord);
        fileMenu.add(home);
        fileMenu.add(exit);
        fileMenu.add(logout);
        jframe.setJMenuBar(menu);


    }
    public void actionPerformed (ActionEvent e){
        if(e.getSource()==dashbord)
        {
            jframe.dispose();
//             new DashBoard (dbn);
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

    public static void main(String[] args) throws SQLException {
        new Menu();
    }
}

