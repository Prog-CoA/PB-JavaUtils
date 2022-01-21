package org.progcoa.pbjavautils.Console;

import java.util.Scanner;

@SuppressWarnings("all")
public class Console {

    public static String ReadLine(){
        return new Scanner(System.in).nextLine();
    }

    public static void WriteLine(String message){
        System.out.println(message);
    }
}
