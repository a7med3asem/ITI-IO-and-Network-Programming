package Day4.Lab1.HelloRMIApplication.Demo2.ClientSide;

import Day4.Lab1.HelloRMIApplication.Demo2.ServerSide.HelloInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            // Getting instance of local registry table
            Registry registry = LocateRegistry.getRegistry(1099);
            // Locating name service and get object reference
            HelloInterface helloInterfaceReference = (HelloInterface) registry.lookup("HelloService");
            // Call Server methods
            String str = helloInterfaceReference.sayHello("JETS");
            // Print result
            System.out.println(str);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}