package newpackege;

import javax.swing.*;
import java.io.IOException;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import javafx.util.Pair;
import java.lang.*;
public class MainApp  {

    public static void main(String[] args) throws IOException

    {
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        Visualisator panel = new Visualisator();
        try {
            panel.run();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        try {
            panel.functionVisual();
        } catch (RuntimeException e1) {
            System.out.println(e1.getMessage());
        }


        panel.dfsVisual();
        frame.add(panel);
        frame.setVisible(true);
    }
}