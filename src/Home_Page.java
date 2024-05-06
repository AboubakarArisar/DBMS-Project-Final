
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

class H extends Menu implements ActionListener {
    String db;
    JButton Adcl; // add client
    JButton Mp; // mange fund
    JButton ml; // gernate report
    JButton Rdcl; // delete client

    JButton Profit_loss;
    JButton Profit_loss_record;
    H() throws SQLException {
        super();

        jframe.setTitle("Home Page");
        jframe.setBounds(0,0,850,800);
        ImageIcon icon = new ImageIcon("Iconpro.jpg");
        jframe.setIconImage(icon.getImage());
        Container c = jframe.getContentPane();
        jframe.setLayout(null);
        c.setBackground(new Color(255,255,255));

        Font f1 = new Font("Times New Roman",Font.BOLD,50);
        JLabel intro = new JLabel("Let's Digitalize");
        intro.setFont(f1);
        intro.setBounds(230,0,700,100);
        jframe.add(intro);

        JLabel choice = new JLabel("Hello , How are you , Choose what you want to do Today !");
        choice.setBounds(50,80,800,50);
        Font f2 = new Font("Arial",Font.PLAIN,26);
        choice.setFont(f2);
        jframe.add(choice);

        Adcl = new JButton("Add Client");
        Adcl.setFocusable(false);
        Adcl.setBounds(50,400,200,50);
        Adcl.addActionListener(this);
        jframe.add(Adcl);


        Profit_loss_record = new JButton("Profit Loss Record");
        Profit_loss_record.setFocusable(false);
        Profit_loss_record.setBounds(50,480,200,50);
        Profit_loss_record.addActionListener(this);
        jframe.add(Profit_loss_record);


        Rdcl = new JButton("Delete Client");
        Rdcl.setFocusable(false);
        Rdcl.setBounds(450,400,200,50);
        jframe.add(Rdcl);

        Rdcl.addActionListener(this);


        Mp = new JButton("Manage funds");
        Mp.setFocusable(false);
        Mp.setBounds(450,550,200,50);
        Mp.addActionListener(this);
        jframe.add(Mp);

        Profit_loss = new JButton("Profit Loss Calculator");
        Profit_loss.setFocusable(false);
        Profit_loss.setBounds(450,480,200,50);
        Profit_loss.addActionListener(this);
        jframe.add(Profit_loss);


        ml = new JButton("Generate Report");
        ml.setFocusable(false);
        ml.setBounds(50,550,200,50);
        jframe.add(ml);
        ml.addActionListener(this);


        ImageIcon bgIcon = new ImageIcon("best-practices-netsuite-integrations-white-paper (1).jpg");
        JLabel bg= new JLabel();
        bg.setIcon(bgIcon);
        bg.setBounds(0,0,850,900);
        jframe.add(bg);

        jframe.setResizable(false);

    }

    public void actionPerformed (ActionEvent e){

        if(e.getSource()==dashbord)
        {
            jframe.dispose();
            try {
                D d = new D();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
        if (e.getSource()==home){
            jframe.dispose();
            try {
                H h = new H();
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
        if(e.getSource()==Adcl){
            jframe.dispose();
            try {
                new Add_New_Client();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==Mp){
            jframe.dispose();
            try {
                new Manage_Funds();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==ml){

            jframe.dispose();
            try {
                new Gernate_Client_Report (db);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==Rdcl){
            jframe.dispose();
            try {
                new Delete_Client();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==Profit_loss){
            jframe.dispose();
            new Profit_Loss_Calculator(db);
        }
        if(e.getSource()==Profit_loss_record){
            jframe.dispose();
            try {
                new Profit_Loss_Record();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }}
class HomePage{
    public static void main (String arg []){
        try {
            H a = new H();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}