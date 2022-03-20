package org.progcoa.pbjavautils.Data.Files;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class DatObject implements Serializable {

    private Map<String, Object> map = new HashMap<>();
    private String path;
    protected Object object;

    public DatObject(){
        this("data.dat");
    }

    public DatObject(String path){
        this.path = path;

        load();
    }

    public void setData(String key, Object value){
        map.put(key, value);
    }

    public Object getData(String key){
        return map.get(key);
    }

    public void setMap(Map<String, Object> map){
        this.map = map;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void saveMap(){
        Save(map, path);
    }

    public void loadMap(){
        if(Load(path) != null) this.map = (Map<String, Object>) object;
    }

    public void load(){
        if(Load(path) != null) object = Load(path);
    }

    public void save(){
        Save(object, path);
    }

    private void Save(Object data, String path){
        try(FileOutputStream f = new FileOutputStream(path);
            BufferedOutputStream b = new BufferedOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(b)){

            out.writeObject(data);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    private Object Load(String path){
        File file = new File(path);

        if(!(file.exists())) Save(null, path);

        try(FileInputStream f = new FileInputStream(path);
            BufferedInputStream b = new BufferedInputStream(f);
            ObjectInputStream in = new ObjectInputStream(b)){

            return in.readObject();
        } catch ( IOException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
        return null;
    }
}
