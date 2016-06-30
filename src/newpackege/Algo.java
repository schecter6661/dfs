package newpackege;
import javafx.util.Pair;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class Algo extends Visualisator{

    public Algo() throws IOException {
    }
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
    public void run() throws IOException {
        readData();
        for (int v = 0; v < n; ++v) {
            if (!used[v]) {
                dfs(v);
            }
        }
    }
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
}