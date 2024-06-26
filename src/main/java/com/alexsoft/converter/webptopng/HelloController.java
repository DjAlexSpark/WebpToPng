package com.alexsoft.converter.webptopng;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

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
    private Label statusLabel;

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

        if (selectedDirectory!=null){
        if (selectedDirectory.exists()){
            textFieldDirectoryInto.setText(selectedDirectory.getAbsolutePath());
            textFieldDirectoryInto.setStyle("");
        }}
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
        buttonStart.setOnAction(event->{
            buttonStart.setDisable(true);
            if (!textFieldOutputName.getText().trim().isEmpty()&&
                    Files.isRegularFile(Path.of(textFieldWithName.getText().trim()))&&
                    !textFieldDirectoryInto.getText().isEmpty()&&Files.exists(Path.of(textFieldDirectoryInto.getText().trim()))){
                //процесс конвертации тут
                Path pathOfInputFile = Path.of(textFieldWithName.getText());
                Path pathOfOutputFile = (Path.of(textFieldDirectoryInto.getText()))
                        .normalize().resolve(textFieldOutputName.getText().concat(".png"));
                File fileOfInputFile = new File(pathOfInputFile.toUri());
                File fileOfOutputFile = new File(pathOfOutputFile.toUri());
                try {
                    BufferedImage imageToRead = ImageIO.read(fileOfInputFile);
                    BufferedImage transparentImage =
                            new BufferedImage(imageToRead.getWidth(), imageToRead.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = transparentImage.createGraphics();
                    g2d.drawImage(imageToRead, 0, 0, null);
                    g2d.dispose();
                    ImageIO.write(transparentImage, "png", fileOfOutputFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }else {if (textFieldWithName.getText().isEmpty()) {
                textFieldWithName.setStyle("-fx-border-color:red");
            }else {
                textFieldWithName.setStyle("");
            }
                if (textFieldOutputName.getText().isEmpty()) {
                    textFieldOutputName.setStyle("-fx-border-color:red");
                }else {
                    textFieldOutputName.setStyle("");
                }
                if (textFieldDirectoryInto.getText().isEmpty()) {
                    textFieldDirectoryInto.setStyle("-fx-border-color:red");
                }else {
                    textFieldDirectoryInto.setStyle("");
                }}
            // имя исходника
//            textFieldOutputName //выходное имя
//            textFieldDirectoryInto//имя выходной папки
            //2 запуск  конверта

            buttonStart.setDisable(false);
            statusLabel.setText("Конвертирование завершено");
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(3500);
                    } catch (InterruptedException e) {
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    statusLabel.setText("");
                }
            });
            Thread thread = new Thread(sleeper);
            thread.setDaemon(true);
            thread.start();
        });


    }
}
