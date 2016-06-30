package newpackege;

import javax.swing.*;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import javafx.util.Pair;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Visualisator extends JPanel implements Depht {

    private int n; //количество вершин в графе
    private int m; //количество дуг в графе
    private ArrayList<ArrayList<Integer>> adj; //список смежности
    private boolean used[]; //массив для хранения информации о пройденных и не пройденных вершинах
    private ArrayList<Integer> listDepht;
    private ArrayList<Pair<Integer, Integer>> listEdges;

    private PrintWriter cout;

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

    @Override
    public void dfs(int v) {
        //если вершина является пройденной, то не производим из нее вызов процедуры
        used[v] = true; //помечаем вершину как пройденную
        listDepht.add(v + 1);
        //запускаем обход из всех вершин, смежных с вершиной v
        for (int i = 0; i < adj.get(v).size(); ++i) {
            int w = adj.get(v).get(i);
            if (!used[w]) {
                listEdges.add(new Pair(v, w));
                dfs(w); //вызов обхода в глубину от вершины w, смежной с вершиной v
            }
        }
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

    @Override
    public void readData() throws IOException {
        System.out.print("Enter number of vertices and edges: ");
        Scanner cin = new Scanner(System.in);
        cout = new PrintWriter(System.out);

        n = cin.nextInt(); //считываем количество вершин графа
        m = cin.nextInt(); //считываем количество ребер графа
        System.out.print("Enter vertices:\n");
        //инициализируем список смежности графа размерности n

        adj = new ArrayList<ArrayList<Integer>>(n);
        used = new boolean[n];

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        //считываем граф, заданный списком ребер
        for (int i = 0; i < m; i++) {
            int v = cin.nextInt(); //vertex 1
            int w = cin.nextInt(); //vertex 2
            v--;
            w--;
            adj.get(v).add(w);
            adj.get(w).add(v);
        }
    }

    @Override
    public void run() throws IOException {
        readData();
        for (int v = 0; v < n; ++v) {
            if (!used[v]) {
                dfs(v);
            }
        }
    }


    public static void main(String[] args) throws IOException {
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

