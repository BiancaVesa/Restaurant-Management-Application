package businessLayer;

import dataLayer.DataValidation;
import dataLayer.FileWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Aceasta clasa are scopul de a crea obiecte necesare aplicatiei care simuleaza functionarea unui restaurant cu un administrator, un chelner si un bucatar.
 * Metodele puse la dispozitie corespund operatiilor pe care acestia le pot efectua.
 * @invariant isEmpty()
 */
public class Restaurant extends Observable implements AdminProcessing, WaiterProcessing, Serializable {
    /**
     * structura de date in care sunt tinute comenzile
     */
    private Map<Order, Collection<MenuItem>> orders = new HashMap<Order, Collection<MenuItem>>();
    /**
     * lista in care sunt pastrate produsele disponibile in meniul restaurantului
     */
    private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
    /**
     * la creare unei noi comenzi, i se atribuie un ID unic, hotarat in cadrul restaurantului
     */
    private int orderIDs = 1;

    /**
     * Adauga un nou produs in meniul restaurantului.
     * @precond Numele menuItem-ului trebuie sa fie valid, iar pretul sau sa fie numar pozitiv.
     * @param menuItem produsul adaugat
     * @postcond menuItems.size() creste cu 1
     */
    @Override
    public void createMenuItem(MenuItem menuItem) {
        assert DataValidation.isValidName(menuItem.getName()) : "Illegal name";
        assert DataValidation.isValidPrice(menuItem.computePrice()) : "Illegal price";
        int size = menuItems.size();
        menuItems.add(menuItem);
        assert size != menuItems.size() : "Not created";
    }

    /**
     * Sterge un produs din meniul restaurantului.
     * Daca alte produse compuse au menuItem in compozitie, acestea vor fi sterse.
     * @precond Numele menuItem-ului trebuie sa fie valid, iar pretul sau sa fie numar pozitiv.
     * @param menuItem produsul sters
     * @postcond menuItems.size() scade cu 1
     */
    @Override
    public void deleteMenuItem(MenuItem menuItem) {
        assert DataValidation.isValidName(menuItem.getName()) : "Illegal name";
        assert DataValidation.isValidPrice(menuItem.computePrice()) : "Illegal price";
        int size = menuItems.size();

        menuItems.remove(menuItem);
        String name = menuItem.getName();
        ArrayList<MenuItem> itemsToRemove = new ArrayList<>();
        for (MenuItem m : menuItems) {
            if (m instanceof CompositeProduct) {
                if (((CompositeProduct) m).hasBaseProduct(name))
                    itemsToRemove.add(m);
            }
        }

        if (!itemsToRemove.isEmpty()) {
            for (MenuItem item : itemsToRemove) {
                deleteMenuItem(item);
            }
        }

        assert size != menuItems.size() : "Not deleted";
    }

    /**
     * Modifica pretul unui produs simplu din meniu.
     * Daca menuItem se afla in compozitia unui produs compus, va fi modificat si pretul acestuia.
     * @precond Numele menuItem-ului trebuie sa fie valid, iar pretul sau si cel nou sa numere pozitive.
     * @param menuItem produsul
     * @param newPrice noul pret
     * @postcond pretul vechi al menuItem sa fie diferit de pretul nou setat
     */
    @Override
    public void editBaseItemPrice(MenuItem menuItem, float newPrice) {
        assert DataValidation.isValidName(menuItem.getName()) : "Illegal name";
        assert DataValidation.isValidPrice(menuItem.computePrice()) : "Illegal price";
        assert DataValidation.isValidPrice(newPrice) : "Illegal price";

        float oldPrice = menuItem.computePrice();

        menuItem.setPrice(newPrice);
        String name = menuItem.getName();
        ArrayList<CompositeProduct> itemsToEdit = new ArrayList<>();
        if (menuItem instanceof BaseProduct) {
            for (MenuItem m : menuItems) {
                if (m instanceof CompositeProduct)
                    if (((CompositeProduct) m).hasBaseProduct(name))
                        itemsToEdit.add((CompositeProduct) m);
            }
        }

        if (!itemsToEdit.isEmpty()) {
            for (CompositeProduct item : itemsToEdit) {
                item.setBaseProductPrice(name, newPrice);
            }
        }

        assert oldPrice != newPrice : "Not modified";
    }

    /**
     * Modifica numele unui produs din meniu.
     * Daca menuItem se afla in compozitia unui produs compus, va fi modificat si in lista acestuia.
     * @precond Numele vechi al menuItem-ului, cat si cel pe care vrem sa il atribuim trebuie sa fie valide, iar pretul sau sa fie numar pozitiv.
     * @param menuItem produsul
     * @param newName numele nou al produsului
     * @postcond numele vechi al menuItem sa fie diferit de numele nou setat
     */
    @Override
    public void editMenuItemName(MenuItem menuItem, String newName) {
        assert DataValidation.isValidName(menuItem.getName()) : "Illegal name";
        assert DataValidation.isValidPrice(menuItem.computePrice()) : "Illegal price";
        assert DataValidation.isValidName(menuItem.getName()) : "Illegal name";

        String oldName = menuItem.getName();
        menuItem.setName(newName);
        String name = menuItem.getName();
        ArrayList<CompositeProduct> itemsToEdit = new ArrayList<>();
        for (MenuItem m : menuItems) {
            if (m instanceof CompositeProduct)
                if (((CompositeProduct) m).hasBaseProduct(name))
                    itemsToEdit.add((CompositeProduct) m);
        }

        if (!itemsToEdit.isEmpty()) {
            for (CompositeProduct item : itemsToEdit) {
                item.setBaseProductName(name, newName);
            }
        }

        assert oldName != name : "Not modified";
    }

    /**
     * Adauga un produs in compozitia unui produs compus.
     * @precond Numele produselor trebuie sa fie valide, iar preturile lor trebuie sa fie numere pozitive
     * @param menuItem    produsul compus
     * @param baseProduct produsul pe care il adaugam
     */
    @Override
    public void addBaseToComItem(MenuItem menuItem, MenuItem baseProduct) {
        assert DataValidation.isValidName(menuItem.getName()) : "Illegal name";
        assert DataValidation.isValidPrice(menuItem.computePrice()) : "Illegal price";

        assert DataValidation.isValidName(menuItem.getName()) : "Illegal name";
        assert DataValidation.isValidPrice(menuItem.computePrice()) : "Illegal price";

        CompositeProduct product = (CompositeProduct) menuItem;
        product.addBaseProduct(baseProduct);
    }

    /**
     * Sterge un produs din compozitia unui produs compus.
     * @precond Numele produselor trebuie sa fie valide, iar preturile lor trebuie sa fie numar pozitiv.
     * @param menuItem    produsul compus
     * @param baseProduct produsul pe care il stergem
     */
    @Override
    public void deleteBaseFromComItem(MenuItem menuItem, MenuItem baseProduct) {
        assert DataValidation.isValidName(menuItem.getName()) : "Illegal name";
        assert DataValidation.isValidPrice(menuItem.computePrice()) : "Illegal price";

        assert DataValidation.isValidName(menuItem.getName()) : "Illegal name";
        assert DataValidation.isValidPrice(menuItem.computePrice()) : "Illegal price";

        CompositeProduct product = (CompositeProduct) menuItem;
        product.delBaseProduct(baseProduct);
    }

    /**
     * Creeaza un JTable, in care adauga toate produsele din meniul restaurantului.
     *
     * @return un JTable, care contine tot meniul restaurantului
     */
    @Override
    public JTable showAllMenuItems() {
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("Name");
        model.addColumn("Price");
        model.addColumn("Composition");
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        Vector<Object> data1 = new Vector<Object>();
        data1.add("Name");
        data1.add("Price");
        data1.add("Composition");
        model.addRow(data1);

        for (MenuItem m : menuItems) {
            Vector<Object> data = new Vector<Object>();
            data.add(m.getName());
            data.add(m.computePrice());
            if (m instanceof BaseProduct) {
                data.add("-");
            } else data.add(((CompositeProduct) m).getBaseProducts());
            model.addRow(data);
        }
        return table;
    }

    /**
     * Cauta dupa nume un produs din meniul restaurantului.
     *
     * @param name numele produsului
     * @return MenuItem care corespunde numelui ; null daca produsul nu se gaseste in meniu
     */
    public MenuItem findMenuItem(String name) {
        MenuItem item = null;
        for (MenuItem m : menuItems) {
            if (m.getName().equals(name)) {
                item = m;
                break;
            }
        }
        return item;
    }

    /**
     * Creeaza o comanda noua, iar daca aceasta contine un produs compus, o adauga in lista de comenzi a bucatarului ( observer ).
     * @precond Data comenzii trebuie sa fie valida.
     * @param order comanda plasata
     * @postcond valoarea orders.size() sa creasca cu 1
     */
    @Override
    public void createOrder(Order order) {
        assert !isEmpty() : "Can't take orders";
        assert DataValidation.isValidDate(order.getDate()) : "Illegal date";

        int size = orders.size();
        order.setOrderID(orderIDs++);
        orders.put(order, order.getMenuItems());
        if (!order.showCompositeProducts().isEmpty()) {
            this.addOrder(order);
        }

        assert size != orders.size() : "Not created";
    }

    /**
     * Calculeaza pretul unei comenzi.
     * @precond Data comenzii trebuie sa fie valida.
     * @param order comanda plasata
     * @return pretul comenzii
     */
    @Override
    public float computePrice(Order order) {
        assert DataValidation.isValidDate(order.getDate()) : "Illegal date";

        return order.computePrice();
    }

    /**
     * Creeaza un fisier care va contine chitanta pentru comanda data ca parametru.
     * @precond Data comenzii trebuie sa fie valida.
     * @param order comanda pentru care vrem sa cream chitanta
     */
    @Override
    public void generateBill(Order order) {
        assert DataValidation.isValidDate(order.getDate()) : "Illegal Arguments";

        String file = "order_" + order.getOrderID() + ".txt";
        try {
            FileWriter.writeInFile(file, order.toString());
        } catch (FileNotFoundException e) {
        } catch (UnsupportedEncodingException e) {
        }
    }

    /**
     * Creeaza un JTable, in care adauga toate comenzile plasate de chelner.
     *
     * @return tabelul cu comenzi
     */
    @Override
    public JTable showAllOrders() {
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Date");
        model.addColumn("Menu Items");
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        Vector<Object> data1 = new Vector<Object>();
        data1.add("ID");
        data1.add("Date");
        data1.add("Menu Items");
        model.addRow(data1);

        ArrayList<Order> o = new ArrayList<>();
        Iterator it = orders.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Order order = (Order) pair.getKey();
            o.add(order);
        }

        for (Order or : o) {
            Vector<Object> data = new Vector<Object>();
            data.add(or.getOrderID());
            data.add(or.getDate());
            data.add(or.showMenuItems());
            model.addRow(data);
        }
        return table;
    }

    /**
     * Cauta o comanda plasata de chelner dupa ID-ul acesteia.
     *
     * @param orderID ID-ul comenzii
     * @return comanda care are ID-ul orderID; null daca aceasta nu a fost gasita
     */
    public Order findOrder(int orderID) {
        Order o = null;
        Iterator it = orders.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Order order = (Order) pair.getKey();
            if (order.getOrderID() == orderID) {
                o = order;
                break;
            }
        }
        return o;
    }

    //restaurantul trebuie sa aiba mereu produse in stoc, pentru a putea plasa comenzi

    /**
     * Invariantul clasei.
     * @return true daca restaurantul nu are produse in stoc; false in caz contrar
     */
    private boolean isEmpty() {
        if (menuItems.isEmpty()) {
            return true;
        }
        return false;
    }

}
