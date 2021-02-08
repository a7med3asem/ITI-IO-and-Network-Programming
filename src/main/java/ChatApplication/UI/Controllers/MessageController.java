package ChatApplication.UI.Controllers;

import ChatApplication.ClientSide.ClientApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageController extends Parent implements Initializable {
    @FXML
    public Label textMessage;
    @FXML
    public Text userName;
    @FXML
    public Circle userIcon;
    @FXML
    public Label dateLabel;
    @FXML
    public Rectangle rectMessage;
    @FXML
    public AnchorPane messageContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textMessage.setWrapText(true);
        userIcon.setFill(new ImagePattern(ClientApp.user.getImage()));
        textMessage.setMinHeight(Region.USE_PREF_SIZE);
        rectMessage.widthProperty().bind(messageContainer.widthProperty().subtract(100));
    }

    @Override
    public Node getStyleableNode() {
        return null;
    }
}
