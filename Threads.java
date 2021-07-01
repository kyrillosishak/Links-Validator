package HTMLLinks;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ahmed
 */      
public class Threads extends Thread{
    
    private String link;
    private int depth;
    private int maxDepth;
    private int threads;
    private String text;
    
    public Threads(String link, int depth, int maxDepth,int threads,String text) {
        this.link = link;
        this.depth = depth;
        this.maxDepth = maxDepth;
        this.threads = threads;
        this.text = text;
    }
    
    @Override
    public void run() {
        try {
            Validation.validateURL(link, depth, maxDepth,threads,text);
        } catch (IOException ex) {
            System.err.println("Problem in Threads");
        } catch (InterruptedException ex) {
            System.err.println("Problem in Threads");
        }
    }
}