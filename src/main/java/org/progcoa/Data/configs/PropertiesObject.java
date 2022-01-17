package org.progcoa.Data.configs;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SuppressWarnings("all")
public class PropertiesObject {

    private final String path;
    private final Properties properties = new Properties();

    public PropertiesObject(){
        this("data.properties");
    }

    public PropertiesObject(String path){
        this.path = path;
        load();
    }

    public void setData(String key, String value){
        properties.setProperty(key, value);
    }

    public String getData(String key){
        return properties.getProperty(key);
    }

    public void save(){
        try {
            OutputStream stream = new FileOutputStream(path);
            OutputStreamWriter osw = new OutputStreamWriter(stream, StandardCharsets.UTF_8);
            properties.store(osw, "Properties File");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(){
        if(!(new File(path).exists())) save();

        try {
            InputStream stream = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(stream, StandardCharsets.UTF_8);
            properties.load(isr);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
