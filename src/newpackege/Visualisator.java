package newpackege;

import javax.swing.*;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import javafx.util.Pair;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Visualisator extends JPanel {

    protected static int n; //количество вершин в графе
    protected static int m; //количество дуг в графе
    protected ArrayList<ArrayList<Integer>> adj; //список смежности
    protected boolean used[]; //массив для хранения информации о пройденных и не пройденных вершинах
    protected ArrayList<Integer> listDepht;
    protected ArrayList<Pair<Integer, Integer>> listEdges;

    protected PrintWriter cout;


    @SuppressWarnings("unused")
    public Visualisator() throws IOException {
        this.setSize(500, 500);
        listDepht = new ArrayList<>();
        listEdges = new ArrayList<>();
    }

    public void functionVisual() {
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        Object points[] = new Object[n];
        double phi0 = 0;
        double phi = 2 * Math.PI / n;
        int r = 250; // радиус окружности
        for (int i = 0; i < points.length; i++) {
            points[i] = graph.insertVertex(parent, null, i + 1, 300 + r * Math.cos(phi0), 300 + r * Math.sin(phi0), 18, 18, "shape=ellipse");
            phi0 += phi;
        }
        for (int i = 0; i < adj.size(); i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                graph.insertEdge(parent, null, null, points[i], points[adj.get(i).get(j)]);
            }
        }
        graph.getModel().endUpdate();
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        this.add(graphComponent);
        this.revalidate();
    }


    public void dfsVisual() {
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        Object points[] = new Object[n];
        double phi0 = 0;
        double phi = 2 * Math.PI / n;
        int r = 250; // радиус окружности
        for (int i = 0; i < points.length; i++) {
            points[i] = graph.insertVertex(parent, null, i + 1, 300 + r * Math.cos(phi0), 300 + r * Math.sin(phi0), 18, 18, "shape=ellipse");
            phi0 += phi;
        }
        for (int i = 0; i < listEdges.size(); i++) {
            graph.insertEdge(parent, null, null, points[listEdges.get(i).getKey()], points[listEdges.get(i).getValue()]);
        }
        graph.getModel().endUpdate();
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        this.add(graphComponent);
        this.revalidate();
    }


    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        Algo panel = new Algo();
        try {
            panel.run();
            panel.functionVisual();
            panel.dfsVisual();
            frame.add(panel);
            frame.setVisible(true);
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
    }
}
