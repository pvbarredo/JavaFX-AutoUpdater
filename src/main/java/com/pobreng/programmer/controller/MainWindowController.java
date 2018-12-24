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


    public void startProcess() {


        //downloading
        new DownloadService(progressBar, progressLabel);
        new ZipService(progressBar, progressLabel);

    }

    public void setProgressBarLabel(String message){
        progressLabel.setText(message);

    }
    public void setProgressBar(double percentage){
        progressBar.setProgress(percentage);
    }




}
