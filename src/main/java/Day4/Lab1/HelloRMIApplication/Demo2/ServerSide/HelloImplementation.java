package Day4.Lab1.HelloRMIApplication.Demo2.ServerSide;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImplementation extends UnicastRemoteObject implements HelloInterface {
    public HelloImplementation() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello" + name;
    }
}
