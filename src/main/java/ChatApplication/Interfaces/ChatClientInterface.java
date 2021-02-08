package ChatApplication.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientInterface extends Remote {
    void tell(String msg) throws RemoteException;
    String getName() throws RemoteException;
}
