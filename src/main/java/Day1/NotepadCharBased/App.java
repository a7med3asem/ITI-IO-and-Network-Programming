package Day1.NotepadCharBased;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

            currentFile = fileChooser.showOpenDialog(stage);
//            currentFile = fileChooser.showOpenDialog(null);

            if (currentFile != null) {
                textEditor.clear();
                try {
                    FileReader fileReader = new FileReader(currentFile);

                    int c;
                    while ((c = fileReader.read()) != -1) {
                        textEditor.appendText(String.valueOf((char) c));
                    }
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        saveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+s"));
        saveItem.setOnAction(actionEvent -> {
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                fileWriter.write(textEditor.getText());
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            DirectoryChooser directoryChooser = new DirectoryChooser();
//            directoryChooser.setTitle("Save file as");
//            File file = directoryChooser.showDialog(stage);
        });

        saveAsItem.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                try {
                    currentFile = file;
//                    FileWriter fileWriter = new FileWriter(file.toURI().toString());
                    FileWriter fileWriter = new FileWriter(file.getPath());
                    fileWriter.write(textEditor.getText());
                    fileWriter.close();
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
            }
            else {
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