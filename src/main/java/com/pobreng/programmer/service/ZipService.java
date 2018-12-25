package com.pobreng.programmer.service;


import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.*;
import java.util.zip.ZipEntry;

import java.util.zip.ZipInputStream;

public class ZipService extends Service<Boolean> {


    @Override
    protected Task<Boolean> createTask() {

        return new Task<Boolean>() {
            private long totalZipSize;

            private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
                FileOutputStream fos = new FileOutputStream(filePath);
                BufferedOutputStream bos = new BufferedOutputStream(fos);

                byte[] bytesIn = new byte[4096];
                int read = 0;
                long totalExtractedSize = 0;
                System.out.println("Extracting downloaded file");
                updateMessage("Extracting downloaded file");

                while ((read = zipIn.read(bytesIn)) != -1) {
                    bos.write(bytesIn, 0, read);
                    totalExtractedSize += read;

                }
                System.out.println("Finish Extracting");
                updateMessage("Finish Extracting");
                updateProgress(100,100);
                bos.close();
            }

            @Override
            protected Boolean call() throws Exception {


                //extract
                try {
                    String zipFilePath = "test/hkctr_v1.1.zip";

                    ZipInputStream zipInput = new ZipInputStream(new FileInputStream(zipFilePath));
                    ZipEntry entry = zipInput.getNextEntry();
                    totalZipSize = entry.getCompressedSize();

                    System.out.println("Extracting downloaded ZIP file - " + totalZipSize);
                    updateMessage("Extracting downloaded ZIP file - " + totalZipSize);

                    while (entry != null) {
                        String filePath = "test/" + entry.getName();

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
                    return true;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return false;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }


            }
        };
    }


}



