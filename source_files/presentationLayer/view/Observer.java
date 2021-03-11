package presentationLayer.view;


import businessLayer.Order;


public interface Observer {
    public void cook(Order order);
}
