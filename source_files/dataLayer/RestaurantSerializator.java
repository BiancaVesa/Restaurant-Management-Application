package dataLayer;

import businessLayer.Restaurant;

import java.io.*;

public class RestaurantSerializator {

    public static void serialize(Restaurant restaurant) {
        try {
            FileOutputStream fileOut = new FileOutputStream("restaurant.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(restaurant);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Restaurant deserialize(String fileName) {
        Restaurant restaurant = new Restaurant();
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            restaurant = (Restaurant) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Restaurant class not found");
            c.printStackTrace();
            return null;
        }
        return restaurant;
    }
}
