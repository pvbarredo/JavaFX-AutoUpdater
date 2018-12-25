package com.pobreng.programmer.controller;

import com.pobreng.programmer.service.DownloadService;
import com.pobreng.programmer.service.ZipService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.net.URLConnection;

public class MainWindowController {

    @FXML
    ProgressBar progressBar;

    @FXML
    Label progressLabel;


    public void initialize() {


        //downloading

//        this.progressBar.setProgress(0);
//        this.progressLabel.setText("Checking internet connection ...");
//        if(hasInternet()){
//            this.progressBar.setProgress(1);
//            this.progressLabel.setText("Internet connection available ...");
//        }else{
//            this.progressLabel.setText("No internet connection!");
//        }

        DownloadService downloadService = new DownloadService();
        progressBar.progressProperty().bind(downloadService.progressProperty());

        progressLabel.textProperty().bind(downloadService.messageProperty());

        downloadService.start();
//        new ZipService(progressBar, progressLabel);

    }

    public void setProgressBarLabel(String message){
        progressLabel.setText(message);

    }
    public void setProgressBar(double percentage){
        progressBar.setProgress(percentage);
    }




}
