package Day4.Lab1.ListAvailableServices.ClientSide;

import Day4.Lab1.ListAvailableServices.ServerSide.DrinkInterface;
import Day4.Lab1.ListAvailableServices.ServerSide.HelloInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            // Getting instance of local registry table
            Registry registry = LocateRegistry.getRegistry(1099);
            // Get list of services
            String [] list = registry.list();
            // Print it
            for (int i = 0 ; i < list.length ; i++)
                System.out.println("Service " + i + " : " + list[i]);

            System.out.println();

            // Locating name service and get object reference
            HelloInterface helloInterfaceReference = (HelloInterface) registry.lookup("HelloService");
            // Call Server methods
            String str = helloInterfaceReference.sayHello("JETS");
            // Print result
            System.out.println(str);

            HelloInterface french = (HelloInterface) registry.lookup("HelloFrenchService");
            System.out.println(french.sayHello("Ahmed"));

            DrinkInterface drinkInterface = (DrinkInterface) registry.lookup("DrinkService");
            System.out.println(drinkInterface.drink("Coffee"));

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}