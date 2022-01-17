package org.progcoa;

import org.jnativehook.GlobalScreen;
import org.progcoa.KeyHook.KeyHook;
import org.progcoa.debug.Data;

public class Main {
    public static void main(String[] args) {
        GlobalScreen.addNativeKeyListener(new KeyHook(new Data()));
    }
}
