package ChatApplication.UI.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ChatApplication.ClientSide.ClientApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    @FXML
    private ImageView userImage;
    @FXML
    private TextField userNameField;

    @FXML
    private void switchToSecondary() throws IOException {
        if(!userNameField.getText().trim().isEmpty()){
            ClientApp.createUserInstance(userNameField.getText(), userImage.getImage());
//            StageCoordinator stageCoordinator = StageCoordinator.getInstance();
//            stageCoordinator.switchToChatScene();
            ClientApp.switchToChatScene();
        }
    }

    @FXML
    private void choosePicture(){
        Stage stage = (Stage) ClientApp.scene.getWindow();

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);

        if (file == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Couldn't open file");
            alert.setContentText("Invalid file");
            alert.showAndWait();
        } else {
            userImage.setImage(new Image(file.toURI().toString()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameField.setOnKeyTyped(e -> {
            if (userNameField.getText().length() >= 30) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Too long username");
                alert.setContentText("Username length is invalid");
                alert.showAndWait();
            }
        });
    }
}