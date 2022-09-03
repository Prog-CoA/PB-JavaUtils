package org.progcoa.pbjavautils.Data.Files;

import org.progcoa.pbjavautils.Console.Console;
import org.progcoa.pbjavautils.Data.Resource;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

@SuppressWarnings("all")
public class YamlObject {
    private final Yaml yaml = new Yaml();
    private final String path;
    private Map<String, Object> map;
    private Class aClass;
    private Resource resource;

    public YamlObject(String path, Class aClass){
        this.path = path;
        this.aClass = aClass;

        SaveDefault();
        Load();
    }

    public Object get(String key){
        return map.get(key);
    }

    public String getString(String key){
        return (String) get(key);
    }

    public int getInt(String key){
        return (int) get(key);
    }

    public Boolean getBoolean(String key){
        return (Boolean) get(key);
    }

    public void set(String key, Object value){
        map.put(key, value);
    }

    public Map getMap() {
        return map;
    }

    public void Load(){
        map = yaml.loadAs(ClassLoader.getSystemResourceAsStream(path), Map.class);
    }

    public void Save(){
        String yamlData = yaml.dumpAs(map, Tag.YAML, DumperOptions.FlowStyle.BLOCK);

        FileWriter filewriter = null;
        try {
            filewriter = new FileWriter(new File(resource.getOutPath()));
            filewriter.write(yamlData);
            filewriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SaveDefault(){
        resource = new Resource(aClass);
        try {
            resource.loadFile(path);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
