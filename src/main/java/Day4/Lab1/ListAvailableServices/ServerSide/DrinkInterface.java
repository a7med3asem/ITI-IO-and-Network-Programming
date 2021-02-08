package Day4.Lab1.ListAvailableServices.ServerSide;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DrinkInterface extends Remote {
    String drink(String drink) throws RemoteException;
}
