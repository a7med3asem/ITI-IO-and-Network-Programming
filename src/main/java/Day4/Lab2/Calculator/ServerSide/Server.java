package Day4.Lab2.Calculator.ServerSide;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server{
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            Registry registry = LocateRegistry.getRegistry();
            CalculatorImplementation implementationObject = new CalculatorImplementation();
            registry.rebind("CalculatorService", implementationObject);

            System.out.println("[System] CalculatorServer is ready.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}