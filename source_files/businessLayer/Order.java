package businessLayer;

import java.io.Serializable;
import java.util.Collection;


public class Order implements Serializable {
    private int orderID;
    private String date;
    private Collection<MenuItem> menuItems;

    public Order(int orderID, String date, Collection<MenuItem> menuItems) {
        this.orderID = orderID;
        this.date = date;
        this.menuItems = menuItems;
    }

    public int hashCode() {
        return orderID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Order) {
            if (((Order) obj).orderID == orderID)
                return true;
        }
        return false;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getDate() {
        return date;
    }

    public Collection<MenuItem> getMenuItems() {
        return menuItems;
    }

    public float computePrice() {
        float s = 0;
        for (MenuItem m : menuItems) {
            s += m.computePrice();
        }
        return s;
    }

    public String showMenuItems() {
        String s = new String();
        int x = 0;
        for (MenuItem m : menuItems) {
            if (x < menuItems.size() - 1)
                s += m.getName() + ", ";
            else s += m.getName();
            x++;
        }
        return s;
    }

    public String showCompositeProducts() {
        String s = new String();
        for (MenuItem m : menuItems) {
            if (m instanceof CompositeProduct) {
                s+=m.getName()+", ";
            }
        }
        return s;
    }

    public String toString() {
        String s = "OrderID: " + orderID + "\n" + "Date: " + date + "\n";
        for (MenuItem m : menuItems) {
            s += m.toString() + "\n";
        }
        s += "Total Price : " + computePrice();
        return s;
    }
}
