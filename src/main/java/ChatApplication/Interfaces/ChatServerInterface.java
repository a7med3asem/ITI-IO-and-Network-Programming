package ChatApplication.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface ChatServerInterface extends Remote {
    void tellOthers(String msg) throws RemoteException;
    void register(ChatClientInterface clientReference) throws RemoteException;
    void unRegister(ChatClientInterface clientReference) throws RemoteException;
    void publish(String msg) throws RemoteException;
    Vector getConnected() throws RemoteException;
}
