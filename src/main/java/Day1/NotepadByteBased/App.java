package Day1.NotepadByteBased;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class App extends Application {
    private TextArea textEditor;
    private String stringBuffer;
    private File currentFile;

    @Override
    public void start(Stage stage) {
        /* File Item menu items */
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");
        MenuItem exitItem = new MenuItem("Exit");

        newItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        newItem.setOnAction(e -> textEditor.clear());

        openItem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        openItem.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");

            currentFile = fileChooser.showOpenDialog(null);

            if (currentFile != null) {
                textEditor.clear();
                try (FileInputStream fis = new FileInputStream(currentFile)) {
                    int i;
                    do {
                        byte[] buf = new byte[1024];
                        i = fis.read(buf);
                        String value = new String(buf, StandardCharsets.UTF_8);
                        textEditor.appendText(value);
                    } while (i != -1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        saveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+s"));
        saveItem.setOnAction(actionEvent -> {
            if (currentFile != null) {
                try (FileOutputStream fos = new FileOutputStream(currentFile)) {
                    byte[] mybytes = textEditor.getText().getBytes();
                    fos.write(mybytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        saveAsItem.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(stage);

            currentFile = file;

            if (file != null) {
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    byte[] mybytes = textEditor.getText().getBytes();
                    fos.write(mybytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        exitItem.setAccelerator(KeyCombination.keyCombination("Alt+F4"));
        exitItem.setOnAction(e -> Platform.exit());

        /* Add items to file menu */
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(newItem, openItem, saveItem, saveAsItem, new SeparatorMenuItem(), exitItem);

        /* Edit item Menu items */
        MenuItem undoItem = new MenuItem("Undo");
        MenuItem cutItem = new MenuItem("Cut");
        MenuItem copyItem = new MenuItem("Copy");
        MenuItem pasteItem = new MenuItem("Paste");
        MenuItem deleteItem = new MenuItem("Delete");
        MenuItem selectAllItem = new MenuItem("Select All");

        undoItem.setOnAction(actionEvent -> textEditor.undo());

        cutItem.setDisable(true);
        cutItem.setOnAction(actionEvent -> {
            stringBuffer = textEditor.getSelectedText();
            textEditor.setText(
                    textEditor.getText().replace(textEditor.getSelectedText(), ""));
        });

        copyItem.setDisable(true);
        copyItem.setOnAction(actionEvent -> stringBuffer = textEditor.getSelectedText());

        pasteItem.setOnAction(actionEvent -> textEditor.insertText(textEditor.getCaretPosition(), stringBuffer));

        deleteItem.setOnAction(e -> textEditor.replaceSelection(""));

        selectAllItem.setOnAction(actionEvent -> textEditor.selectAll());
        Menu editMenu = new Menu("Edit");
        editMenu.getItems().addAll(undoItem, new SeparatorMenuItem(), cutItem, copyItem,
                pasteItem, deleteItem, new SeparatorMenuItem(), selectAllItem);

        editMenu.setOnShowing(event -> {
            if(textEditor.getSelectedText() != null){
                cutItem.setDisable(false);
                copyItem.setDisable(false);
            } else {
                cutItem.setDisable(true);
                cutItem.setDisable(true);
            }
        });

        MenuItem aboutNotepadItem = new MenuItem("About Notepad");
        aboutNotepadItem.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About Notepad");
            alert.setContentText("Text editor");
            alert.show();
        });

        Menu helpMenu = new Menu("Help");
        helpMenu.getItems().addAll(aboutNotepadItem);

        MenuBar bar = new MenuBar();
        bar.getMenus().addAll(fileMenu, editMenu, helpMenu);

        textEditor = new TextArea();
        textEditor.setFont(Font.font(20));
        textEditor.setWrapText(true);

        BorderPane pane = new BorderPane();
        pane.setTop(bar);
        pane.setCenter(textEditor);

        var scene = new Scene(pane, 640, 480);
        stage.setScene(scene);
        stage.setTitle("FX NotePad");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}