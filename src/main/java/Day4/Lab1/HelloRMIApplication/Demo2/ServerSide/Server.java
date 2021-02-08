package Day4.Lab1.HelloRMIApplication.Demo2.ServerSide;

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

            System.out.println("[System] Server is ready.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}