package za.co.junghans.hszt.semin.ir;

import za.co.junghans.hszt.semin.ir.analyzer.KwAnalyzer;
import za.co.junghans.hszt.semin.ir.analyzer.LuceneKwAnalyzer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class KeywordAnalyzer {


    private AnalyzeGui form;
    private KwAnalyzer analyzer;
    private ActionListener analyzeActionListener;
    private ActionListener clipboardActionListener;

    private KeywordAnalyzer() {
        analyzer = new LuceneKwAnalyzer();
        analyzeActionListener = new AnalyzeActionListener(analyzer);
        clipboardActionListener = new ClipboardActionListener();
        form = AnalyzeGui.getInstance();
        form.setActionListeners(analyzeActionListener, clipboardActionListener);
    }

    private void setupFrame() {
        JFrame frame = new JFrame("Keyword KwAnalyzer");
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setLocationRelativeTo(null);
        frame.setContentPane(form.$$$getRootComponent$$$());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        KeywordAnalyzer ka = new KeywordAnalyzer();
        ka.setupFrame();
    }

}
