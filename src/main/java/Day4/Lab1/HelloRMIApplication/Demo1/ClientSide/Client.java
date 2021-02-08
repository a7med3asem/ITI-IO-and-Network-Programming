package Day4.Lab1.HelloRMIApplication.Demo1.ClientSide;

import Day4.Lab1.HelloRMIApplication.Demo1.ServerSide.HelloInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try {
            // Locating name service and get object reference
            HelloInterface helloInterfaceReference = (HelloInterface) Naming.lookup("rmi://127.0.0.1/myService");
            // Call Server methods
            String str = helloInterfaceReference.sayHello("JETS");
            // Print result
            System.out.println(str);
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
