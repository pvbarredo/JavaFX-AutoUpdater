package com.pobreng.programmer;

import com.pobreng.programmer.model.Changelog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class AutoUpdate {

    public AutoUpdate() {

    }

    public void start() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AutoUpdateMainWindow.fxml"));
        Pane pane = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(pane));
        stage.setTitle("Auto Updater");
        stage.show();
    }


    private Boolean hasUpdate() {
        return true;
    }

    public void checkUpdates() {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Auto Updater v 1.0");
            alert.setHeaderText(null);


            if (hasUpdate()) {

                Changelog changelog = getChangeLog();
                //todo: run changelog with asking of update and cancel in below

                VBox dialogPaneContent = new VBox();
                Label label = new Label("Changelogs");
                TextArea textArea = new TextArea();
                textArea.setText("asdasdasdasdasd");

                dialogPaneContent.getChildren().addAll(label, textArea);

                alert.getDialogPane().setContent(dialogPaneContent);

                ButtonType updateButton = new ButtonType("Update");

                alert.getButtonTypes().removeAll();
                alert.getButtonTypes().add(updateButton);

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == updateButton) {

                    start();

                }


            } else {
                //todo: alert window that there is no update
                System.out.println("No updates available");


                alert.setContentText("No updates available");

                alert.show();


            }
            //todo:catching kapag walang internet

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Changelog getChangeLog() {
        return new Changelog();
    }

}
