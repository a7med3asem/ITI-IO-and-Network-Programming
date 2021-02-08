package Day4.Lab1.ListAvailableServices.ServerSide;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server{
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            Registry registry = LocateRegistry.getRegistry();

            HelloImplementation implementationObject = new HelloImplementation();
            registry.rebind("HelloService", implementationObject);

            DrinkImplementation implementationObject2 = new DrinkImplementation();
            registry.rebind("DrinkService", implementationObject2);

            HelloFrenchImplementation implementationObject3 = new HelloFrenchImplementation();
            registry.rebind("HelloFrenchService", implementationObject3);

            System.out.println("[System] Server is ready.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}