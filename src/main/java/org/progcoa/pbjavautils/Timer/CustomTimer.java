package org.progcoa.pbjavautils.Timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("all")
public class CustomTimer {

    private final Timer timer;
    private TimerTask task;
    private Task TimerTask;

    public CustomTimer(){
        timer = new Timer();
    }

    public void run(Task task, int delay){
        setTask(task);

        timer.schedule(this.task, delay);
    }

    public void run(Task task, String src){
        setTask(task);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            timer.schedule(this.task, sdf.parse(src));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void cancel(){
        timer.cancel();
    }

    private void setTask(Task task){
        this.task = new TimerTask() {
            @Override
            public void run() {
                task.runTask();
            }
        };
    }
}
