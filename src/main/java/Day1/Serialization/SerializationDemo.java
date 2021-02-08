package Day1.Serialization;

import java.io.*;

public class SerializationDemo {
    public void serialize() {
        try {
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream("serial.txt"));

            Person person = new Person("Asim", 25, 65.0);

            System.out.println("Person: " + person);

            objectOutputStream.writeObject(person);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialize() {
        try {
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(new FileInputStream("serial.txt"));

            Person person = (Person) objectInputStream.readObject();

            System.out.println("Person2 : " + person);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SerializationDemo sd = new SerializationDemo();
        sd.serialize();
        sd.deserialize();
    }
}
