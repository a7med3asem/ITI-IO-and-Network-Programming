package Day4.Lab1.HelloRMIApplication.Demo1.ServerSide;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloInterface extends Remote {
    String sayHello(String name) throws RemoteException;
}
