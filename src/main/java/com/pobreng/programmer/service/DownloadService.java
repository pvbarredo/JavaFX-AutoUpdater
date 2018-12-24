package com.pobreng.programmer.service;

import com.pobreng.programmer.model.Config;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DownloadService {

    private Config config;

    public DownloadService() {
    }

    public static void main(String[] args) {
//        download();
        new ZipService();
    }

//manipulate the URL first
    //download
    public static void download(){
        //https://github.com/pvbarredo/JavaFX-HKCTR/releases/download/1.1/hkctr_v1.1.jar
        //you can never go down if version 1 ung nakalagay
        //main program ung source ng version tapos increase lang ng isa
        //pag wala check din kung may major change 2.0
        //need din replace ung download ng update sa labas para maging updated din ung downloader


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
                System.out.println( "Download percentage : " + (totalDownloadedSize * 100) /totalFileSize + "%") ;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int getFileSize(URL url) {
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

    private static String generateURL() {

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
}
