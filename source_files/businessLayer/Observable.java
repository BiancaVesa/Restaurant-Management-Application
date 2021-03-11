package businessLayer;

import presentationLayer.view.Observer;

public abstract class Observable {
    private Observer observer;

    public void addObserver(Observer observer){
        this.observer = observer;
    }

    public void addOrder(Order order){
        observer.cook(order);
    }

}
