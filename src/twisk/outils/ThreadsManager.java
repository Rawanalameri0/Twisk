package twisk.outils;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class ThreadsManager {
    private static ThreadsManager instance= new ThreadsManager();
    private ArrayList<Thread> threads;
    private ThreadsManager(){
        threads = new ArrayList<>(10);
    }

    public static ThreadsManager getInstance() {
        return instance;
    }

    public void lancer(Task task){
        Thread thread = new Thread(task);
        thread.start();
        threads.add(thread);
    }

    public void detruireTout(){
        for (Thread thread: threads)
            thread.interrupt();
    }
}
