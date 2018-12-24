package com.pobreng.programmer.service;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipService {
    private long totalZipSize ;
    private ProgressBar progressBar;
    private Label progressLabel;

    public ZipService(ProgressBar progressBar , Label progressLabel ) {

        this.progressBar = progressBar;
        this.progressBar.setProgress(0);
        this.progressLabel = progressLabel;
        unzip();
    }

    private void unzip(){
        try {
            String zipFilePath = "test/hkctr_v1.1.zip";

            ZipInputStream zipInput = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry entry = zipInput.getNextEntry();
            totalZipSize = entry.getCompressedSize();

            System.out.println("Extracting downloaded ZIP file - " + totalZipSize);
            progressLabel.setText("Extracting downloaded ZIP file - " + totalZipSize);

            while (entry != null) {
                String filePath = "test/"+ entry.getName();

                if (!entry.isDirectory()) {

                    extractFile(zipInput, filePath);
                } else {

                    File dir = new File(filePath);
                    dir.mkdir();
                }
                zipInput.closeEntry();
                entry = zipInput.getNextEntry();
            }
            zipInput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        byte[] bytesIn = new byte[4096];
        int read = 0;
        long totalExtractedSize = 0;
        System.out.println( "Extracting downloaded file");
        progressLabel.setText( "Extracting downloaded file");
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
            totalExtractedSize += read;

        }
        System.out.println( "Finish Extracting");
        progressLabel.setText( "Finish Extracting");
        this.progressBar.setProgress(1);
        bos.close();
    }
    private void clean(){

    }

    private void copy(){

    }
}
