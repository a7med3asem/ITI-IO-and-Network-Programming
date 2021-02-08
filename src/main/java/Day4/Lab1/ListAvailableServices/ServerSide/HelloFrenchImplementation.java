package Day4.Lab1.ListAvailableServices.ServerSide;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloFrenchImplementation extends UnicastRemoteObject implements HelloInterface {
    public HelloFrenchImplementation() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "Bonjour " + name + "!";
    }
}
