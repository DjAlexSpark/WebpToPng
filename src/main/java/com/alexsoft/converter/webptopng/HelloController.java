package com.alexsoft.converter.webptopng;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HelloController implements Initializable {
    Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button buttonDirectoryIntoChooser;

    @FXML
    private Button buttonFileFromChooser;

    @FXML
    private Button buttonStart;

    @FXML
    private TextField textFieldDirectoryInto;

    @FXML
    private TextField textFieldOutputName;

    @FXML
    private TextField textFieldWithName;

    @FXML
    void buttonDirectoryIntoChooserOnAction(ActionEvent event) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Выберите папку для сохранения");
        File defaultDirectory = new File("C:\\");
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(stage);
        if (selectedDirectory.exists()){
            textFieldDirectoryInto.setText(selectedDirectory.getAbsolutePath());
            textFieldDirectoryInto.setStyle("");
        }
    }

    @FXML
    void buttonFileFromChooserOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбрать файл webp");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Webp, Heic", "*.webp"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            textFieldWithName.setText(selectedFile.getAbsolutePath());
            textFieldWithName.setStyle("");
        }
    }

    @FXML
    void textFieldTakeFileFromOnKeyTyped(KeyEvent event) {
        try {
            System.out.println(((TextField)event.getSource()).getText());
            ((TextField) event.getSource()).getStyle();
            if(Files.notExists(Path.of(((TextField) event.getSource()).getText()))){
                ((TextField) event.getSource()).setStyle("-fx-text-fill:#ffb433");
            }else{((TextField) event.getSource()).setStyle("");}
            ((TextField)event.getSource()).getText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
}
