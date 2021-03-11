package presentationLayer.controller;

import dataLayer.DataValidation;
import businessLayer.*;
import dataLayer.FileWriter;
import dataLayer.RestaurantSerializator;
import presentationLayer.view.AdministratorGUI;
import presentationLayer.view.ChefGUI;
import presentationLayer.view.RestaurantGUI;
import presentationLayer.view.WaiterGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class RestaurantGUIController extends JPanel {
    public static void main(String[] args) {
        Restaurant restaurant = RestaurantSerializator.deserialize(args[0]);
        RestaurantGUI restaurantGUI = new RestaurantGUI();

        RestaurantGUI.b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdministratorGUI administator = new AdministratorGUI();
                AdministratorGUI.b1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        //create base menu item
                        JTextField n = new JTextField();
                        JTextField p = new JTextField();
                        Object[] message = {
                                "Name:", n,
                                "Price:", p
                        };

                        int option = JOptionPane.showConfirmDialog(null, message, "Add base product", JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            String name = n.getText();
                            String price = p.getText();
                            if (DataValidation.isValidName(name) && DataValidation.isValidPrice(Float.parseFloat(price))) {
                                BaseProduct product = new BaseProduct(name, Float.parseFloat(price));
                                restaurant.createMenuItem(product);
                            } else JOptionPane.showMessageDialog(null, "Wrong input! Try again");
                        }

                    }
                });

                AdministratorGUI.b2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        //create composite menu item
                        JTextField n = new JTextField();
                        JTextField p = new JTextField();
                        Object[] message = {
                                "Name:", n,
                                "Base Products (separated by , ):", p
                        };

                        int option = JOptionPane.showConfirmDialog(null, message, "Add composite product", JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            String name = n.getText();
                            String products = p.getText();
                            String[] tokens = products.split(", ");
                            if (DataValidation.isValidName(name)) {
                                ArrayList<MenuItem> bProducts = new ArrayList<>();
                                for (String bProduct : tokens) {
                                    if (DataValidation.isValidName(bProduct)) {
                                        MenuItem item = restaurant.findMenuItem(bProduct);
                                        if (item != null) {
                                            bProducts.add(item);
                                        } else
                                            JOptionPane.showMessageDialog(null, "There is no product with the name " + bProduct);
                                    } else JOptionPane.showMessageDialog(null, "Wrong input! Try again");

                                }
                                if (!bProducts.isEmpty()) {
                                    restaurant.createMenuItem(new CompositeProduct(name, bProducts));
                                }
                            } else JOptionPane.showMessageDialog(null, "Wrong input! Try again");
                        }

                    }
                });

                AdministratorGUI.b3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        //delete menu item
                        String name = AdministratorGUI.tf1.getText();
                        if (DataValidation.isValidName(name)) {
                            MenuItem menuItem = restaurant.findMenuItem(name);
                             if (menuItem != null)
                                restaurant.deleteMenuItem(menuItem);
                             else JOptionPane.showMessageDialog(null, "Couldn't find a menu item with this name");
                        } else JOptionPane.showMessageDialog(null, "Wrong input! Try again");

                    }
                });

                AdministratorGUI.b4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        //edit base name
                        JTextField n1 = new JTextField();
                        JTextField n2 = new JTextField();
                        Object[] message = {
                                "Old name:", n1,
                                "New name:", n2
                        };

                        int option = JOptionPane.showConfirmDialog(null, message, "Edit base item name", JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            String oldName = n1.getText();
                            String newName = n2.getText();
                            if (DataValidation.isValidName(oldName) && DataValidation.isValidName(newName)) {
                                MenuItem item = restaurant.findMenuItem(oldName);
                                if (item != null)
                                    restaurant.editMenuItemName(item, newName);
                                else JOptionPane.showMessageDialog(null, "Couldn't find a product with this name");
                            } else JOptionPane.showMessageDialog(null, "Wrong input! Try again");
                        }

                    }
                });

                AdministratorGUI.b5.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        //edit base item price
                        JTextField n = new JTextField();
                        JTextField p = new JTextField();
                        Object[] message = {
                                "Product name:", n,
                                "New price:", p
                        };

                        int option = JOptionPane.showConfirmDialog(null, message, "Edit base product price", JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            String name = n.getText();
                            String price = p.getText();
                            if (DataValidation.isValidName(name) && DataValidation.isValidPrice(Float.parseFloat(price))) {
                                MenuItem item = restaurant.findMenuItem(name);
                                if (item != null)
                                    restaurant.editBaseItemPrice(item, Float.parseFloat(price));
                                else JOptionPane.showMessageDialog(null, "Couldn't find a product with this name");
                            } else JOptionPane.showMessageDialog(null, "Wrong input! Try again");
                        }

                    }
                });

                AdministratorGUI.b6.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        //edit composite item name
                        JTextField n1 = new JTextField();
                        JTextField n2 = new JTextField();
                        Object[] message = {
                                "Old name:", n1,
                                "New name:", n2
                        };

                        int option = JOptionPane.showConfirmDialog(null, message, "Edit composite product name", JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            String oldName = n1.getText();
                            String newName = n2.getText();
                            if (DataValidation.isValidName(oldName) && DataValidation.isValidName(newName)) {
                                MenuItem item = restaurant.findMenuItem(oldName);
                                if (item != null)
                                    restaurant.editMenuItemName(item, newName);
                                else JOptionPane.showMessageDialog(null, "Couldn't find a product with this name");
                            } else JOptionPane.showMessageDialog(null, "Wrong input! Try again");
                        }

                    }
                });

                AdministratorGUI.b7.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        //add menu item to composite product
                        JTextField n1 = new JTextField();
                        JTextField n2 = new JTextField();
                        Object[] message = {
                                "Composite product name:", n1,
                                "Menu item name:", n2
                        };

                        int option = JOptionPane.showConfirmDialog(null, message, "Add menu item to composite product", JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            String cName = n1.getText();
                            String bName = n2.getText();
                            if (DataValidation.isValidName(cName) && DataValidation.isValidName(bName)) {
                                MenuItem cItem = restaurant.findMenuItem(cName);
                                MenuItem bItem = restaurant.findMenuItem(bName);
                                if (cItem != null && bItem != null)
                                    restaurant.addBaseToComItem(cItem, bItem);
                                else JOptionPane.showMessageDialog(null, "Couldn't find a product with this name");
                            } else JOptionPane.showMessageDialog(null, "Wrong input! Try again");
                        }

                    }
                });

                AdministratorGUI.b8.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        //delete menu item from composite product
                        JTextField n1 = new JTextField();
                        JTextField n2 = new JTextField();
                        Object[] message = {
                                "Composite product name:", n1,
                                "Menu name:", n2
                        };

                        int option = JOptionPane.showConfirmDialog(null, message, "Delete menu item from composite product", JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            String cName = n1.getText();
                            String bName = n2.getText();
                            if (DataValidation.isValidName(cName) && DataValidation.isValidName(bName)) {
                                MenuItem cItem = restaurant.findMenuItem(cName);
                                MenuItem bItem = restaurant.findMenuItem(bName);
                                if (cItem != null && bItem != null)
                                    restaurant.deleteBaseFromComItem(cItem, bItem);
                                else JOptionPane.showMessageDialog(null, "Couldn't find a product with this name");
                            } else JOptionPane.showMessageDialog(null, "Wrong input! Try again");
                        }

                    }
                });

                AdministratorGUI.b9.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        //show all menu items
                        JFrame frame = new JFrame("Menu Items");
                        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        frame.setSize(500, 500);

                        JPanel panel = new JPanel();
                        panel.add(restaurant.showAllMenuItems());


                        frame.setContentPane(panel);
                        frame.setVisible(true);
                    }
                });
            }
        });

        RestaurantGUI.b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WaiterGUI waiter = new WaiterGUI();

                WaiterGUI.b1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        //create order
                        String date = WaiterGUI.tf1.getText();
                        String menuItems = WaiterGUI.tf2.getText();
                        String[] tokens = menuItems.split(", ");
                        if (DataValidation.isValidDate(date)) {
                            Collection<MenuItem> items = new ArrayList<MenuItem>();
                            for (String product : tokens) {
                                if (DataValidation.isValidName(product)) {
                                    MenuItem item = restaurant.findMenuItem(product);
                                    if (item != null) {
                                        items.add(item);
                                    } else
                                        JOptionPane.showMessageDialog(null, "There is no product with the name " + product);
                                } else JOptionPane.showMessageDialog(null, "Wrong input! Try again");

                            }
                            if (items != null) {
                                restaurant.createOrder(new Order(0, date, items));
                            }
                        } else JOptionPane.showMessageDialog(null, "Wrong input! Try again");


                    }
                });

                WaiterGUI.b2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        //show menu
                        JFrame frame = new JFrame("Menu");
                        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        frame.setSize(500, 500);

                        JPanel panel = new JPanel();
                        panel.add(restaurant.showAllMenuItems());


                        frame.setContentPane(panel);
                        frame.setVisible(true);
                    }
                });

                WaiterGUI.b3.addActionListener(new ActionListener() {

                    //compute price for order
                    public void actionPerformed(ActionEvent e) {
                        String id = WaiterGUI.tf3.getText();
                        if (DataValidation.isValidNumber(id)) {
                            Order o = restaurant.findOrder(Integer.parseInt(id));
                            if (o != null)
                                JOptionPane.showMessageDialog(null, "The price for order nr " + id + " is " + o.computePrice());
                            else JOptionPane.showMessageDialog(null, "There is no order with this ID!");
                        } else JOptionPane.showMessageDialog(null, "Wrong input! Try again");
                    }
                });

                WaiterGUI.b4.addActionListener(new ActionListener() {

                    //generate bill for order
                    public void actionPerformed(ActionEvent e) {
                        String id = WaiterGUI.tf4.getText();
                        if (DataValidation.isValidNumber(id)) {
                            Order o = restaurant.findOrder(Integer.parseInt(id));
                            if (o != null) {
                                try {
                                    FileWriter.writeInFile("bill_order" + o.getOrderID() + ".txt", o.toString());
                                } catch (FileNotFoundException ex) {
                                } catch (UnsupportedEncodingException ex) {
                                }
                            } else JOptionPane.showMessageDialog(null, "There is no order with this ID!");
                        } else JOptionPane.showMessageDialog(null, "Wrong input! Try again");

                    }
                });

                WaiterGUI.b5.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        //show all orders
                        JFrame frame = new JFrame("Orders");
                        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        frame.setSize(500, 500);

                        JPanel panel = new JPanel();
                        panel.add(restaurant.showAllOrders());


                        frame.setContentPane(panel);
                        frame.setVisible(true);
                    }
                });

            }
        });

        RestaurantGUI.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                RestaurantSerializator.serialize(restaurant);
            }
        });

        RestaurantGUI.b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChefGUI chef = new ChefGUI();
                restaurant.addObserver(chef);
            }
        });

    }
}
