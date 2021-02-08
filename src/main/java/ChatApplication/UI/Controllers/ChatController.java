package ChatApplication.UI.Controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.ResourceBundle;

import ChatApplication.ClientSide.ClientApp;
import ChatApplication.ClientSide.ChatClientImplementation;
import ChatApplication.Interfaces.ChatClientInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class ChatController implements Initializable {
    @FXML
    private ImageView userImage;
    @FXML
    private VBox chatContainer;
    @FXML
    private TextArea messageArea;
    @FXML
    public Text userName;
    @FXML
    public ScrollPane scrollPane;

    @FXML
    private void onEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER && !messageArea.getText().trim().isEmpty()) {
            Parent messageInstance =
                    ClientApp.createMessageInstance(messageArea.getText(), ClientApp.user.getUserName(), new Date());
            try {
                ClientApp.server.tellOthers(messageArea.getText());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            chatContainer.getChildren().add(messageInstance);
            messageArea.clear();
        }
    }

    public void writeMessage(String msg) {
//        System.out.println(msg);
        Parent serverMessage =
                ClientApp.createServerMessageInstance(msg, "Server", new Date());
        chatContainer.getChildren().add(serverMessage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userImage.setImage(ClientApp.user.getImage());
        userName.setText(ClientApp.user.getUserName());

        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        scrollPane.vvalueProperty().bind(chatContainer.heightProperty());

        messageArea.setWrapText(true);

        try {
            ChatClientInterface client = new ChatClientImplementation(this);
            // Register
            ClientApp.server.register(client);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}