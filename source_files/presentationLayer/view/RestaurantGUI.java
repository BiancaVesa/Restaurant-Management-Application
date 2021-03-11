package presentationLayer.view;

import javax.swing.*;
import java.awt.*;

public class RestaurantGUI {
    public static JFrame frame;
    public static JPanel p;
    public static JLabel l;
    public static JButton b1, b2, b3;

    public RestaurantGUI() {
        frame = new JFrame("Restaurant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 150);

        p = new JPanel();

        l = new JLabel("Select : ");

        p.add(l);
        p.setLayout(new FlowLayout());

        b1 = new JButton("Administrator");
        b2 = new JButton("Waiter");
        b3 = new JButton("Open the kitchen");
        p.add(b1);
        p.add(b3);
        p.add(b2);

        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        frame.setContentPane(p);
        frame.setVisible(true);
    }
}
