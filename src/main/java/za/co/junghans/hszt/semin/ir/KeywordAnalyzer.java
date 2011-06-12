package za.co.junghans.hszt.semin.ir;

import javax.swing.*;
import java.awt.*;

/**
 * Hello world!
 */
public class KeywordAnalyzer {
    public static void main(String[] args) {
        showUI();
    }

    private static void showUI() {
        JFrame frame = new JFrame("Keyword Analyzer");
        frame.setPreferredSize(new Dimension(600, 500));
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new AnalyzerGui().getForm());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
