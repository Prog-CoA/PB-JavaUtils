package org.progcoa.pbjavautils.KeyHook;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.LogManager;

@SuppressWarnings("all")
public class KeyHook implements NativeKeyListener {
    private KeyBoardEvent event;

    public KeyHook(KeyBoardEvent event) {
        if (!GlobalScreen.isNativeHookRegistered()) {
            try {
                GlobalScreen.registerNativeHook();
            } catch (NativeHookException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        LogManager.getLogManager().reset();
        this.event = event;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        event.onPressed(e);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        event.onReleased(nativeKeyEvent);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        event.onTyped(nativeKeyEvent);
    }
}
