package com.pobreng.programmer.service;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipService {
    private long totalZipSize ;

    public ZipService( ) {
      unzip();
    }

    private void unzip(){
        try {
            String zipFilePath = "test/hkctr_v1.1.zip";

            ZipInputStream zipInput = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry entry = zipInput.getNextEntry();
            totalZipSize = entry.getCompressedSize();
            System.out.println("Extracting downloaded ZIP file - " + totalZipSize);
            // iterates over entries in the zip file
            while (entry != null) {
                String filePath = "test/"+ entry.getName();
                System.out.println(filePath);
                if (!entry.isDirectory()) {
                    // if the entry is a file, extracts it
                    extractFile(zipInput, filePath);
                } else {
                    // if the entry is a directory, make the directory
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
        System.out.println( "Extracting file");
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
            totalExtractedSize += read;

        }
        System.out.println( "Finish Extracting");
        bos.close();
    }
    private void clean(){

    }

    private void copy(){

    }
}
