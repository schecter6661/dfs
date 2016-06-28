package newpackege;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Visualisator extends JFrame implements Depht {

    private int n; //количество вершин в орграфе
    private int m; //количествое дуг в орграфе
    private  ArrayList<Integer> adj[]; //список смежности
    private boolean used[]; //массив для хранения информации о пройденных и не пройденных вершинах

    private BufferedReader cin;
    private static PrintWriter cout;
    private StringTokenizer tokenizer;

    @SuppressWarnings("unused")
    public Visualisator() throws IOException {
        super("Visualisation");

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();

        int n = 0; // Количество вершин
        Object points[] = new Object[n];

        double phi0 = 0;
        double phi = 2 * Math.PI / n;
        int r = 250; // радиус окружности

        for (int i = 0; i < points.length; i++) {
            points[i] = graph.insertVertex(parent, null, i + 1, 300 + r * Math.cos(phi0), 300 + r * Math.sin(phi0), 18, 18, "shape=ellipse");
            phi0 += phi;
            for (int j = 0; j < points.length; j++) {
                if (adj[i].get(i) == j) {
                    graph.insertEdge(parent, null, "", i, j);

                }

            }

        }


        graph.getModel().endUpdate();

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    //@Override
   public void dfs(int v) {
       //если вершина является пройденной, то не производим из нее вызов процедуры

       if (used[v]) {
           return;
       }
       used[v] = true; //помечаем вершину как пройденную
       cout.print((v + 1) + " ");
       //запускаем обход из всех вершин, смежных с вершиной v
       for (int i = 0; i < adj[v].size(); ++i) {
           int w = adj[v].get(i);
           dfs(w); //вызов обхода в глубину от вершины w, смежной с вершиной v
       }
   }


    //@Override
    public void readData() throws IOException {
        System.out.print("Enter number of vertices and edges: ");
        cin = new BufferedReader(new InputStreamReader(System.in));
        cout = new PrintWriter(System.out);
        tokenizer = new StringTokenizer(cin.readLine());
        n = Integer.parseInt(tokenizer.nextToken()); //считываем количество вершин графа
        m = Integer.parseInt(tokenizer.nextToken()); //считываем количество ребер графа
        System.out.print("Enter vertices: ");
        //инициализируем список смежности графа размерности n
        adj = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<Integer>();
        }

        //считываем граф, заданный списком ребер
        for (int i = 0; i < m; ++i) {
            tokenizer = new StringTokenizer(cin.readLine());
            int v = 0, w = 0;
            try {
                v = Integer.parseInt(tokenizer.nextToken());
                w = Integer.parseInt(tokenizer.nextToken());
            } catch (NoSuchElementException ex1) {
                System.out.println(ex1.getMessage());
            }
            v--;
            w--;

            try {
                adj[v].add(w);
                adj[w].add(v);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println(ex.getMessage());
            }

        }

        used = new boolean[n];
        Arrays.fill(used, false);
    }

    //@Override
    public void run() throws IOException {
        readData();
        for (int v = 0; v < n; ++v) {
            dfs(v);
        }
       // visualisation();

    }
    // cin.close();
    // cout.close();


    public static void main(String[] args) throws IOException {
        Visualisator visualisator = new Visualisator();
        visualisator.run();
        Visualisator frame = new Visualisator();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // frame.pack();
         frame.setSize(700, 700);
        frame.setVisible(true);
    }

  //  public void visualisation() {

   // }
}

