package newpackege;

import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class Main {

    static int step = 0;
    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame();
        frame.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());

        frame.setLayout(new FlowLayout());

        Algo panel = new Algo();

        JButton btnData = new JButton("Data");
        JButton btnNext = new JButton("Next Step");

        frame.add(btnData);
        frame.add(panel);
        frame.add(btnNext);
        frame.setVisible(true);

        btnNext.setEnabled(false);

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.dfsVisual(step);
                step++;
                if (step == panel.getN()) {
                    btnNext.setEnabled(false);
                }
            }
        });

        btnData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //удаление всего всего всего
                // JFileChooser fileChooser = new JFileChooser();
                // int ret = fileChooser.showDialog(null,"Open file");
                // if (ret == JFileChooser.APPROVE_OPTION) {
                try {
                    panel.run();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                //panel.run(fileChooser.getSelectedFile());
                panel.functionVisual();
                btnNext.setEnabled(true);
            }
            // }
            // }


        });
    }
}
