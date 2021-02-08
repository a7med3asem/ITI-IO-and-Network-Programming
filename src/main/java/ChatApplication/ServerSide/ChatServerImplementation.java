package ChatApplication.ServerSide;

import ChatApplication.Interfaces.ChatClientInterface;
import ChatApplication.Interfaces.ChatServerInterface;
import javafx.scene.image.Image;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class ChatServerImplementation extends UnicastRemoteObject implements ChatServerInterface {

    Vector<ChatClientInterface> clientsVector = new Vector<>();
    Image serverImage;

    public ChatServerImplementation() throws RemoteException {
    }

    @Override
    public void tellOthers(String msg) {
        System.out.println("Message received : " + msg);
        for (ChatClientInterface clientRef : clientsVector) {
            try {
                clientRef.tell(msg);
            } catch (RemoteException e) {
                System.out.println("Cannot send msg to Client!");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void register(ChatClientInterface clientRef) throws RemoteException {
        System.out.println(clientRef.getName() + " added");
        clientRef.tell("You have connected successfully");

        clientsVector.add(clientRef);
    }

    @Override
    public void unRegister(ChatClientInterface clientRef) throws RemoteException {
        System.out.println(clientRef.getName() + " removed");
        clientRef.tell("You have disconnected");
        publish(clientRef.getName() + "has just disconnected");
        clientsVector.remove(clientRef);
    }

    @Override
    public void publish(String msg) throws RemoteException {
        System.out.println("Message: " + msg);
        for (ChatClientInterface client : clientsVector) {
            client.tell(msg);
        }
    }

    @Override
    public Vector<ChatClientInterface> getConnected() throws RemoteException {
        return clientsVector;
    }
}
