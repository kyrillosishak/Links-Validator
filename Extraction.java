/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HTMLLinks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author ahmed
 */
public class Extraction {  

    public static Link[] extract(String link) {//ex
        Document doc = null;
        try {
            doc = Jsoup.connect(link).get(); //ex
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, "problem in Extraction");
        }
        Elements e = doc.select("a[href]"); // extract only links
        URL u = null;
        try {
            u = new URL(link);
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, "problem in Extraction");
        }
        String LINK = u.getProtocol() + "://" + u.getHost();
        Link arr [] = new Link[e.size()];
        
        for (int i = 0; i < e.size(); i++) {
            String x = e.get(i).attr("href");
            String t = e.get(i).text();
           if (!x.startsWith("http")) {
                x = LINK + x;
                arr[i].setLINK (x);
            }
            else{
                arr[i].setLINK (x);
            }
               arr[i].setTEXT(t);
        }
        return arr;
    }

    

}
