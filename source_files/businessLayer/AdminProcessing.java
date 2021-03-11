package businessLayer;

import javax.swing.*;

public interface AdminProcessing {
    /**
     * @param menuItem produsul adaugat
     */
    public void createMenuItem(MenuItem menuItem);

    /**
     * @param menuItem produsul sters
     */
    public void deleteMenuItem(MenuItem menuItem);

    /**
     * @param menuItem produsul
     * @param newPrice noul pret
     */
    public void editBaseItemPrice(MenuItem menuItem, float newPrice);

    /**
     * @param menuItem produsul
     * @param name noul nume
     */
    public void editMenuItemName(MenuItem menuItem, String name);

    /**
     * @param menuItem produsul compus
     * @param baseProduct produsul de baza
     */
    public void deleteBaseFromComItem(MenuItem menuItem, MenuItem baseProduct);

    /**
     * @param menuItem produsul compus
     * @param baseProduct produsul de baza
     */
    public void addBaseToComItem(MenuItem menuItem, MenuItem baseProduct);

    /**
     * Creeaza un JTable, in care adauga toate produsele din meniul restaurantului.
     * @return un JTable, care contine tot meniul restaurantului
     */
    public JTable showAllMenuItems();
}
