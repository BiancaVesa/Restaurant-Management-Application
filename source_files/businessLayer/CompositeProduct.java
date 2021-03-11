package businessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem implements Serializable {
    private List<MenuItem> baseProducts;

    public CompositeProduct(String name, ArrayList<MenuItem> list) {
        super(name, 0);
        baseProducts = list;
        this.setPrice(computePrice());
    }

    public float computePrice() {
        float price = 0;
        for (MenuItem m : baseProducts) {
            price += m.computePrice();
        }
        return price;
    }

    public void addBaseProduct(MenuItem baseProduct) {
        baseProducts.add(baseProduct);
    }

    public void delBaseProduct(MenuItem baseProduct) {
        baseProducts.remove(baseProduct);
        for (MenuItem product : baseProducts) {
            ArrayList<MenuItem> itemsToRemove = new ArrayList<>();
            if (product instanceof CompositeProduct) {
                if (((CompositeProduct) product).hasBaseProduct(baseProduct.getName()))
                    itemsToRemove.add(product);
            }
        }
    }

    public void setBaseProductName(String oldName, String newName) {
        for (MenuItem product : baseProducts) {
            if (product.getName().equals(oldName)) {
                product.setName(newName);
            }
        }
    }

    public void setBaseProductPrice(String name, float newPrice) {
        for (MenuItem product : baseProducts) {
            if (product.getName().equals(name)) {
                product.setPrice(newPrice);
            }
        }
    }

    public boolean hasBaseProduct(String name) {
        boolean res = false;
        for (MenuItem product : baseProducts) {
            if (product.getName().equals(name)) {
                res = true;
                break;
            }

        }
        return res;
    }

    public String getBaseProducts() {
        String s = new String();
        for (MenuItem product : baseProducts) {
            s += product.getName() + ", ";
        }
        return s;
    }

    public String toString() {
        String s = super.getName() + ", price: " + computePrice();
        return s;
    }
}
