package ChatApplication.ServerSide;

import ChatApplication.Interfaces.ChatServerInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerApp {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            ChatServerInterface obj = new ChatServerImplementation();
            Naming.rebind("rmi://127.0.0.1/ChatService", obj);
            System.out.println("[System] Chat server is ready.");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
