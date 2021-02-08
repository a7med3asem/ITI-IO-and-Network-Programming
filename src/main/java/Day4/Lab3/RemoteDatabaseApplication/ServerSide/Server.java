package Day4.Lab3.RemoteDatabaseApplication.ServerSide;

import Day4.Lab3.RemoteDatabaseApplication.ServerSide.DB.DTOImplementation;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            Registry registry = LocateRegistry.getRegistry();

            DTOImplementation implementationObject = new DTOImplementation();
            registry.rebind("DatabaseConnection", implementationObject);

            System.out.println("[System] Server is ready.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}