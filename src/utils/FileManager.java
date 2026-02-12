package utils;

import model.PrintingHouse;

import java.io.*;

public class FileManager {

    public static void save(PrintingHouse house, String file) throws IOException {

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(house);
        out.close();
    }

    public static PrintingHouse load(String file) throws IOException, ClassNotFoundException {

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        PrintingHouse house = (PrintingHouse) in.readObject();
        in.close();
        return house;
    }
}
