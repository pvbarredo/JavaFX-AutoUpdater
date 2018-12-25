package com.pobreng.programmer.controller;

import com.pobreng.programmer.service.DownloadService;
import com.pobreng.programmer.service.ZipService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;


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

        downloadService.setOnSucceeded(succeed ->{
            System.out.println("SUCCEEDED!");
            ZipService zipService = new ZipService();
            progressBar.progressProperty().bind(zipService.progressProperty());
            progressLabel.textProperty().bind(zipService.messageProperty());
            zipService.start();
        });

    }





}
