package ChatApplication.ClientSide;

import ChatApplication.Interfaces.ChatServerInterface;
import ChatApplication.UI.Controllers.MessageController;
import ChatApplication.Models.User;
import ChatApplication.UI.Controllers.ServerMessageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;

public class ClientApp extends Application {
    public static Scene scene;
    public static User user;
    public static ChatServerInterface server;

    @Override
    public void start(Stage stage) {
        try {
            // Locating name service and get object reference
            server = (ChatServerInterface) Naming.lookup("rmi://127.0.0.1/ChatService");
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

        scene = new Scene(loadFXML("/views/login"), 600, 350);
        stage.setScene(scene);
        stage.setTitle("Chat app");
        stage.setMinHeight(350);
        stage.setMinWidth(600);
        stage.show();
    }

    public static void switchToChatScene() {
        Stage stage = (Stage) ClientApp.scene.getWindow();
        stage.setScene(new Scene(loadFXML("/views/chat"), 600, 350));
    }

    public static Parent createMessageInstance(String msg, String userName, Date date) {
        MessageController message;
        Parent temp = null;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ClientApp.class.getResource("/views/message.fxml"));

        try {
            temp = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        message = loader.getController();

        message.textMessage.setText(msg);
        message.userName.setText(userName);
        message.dateLabel.setText(date.toString());

        return temp;
    }

    public static Parent createServerMessageInstance(String msg, String userName, Date date) {
        ServerMessageController message;
        Parent temp = null;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ClientApp.class.getResource("/views/serverMessage.fxml"));

        try {
            temp = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        message = loader.getController();

        message.textMessage.setText(msg);
        message.userName.setText(userName);
        message.dateLabel.setText(date.toString());

        return temp;
    }

    public static void createUserInstance(String name, Image image) {
        user = new User();
        user.setUserName(name);
        user.setImage(image);
    }

    private static Parent loadFXML(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource(fxml + ".fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/ChatApplication.org.asem.Controllers/login.fxml"));
//        Parent root = FXMLLoader.load(App.class.getResource("G:\\JETS\\IO and Network Programming\\Day4\\IONetworkProgrammingWithJava\\src\\main\\resources\\login.fxml"));
//        return fxmlLoader.load();
//        URL url = new File("src/main/resources/login.fxml").toURI().toURL();
//        Parent root = FXMLLoader.load(url);
//        return root;
//        return fxmlLoader.load();

//        FXMLLoader fxmlLoader = new FXMLLoader(new URL()File("G:\\JETS\\IO and Network Programming\\Day4\\IONetworkProgrammingWithJava\\src\\main\\resources\\ChatApplication.org.asem.Controllers\\login.fxml"));
    }

    public static void main(String[] args) {
        launch();
    }
}