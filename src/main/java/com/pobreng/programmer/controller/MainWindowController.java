package com.pobreng.programmer.controller;

import com.pobreng.programmer.service.DownloadService;
import com.pobreng.programmer.service.ZipService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.concurrent.TimeUnit;


public class MainWindowController {

    @FXML
    ProgressBar progressBar;

    @FXML
    Label progressLabel;


    public void initialize() {

        DownloadService downloadService = new DownloadService();
        progressBar.progressProperty().bind(downloadService.progressProperty());
        progressLabel.textProperty().bind(downloadService.messageProperty());
        downloadService.start();

        ZipService zipService = new ZipService();
        zipService.setOnSucceeded(succeed ->{

            try {
                //start the application here that is in the config properties.

                System.out.println("start the application here that is in the config properties.");


                Process proc = Runtime.getRuntime().exec("java -jar hkctr_v1.1.jar");
                TimeUnit.SECONDS.sleep(2);
                System.exit(0);

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        downloadService.setOnSucceeded(succeed ->{
            progressBar.progressProperty().bind(zipService.progressProperty());
            progressLabel.textProperty().bind(zipService.messageProperty());
            zipService.start();
        });

    }





}
