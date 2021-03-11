package businessLayer;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable {

    public BaseProduct(String name, float price) {
        super(name, price);
    }

    public float computePrice() {
        return super.computePrice();
    }

}
