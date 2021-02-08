package ChatApplication.Models;

import javafx.scene.image.Image;

import java.io.Serializable;

public class User implements Serializable {
    private Image image;
    private String userName;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
