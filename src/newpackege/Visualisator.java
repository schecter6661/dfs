package newpackege;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Visualisator extends JFrame implements Depht {

    private static final long serialVersionUID = -2707712944901661771L;

    private int n; //РєРѕР»РёС‡РµСЃС‚РІРѕ РІРµСЂС€РёРЅ РІ РѕСЂРіСЂР°С„Рµ
    private int m; //РєРѕР»РёС‡РµСЃС‚РІРѕРµ РґСѓРі РІ РѕСЂРіСЂР°С„Рµ
    //  int v;
    private ArrayList<Integer> adj[]; //СЃРїРёСЃРѕРє СЃРјРµР¶РЅРѕСЃС‚Рё
    private boolean used[]; //РјР°СЃСЃРёРІ РґР»СЏ С…СЂР°РЅРµРЅРёСЏ РёРЅС„РѕСЂРјР°С†РёРё Рѕ РїСЂРѕР№РґРµРЅРЅС‹С… Рё РЅРµ РїСЂРѕР№РґРµРЅРЅС‹С… РІРµСЂС€РёРЅР°С…

    private BufferedReader cin;
    private PrintWriter cout;
    private StringTokenizer tokenizer;

    @SuppressWarnings("unused")
    public Visualisator() throws IOException {
        super("Visualisation");
    }

    public void pridumaySama(){

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();

        // readData();
        // int n; // РљРѕР»РёС‡РµСЃС‚РІРѕ РІРµСЂС€РёРЅ
        Object points[] = new Object[n];

        double phi0 = 0;
        double phi = 2 * Math.PI / n;
        int r = 250; // СЂР°РґРёСѓСЃ РѕРєСЂСѓР¶РЅРѕСЃС‚Рё
        for (int i = 0; i < points.length; i++) {
            points[i] = graph.insertVertex(parent, null, i + 1, 300 + r * Math.cos(phi0), 300 + r * Math.sin(phi0), 18, 18, "shape=ellipse");
            phi0 += phi;
        }
// РЈР¶ СЂР°Р·Р±РµСЂРёСЃСЊ СЃ СЌС‚РёРј СЃР°РјР°, Рё РїСЂРѕСЃС‚Рѕ РїРѕРґСѓРјР°Р№, РєР°Рє С‚С‹ С…РѕС‚РµР»Р° СЃРѕРµРґРёРЅСЏС‚СЊ С‚РѕС‡РєРё, РµСЃР»Рё С‚С‹ РёС… РЅРµ РЅР°СЂРёСЃРѕРІР°Р»Р°. Р”Р»СЏ СЌС‚РѕРіРѕ СЃСЂР°РІРЅРё, С‡С‚Рѕ РЅР°РїРёСЃР°РЅРѕ С‚СѓС‚ Рё С‚Рѕ, С‡С‚Рѕ РЅР°РїРёСЃР°РЅРѕ Сѓ С‚РµР±СЏ.
//        for (int i = 0; i < points.length; i++) {
//
//            for (int j = 0; j < points.length; j++) {
//                if (adj[i].get(i) == j) {
//                    graph.insertEdge(parent, null, null, points[i], points[j]);
//
//                }
//
//            }
//        }


        graph.getModel().endUpdate();

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }
    @Override
    public void dfs(int v) {
        //РµСЃР»Рё РІРµСЂС€РёРЅР° СЏРІР»СЏРµС‚СЃСЏ РїСЂРѕР№РґРµРЅРЅРѕР№, С‚Рѕ РЅРµ РїСЂРѕРёР·РІРѕРґРёРј РёР· РЅРµРµ РІС‹Р·РѕРІ РїСЂРѕС†РµРґСѓСЂС‹
        if (used[v]) {
            return;
        }
        used[v] = true; //РїРѕРјРµС‡Р°РµРј РІРµСЂС€РёРЅСѓ РєР°Рє РїСЂРѕР№РґРµРЅРЅСѓСЋ
        cout.print((v + 1) + " ");
        //Р·Р°РїСѓСЃРєР°РµРј РѕР±С…РѕРґ РёР· РІСЃРµС… РІРµСЂС€РёРЅ, СЃРјРµР¶РЅС‹С… СЃ РІРµСЂС€РёРЅРѕР№ v
        for (int i = 0; i < adj[v].size(); ++i) {
            int w = adj[v].get(i);
            dfs(w); //РІС‹Р·РѕРІ РѕР±С…РѕРґР° РІ РіР»СѓР±РёРЅСѓ РѕС‚ РІРµСЂС€РёРЅС‹ w, СЃРјРµР¶РЅРѕР№ СЃ РІРµСЂС€РёРЅРѕР№ v
        }

    }

    @Override
    public void readData() throws IOException {
        cin = new BufferedReader(new InputStreamReader(System.in));
        cout = new PrintWriter(System.out);
        tokenizer = new StringTokenizer(cin.readLine());

        n = Integer.parseInt(tokenizer.nextToken()); //СЃС‡РёС‚С‹РІР°РµРј РєРѕР»РёС‡РµСЃС‚РІРѕ РІРµСЂС€РёРЅ РіСЂР°С„Р°
        m = Integer.parseInt(tokenizer.nextToken()); //СЃС‡РёС‚С‹РІР°РµРј РєРѕР»РёС‡РµСЃС‚РІРѕ СЂРµР±РµСЂ РіСЂР°С„Р°

        //РёРЅРёС†РёР°Р»РёР·РёСЂСѓРµРј СЃРїРёСЃРѕРє СЃРјРµР¶РЅРѕСЃС‚Рё РіСЂР°С„Р° СЂР°Р·РјРµСЂРЅРѕСЃС‚Рё n
        adj = new ArrayList[n];
        used = new boolean[n];

        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<Integer>();
        }

        //СЃС‡РёС‚С‹РІР°РµРј РіСЂР°С„, Р·Р°РґР°РЅРЅС‹Р№ СЃРїРёСЃРєРѕРј СЂРµР±РµСЂ
        for (int i = 0; i < m; ++i) {
            tokenizer = new StringTokenizer(cin.readLine());
            int v = Integer.parseInt(tokenizer.nextToken()); //vertex 1
            int w = Integer.parseInt(tokenizer.nextToken()); //vertex 2
            v--;
            w--;
            adj[v].add(w);
            adj[w].add(v);

        }


    }

    @Override
    public void run() throws IOException {
        readData();
        for (int v = 0; v < n; ++v) {
            dfs(v);
        }
        // visualisation();

    }


    public static void main(String[] args) throws IOException {

        Visualisator frame = new Visualisator();
        frame.run();
        frame.pridumaySama();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setVisible(true);
    }

}

