package twisk.outils;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class ThreadsManager {
    private static ThreadsManager instance= new ThreadsManager();
    private ArrayList<Thread> threads;

    /**
     * Constructeur
     */
    private ThreadsManager(){
        threads = new ArrayList<>(10);
    }

    /**
     * @return l'instance du thread manager
     */
    public static ThreadsManager getInstance() {
        return instance;
    }

    /**
     * lance la simulation
     * @param task
     */
    public void lancer(Task task){
        Thread thread = new Thread(task);
        //thread.start();
        threads.add(thread);
        threads.forEach(Thread::start);
    }

    /**
     * detruit tout les threads
     */
    public void detruireTout(){
        for (Thread thread: threads)
            thread.interrupt();
    }
}
