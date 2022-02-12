package org.progcoa.pbjavautils;

import org.progcoa.pbjavautils.Console.Console;
import org.progcoa.pbjavautils.Data.Resource;
import org.progcoa.pbjavautils.Data.configs.YamlObject;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) {
        YamlObject yaml = new YamlObject("test.yml",  Main.class);

        Console.WriteLine(yaml.get("TEST").toString());

        Console.WriteLine(yaml.getMap().toString());

        yaml.set("AA", new Resource(Main.class));

        yaml.Save();
    }
}
