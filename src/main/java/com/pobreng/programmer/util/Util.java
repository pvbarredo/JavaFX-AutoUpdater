package com.pobreng.programmer.util;


import com.pobreng.programmer.model.Config;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {
    private final static Logger logger = Logger.getLogger(Util.class);
    private static Config config = new Config();

    public static Config getConfig() {
        Properties prop = new Properties();
        InputStream input;
        try {
            input = new FileInputStream("config.properties");
            prop.load(input);

            config.setApplicationName((String) prop.get("APPLICATION_NAME"));

            return config;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }


        return null;
    }


}
