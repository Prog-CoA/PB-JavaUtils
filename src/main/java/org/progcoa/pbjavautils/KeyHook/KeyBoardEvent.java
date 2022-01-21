package org.progcoa.pbjavautils.KeyHook;

import org.jnativehook.keyboard.NativeKeyEvent;

@SuppressWarnings("all")
public interface KeyBoardEvent {
    public void onPressed(NativeKeyEvent e);
    public void onReleased(NativeKeyEvent nativeKeyEvent);
    public void onTyped(NativeKeyEvent nativeKeyEvent);
}
