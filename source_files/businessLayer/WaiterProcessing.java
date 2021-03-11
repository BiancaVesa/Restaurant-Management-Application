package businessLayer;

import javax.swing.*;

public interface WaiterProcessing {
    /**
     * @param order comanda plasata
     */
    public void createOrder(Order order);

    /**
     * @param order comanda plasata
     * @return pretul comenzii
     */
    public float computePrice(Order order);

    /**
     * @param order comanda pentru care vrem sa cream chitanta
     */
    public void generateBill(Order order);

    /**
     * @return un JTable, care contine toate comenzile
     */
    public JTable showAllOrders();
}
