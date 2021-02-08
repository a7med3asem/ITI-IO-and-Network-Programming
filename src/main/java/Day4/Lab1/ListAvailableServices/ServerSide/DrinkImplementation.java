package Day4.Lab1.ListAvailableServices.ServerSide;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DrinkImplementation extends UnicastRemoteObject implements DrinkInterface {
    protected DrinkImplementation() throws RemoteException {
        super();
    }

    @Override
    public String drink(String drink) throws RemoteException {
        return "Drinking " + drink;
    }
}
