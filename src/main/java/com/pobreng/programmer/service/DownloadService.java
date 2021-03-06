package com.pobreng.programmer.service;

import com.pobreng.programmer.model.Config;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DownloadService extends Service<Boolean> {

    private Config config;


//manipulate the URL first
    //download

    public DownloadService() {




    }

    public void download(){
        //https://github.com/pvbarredo/JavaFX-HKCTR/releases/download/1.1/hkctr_v1.1.jar
        //you can never go down if version 1 ung nakalagay
        //main program ung source ng version tapos increase lang ng isa
        //pag wala check din kung may major change 2.0
        //need din replace ung download ng update sa labas para maging updated din ung downloader

    //need ata ng interface para iba gagawin kapag sa progress . gawin lang muna ung mag update ung UI
        try {

            URL url = new URL(generateURL());
            long totalFileSize = getFileSize(url);

            System.out.println("Downloading total file size : " + totalFileSize);


            BufferedInputStream inputStream = new BufferedInputStream(url.openStream());

            FileOutputStream outputStream = new FileOutputStream("hkctr_v1.1.jar");

            byte dataBuffer[] = new byte[1024];

            int bytesRead;
            long totalDownloadedSize = 0;
            while((bytesRead = inputStream.read(dataBuffer,0,1024)) != -1 ){
                outputStream.write(dataBuffer,0,bytesRead);
                totalDownloadedSize += bytesRead;
                System.out.println( "Download percentage : " + computePercentage(totalFileSize, totalDownloadedSize) + "%") ;


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private double computePercentage(long totalFileSize, long totalDownloadedSize) {
        return (totalDownloadedSize * 100) /totalFileSize;
    }

    private  int getFileSize(URL url) {
        URLConnection conn = null;
        try {
            conn = url.openConnection();
            return Integer.parseInt(conn.getHeaderField("Content-Length"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(conn instanceof HttpURLConnection) {
                ((HttpURLConnection)conn).disconnect();
            }
        }
    }

    private  String generateURL() {

//        String githubUsername = config.getGithubUsername();
//        String githubRepository = config.getGithubRepositoryName();
//        String applicationName = config.getApplicationName();
        String nextVersion = "1.1";


        StringBuilder sb = new StringBuilder("https://github.com/");
//        sb.append(githubUsername + "/");
//        sb.append(githubRepository +"/");

        sb.append("pvbarredo/");
        sb.append("JavaFX-HKCTR/");

        sb.append("releases/download/");
        sb.append(nextVersion);
//        sb.append("/" + applicationName + "_" + nextVersion + ".zip");
        sb.append("/hkctr_v" + nextVersion + ".jar");

        return sb.toString();
    }

    public boolean hasInternet(){

        try {
            URL url = new URL("https://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }

    @Override
    protected Task<Boolean> createTask() {

        return new Task<Boolean>(){

            @Override
            protected Boolean call() throws Exception {
                try {
                updateMessage("Checking internet connection");
                updateProgress(0,100);
                System.out.println("Checking internet connection ...");
                if(hasInternet()){
                    System.out.println("Internet connection available ...");
                    updateProgress(100,100);
                    updateMessage("Internet connection available ...");
                }else{

                    System.out.println("No internet connection available ...");
                    updateMessage("No Internet connection available ...");
                }


                //---Download


                    URL url = new URL(generateURL());
                    long totalFileSize = getFileSize(url);

                    System.out.println("Downloading total file size : " + totalFileSize);
                    updateMessage("Downloading total file size : " + totalFileSize);
                    updateProgress(0,100);
                    BufferedInputStream inputStream = new BufferedInputStream(url.openStream());

                    FileOutputStream outputStream = new FileOutputStream("hkctr_v1.1.jar");

                    byte dataBuffer[] = new byte[1024];

                    int bytesRead;
                    long totalDownloadedSize = 0;
                    while((bytesRead = inputStream.read(dataBuffer,0,1024)) != -1 ){
                        outputStream.write(dataBuffer,0,bytesRead);
                        totalDownloadedSize += bytesRead;
                        System.out.println( "Download percentage : " + computePercentage(totalFileSize, totalDownloadedSize) + "%") ;
                        updateMessage("Download percentage : " + computePercentage(totalFileSize, totalDownloadedSize) + "%");
                        updateProgress( computePercentage(totalFileSize, totalDownloadedSize),100);

                    }


//---Download

                updateMessage("Finish downloading ...");
                updateProgress(100,100);
                return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }

            }
        };
    }
}
