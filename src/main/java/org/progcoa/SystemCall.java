package org.progcoa;

import java.io.IOException;

@SuppressWarnings("all")
public class SystemCall {

    private Process process;
    private final Runtime runtime;
    private final String command;

    public SystemCall(String Command){
        this.runtime = Runtime.getRuntime();
        this.command = Command;
    }

    public void Run() {
        try {
            process = runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Exit(){
        if(process != null) process.destroy();
    }
}
