package presentationLayer.view;

import javax.swing.*;
import java.awt.*;

public class AdministratorGUI {
    public static JFrame frame;
    public static JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7, p;
    public static JLabel l, l1, l2, l3;
    public static JTextField tf1;
    public static JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;

    public AdministratorGUI() {
        frame = new JFrame("Administrator");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(700, 700);

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        panel7 = new JPanel();

        l = new JLabel("Choose action: ");

        panel1.add(l);
        panel1.setLayout(new FlowLayout());

        l1 = new JLabel("Add menu item");
        b1 = new JButton("Base item");
        b2 = new JButton("Composite item");
        b3 = new JButton("Delete menu item");
        tf1 = new JTextField("Type the name...");
        l2 = new JLabel("Edit base item");
        b4 = new JButton("Edit name");
        b5 = new JButton("Edit price");
        l3 = new JLabel("Edit composite item");
        b6 = new JButton("Edit name");
        b7 = new JButton("Add menu item");
        b8 = new JButton("Delete menu item");
        b9 = new JButton("Show all menu items");

        panel2.add(l1);
        panel2.add(b1);
        panel2.add(b2);
        panel3.add(b3);
        panel3.add(tf1);
        panel4.add(l2);
        panel4.add(b4);
        panel4.add(b5);
        panel5.add(l3);
        panel5.add(b6);
        panel5.add(b7);
        panel5.add(b8);
        panel6.add(b9);

        p = new JPanel();
        p.add(panel1);
        p.add(panel2);
        p.add(panel3);
        p.add(panel4);
        p.add(panel5);
        p.add(panel6);
        p.add(panel7);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        frame.setContentPane(p);
        frame.setVisible(true);
    }
}
