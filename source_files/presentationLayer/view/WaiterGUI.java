package presentationLayer.view;

import javax.swing.*;
import java.awt.*;

public class WaiterGUI {
    public static JFrame frame;
    public static JPanel panel1, panel2, panel3, panel4, panel5, p;
    public static JLabel l1;
    public static JTextField tf1, tf2, tf3, tf4;
    public static JButton b1, b2, b3, b4, b5;


    public WaiterGUI() {
        frame = new JFrame("Waiter");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(600, 600);

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();

        l1 = new JLabel("Choose action: ");

        panel1.add(l1);
        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());
        panel3.setLayout(new FlowLayout());
        panel4.setLayout(new FlowLayout());
        panel5.setLayout(new FlowLayout());

        b1 = new JButton("Add order");
        tf1 = new JTextField("Type the date...");
        tf2 = new JTextField("Type the menu items (separated by , )...");
        b2 = new JButton ("See menu");
        b3 = new JButton("Compute price for order");
        tf3 = new JTextField("Type the ID...");
        b4 = new JButton("Generate bill");
        tf4 = new JTextField("Type the order ID...");
        b5 = new JButton("Show all orders");

        panel2.add(b1);
        panel2.add(tf1);
        panel2.add(tf2);
        panel2.add(b2);
        panel3.add(b3);
        panel3.add(tf3);
        panel4.add(b4);
        panel4.add(tf4);
        panel5.add(b5);

        p = new JPanel();
        p.add(panel1);
        p.add(panel2);
        p.add(panel3);
        p.add(panel4);
        p.add(panel5);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        frame.setContentPane(p);
        frame.setVisible(true);
    }
}
