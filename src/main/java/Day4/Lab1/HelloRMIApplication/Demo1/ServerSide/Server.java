package Day4.Lab1.HelloRMIApplication.Demo1.ServerSide;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            HelloInterface implementationObject = new HelloImplementation();

            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://127.0.0.1/myService", implementationObject);
            System.out.println("[System] Server is ready.");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}