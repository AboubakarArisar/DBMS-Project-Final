import  javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
public class Profit_Loss_Calculator implements ActionListener {
    JTextArea screen;
    JTextField m_invest;
    JTextField m_sale;
    JTextField date;

    JTextField additional ;

    JButton re;// re calculate
    JButton save;
    JButton cal; // calculate
    JButton back;
    JFrame jfram;
    String db;
    Profit_Loss_Calculator(String database){
        db=database;
        jfram= new JFrame("Profit loss Calculator");
        jfram.setVisible(true);
        jfram.setDefaultCloseOperation(jfram.EXIT_ON_CLOSE);
        jfram.setBounds(30,30,500,600);

        ImageIcon icon = new ImageIcon("Iconpro.jpg");
        jfram.setIconImage(icon.getImage());
        jfram.setLayout(null);
        Font font = new Font("Times new Roman ",Font.BOLD,18);

        screen = new JTextArea();
        screen.setBounds(40,30,400,150);
        jfram.add(screen);
        screen.setFont(font);
        screen.setEditable(false);

        Font f1 = new Font("Times new Roman ",Font.BOLD,18);
        JLabel invest = new JLabel("Monthly Investment");
        invest.setForeground(Color.white);
        invest.setBounds(50,210,300,50);

        invest.setFont(f1);
        jfram.add(invest);

        m_invest = new JTextField();
        m_invest.setBounds(300,225,100,20);
        m_invest.setText("0");
        jfram.add(m_invest);


        JLabel sale = new JLabel("Monthly Sales");
        sale.setBounds(50,270,300,50);
        sale.setForeground(Color.white);
        sale.setFont(f1);
        jfram.add(sale);

        m_sale = new JTextField();
        m_sale.setBounds(300,285,100,20);
        m_sale.setText("0");
        jfram.add(m_sale);

        JLabel da = new JLabel("Calculating Date");
        da.setBounds(50,320,300,50);
        da.setForeground(Color.white);
        da.setFont(f1);
        jfram.add(da);

        date = new JTextField();
        date.setBounds(300,335,100,20);
        date.setText("dd/mm/yyyy");
        jfram.add(date);


        JLabel add = new JLabel("Additional details ");
        add.setBounds(50,380,300,50);
        add.setForeground(Color.white);
        add.setFont(f1);
        jfram.add(add);

        additional = new JTextField();
        additional.setBounds(300,395,100,20);
        additional.setText("nothing");
        jfram.add(additional);

        re = new JButton("Re-Calculate");
        re.setBounds(50,440,150,30);
        re.setFocusable(false);
        jfram.add(re);


        cal = new JButton("Calculate");
        cal.setBounds(250,440,150,30);
        jfram.add(cal);
        cal.setFocusable(false);

        save = new JButton("Save");
        save.setBounds(50,480,150,30);
        jfram.add(save);
        save.setFocusable(false);


        back = new JButton("Back");
        back.setBounds(250,480,150,30);
        jfram.add(back);
        back.setFocusable(false);


        jfram.getContentPane().setBackground(new Color (19, 56, 82));

        back.addActionListener(this);
        save.addActionListener(this);
        re.addActionListener(this);
        cal.addActionListener(this);

        jfram.setResizable(false);
    }
    public void actionPerformed(ActionEvent e){

        double inv = Double.parseDouble(m_invest.getText());
        double sal =Double.parseDouble(m_sale.getText());
        String inves = String.valueOf(inv);
        String saal = String.valueOf(sal);
        String daate =date.getText();
        String info = additional.getText();
        String T_p =" null ";
        String P_p= "null";
        String T_l = "null";
        String P_l = "null";

        if(e.getSource()==re){
            jfram.dispose();

            new Profit_Loss_Calculator(db);
        }
        if(e.getSource()==back){
            System.out.println("back please");
            jfram.dispose();
            try {
                new H ();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==cal){
            double calc =  sal - inv;
            double per = (calc/sal )*100;

            DecimalFormat df = new DecimalFormat("#.###");
            String perc = " ";

            System.out.println(calc/inv);
            String calcu =" ";


            if (calc>0){
                perc = df.format(per);
                calcu = String.valueOf(calc);
                screen.setText("\n Total Profit = \t"+calcu+"\n Percentage profit=  "+perc+"%");
                T_p=calcu;
                P_p=perc;
            }
            else {
                per = per*(-1);
                calc = calc*(-1);
                perc = df.format(per);
                calcu = String.valueOf(calc);
                T_l= calcu;
                P_l = perc;
                screen.setText("\n Total Loss = \t"+calcu+"\n Percentage Loss=  "+perc+"%");
                System.out.println(" t_l "+T_l+"P _ L "+P_l);

            }


        }
        if(e.getSource()==save){
            try {   // saving the values in user database profit table
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db, "root", "root");
                String sql = "INSERT INTO Profit_Loss_History ( Monthly_Investment ,Monthly_Sales, Calculating_Date, Additional_Details,Total_Profit, Percentage_Profit,Total_Loss,Percentage_Loss) VALUES ( ?,?,?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                double calc =  sal - inv;
                double per = (calc/sal )*100;
                DecimalFormat df = new DecimalFormat("#.###");
                String perc = " ";
                System.out.println(calc/inv);
                String calcu =" ";


                if (calc>0){
                    perc = df.format(per);
                    calcu = String.valueOf(calc);
                    screen.setText("\n Total Profit = \t"+calcu+"\n Percentage profit=  "+perc+"%");
                    T_p=calcu;
                    P_p=perc+"%";
                }
                else {
                    per = per*(-1);
                    calc = calc*(-1);
                    perc = df.format(per);
                    calcu = String.valueOf(calc);
                    T_l= calcu;
                    P_l = perc+"%";
                    screen.setText("\n Total Loss = \t"+calcu+"\n Percentage Loss=  "+perc+"%");
                    System.out.println(" t_l "+T_l+"P _ L "+P_l);

                }

                pst.setString(1,inves);
                pst.setString(2, saal);
                pst.setString(3,daate);
                pst.setString(4,info);

                pst.setString(5,T_p);
                pst.setString(6,P_p);
                pst.setString(7,T_l);
                pst.setString(8,P_l);
                pst.executeUpdate();
                conn.close();
                System.out.println(" t_l "+T_l+"P _ L "+P_l);
                JOptionPane.showMessageDialog(null, "your date is stored Successfully ");

            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, ee);
            }
        }
    }
}
