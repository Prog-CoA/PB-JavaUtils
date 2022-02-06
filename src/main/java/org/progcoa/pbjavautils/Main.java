package org.progcoa.pbjavautils;

import org.progcoa.pbjavautils.Data.Resource;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Resource.loadFile("test.txt");
        Resource.loadDirectory("TEST");
    }
}
