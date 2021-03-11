package presentationLayer.view;

import businessLayer.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class ChefGUI implements Observer {
    public static JFrame frame;
    public static JPanel panel;
    public static JLabel l1;
    public static DefaultTableModel model;
    public static JTable table;
    private static ArrayList<Order> orders = new ArrayList<>();

    public ChefGUI() {
        frame = new JFrame("Chef");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(600, 150);

        panel = new JPanel();

        panel.setLayout(new FlowLayout());

        l1 = new JLabel("See orders to prepare");
        panel.add(l1);

        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Date");
        model.addColumn("Menu Items");
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        Vector<Object> data1 = new Vector<Object>();
        data1.add("ID");
        data1.add("Date");
        data1.add("Menu Items");
        model.addRow(data1);

        if (!orders.isEmpty()) {
            for (Order order : orders) {
                Vector<Object> data = new Vector<Object>();
                data.add(order.getOrderID());
                data.add(order.getDate());
                data.add(order.showCompositeProducts());
                model.addRow(data);
            }
        }

        panel.add(table);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    @Override
    public void cook(Order order) {
            this.orders.add(order);
            Vector<Object> data = new Vector<Object>();
            data.add(order.getOrderID());
            data.add(order.getDate());
            data.add(order.showCompositeProducts());
            model.addRow(data);

    }
}


