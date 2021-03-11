package businessLayer;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String name;
    private float price;

    public MenuItem(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public float computePrice() {
        return price;
    }

    public void setPrice(float newPrice){
        price = newPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return name+", price: "+price;
    }
}
