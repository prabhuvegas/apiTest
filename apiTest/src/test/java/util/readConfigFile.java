package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class readConfigFile {
    public static Properties readPropertyFile() throws Exception {

        try{

            File file = new File("src/test/resources/config/apiTest.properties");

            FileInputStream fileInput = null;
            try {
                fileInput = new FileInputStream(file.getAbsolutePath());
                //System.out.println("Application Properties File Path : "+file.getAbsolutePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Properties prop = new Properties(System.getProperties());

            //load properties file
            try {
                prop.load(fileInput);
                System.setProperties(prop);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prop;
        }catch(Exception e){
            return null;
        }
    }
}
