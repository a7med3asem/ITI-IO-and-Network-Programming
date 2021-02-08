package ChatApplication.ClientSide;

import ChatApplication.Interfaces.ChatClientInterface;
import ChatApplication.UI.Controllers.ChatController;
import javafx.application.Platform;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImplementation extends UnicastRemoteObject implements ChatClientInterface {
    private final ChatController chatController;

    public ChatClientImplementation(ChatController client) throws RemoteException {
        chatController = client;
    }

    @Override
    public void tell(String msg) throws RemoteException {
//        System.out.println(msg);
        Platform.runLater(()->{ chatController.writeMessage(msg); });
    }

//    @Override
    public String getName() throws RemoteException {
//        return name;
        return "Name";
    }
}
