package za.co.junghans.hszt.semin.ir;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import za.co.junghans.hszt.semin.ir.analyzer.KwAnalyzer;
import za.co.junghans.hszt.semin.ir.analyzer.LuceneKwAnalyzer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
public class KeywordAnalyzer implements ActionListener {

    private Logger log = Logger.getLogger(KeywordAnalyzer.class);
    AnalyzeGui form;
    KwAnalyzer analyzer;


    public static void main(String[] args) {
        KeywordAnalyzer ka = new KeywordAnalyzer();
        ka.setupFrame();
    }

    public KeywordAnalyzer() {
        form = new AnalyzeGui(this);
        analyzer = new LuceneKwAnalyzer();
    }

    private void setupFrame() {
        JFrame frame = new JFrame("Keyword KwAnalyzer");
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setLocationRelativeTo(null);
        frame.setContentPane(form.$$$getRootComponent$$$());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String parsedHtml = "";
        String result;
        List<String> keywords;

        try {
            parsedHtml= loadAndParsePage(getTargetUrl());
        } catch (IOException e) {
            log.error(e);
            form.setStatus("Error parsing target page: " + e.getMessage());
        }

        keywords = getKeywordList();
        if(keywords.isEmpty()) {
            form.setStatus("Please supply at least one keyword");
        }

        log.debug(parsedHtml.toString());

        result = analyzer.analyze(parsedHtml, keywords);
        form.setResult(result);
    }

    private String loadAndParsePage(String Url) throws IOException {
        Document website = Jsoup.connect(Url).get();

        StringBuffer resultBuffer = new StringBuffer();
        resultBuffer.append(website.title());
        resultBuffer.append(website.body().text());

        return resultBuffer.toString();
    }

    private List<String> getKeywordList() {
        return Arrays.asList(form.getKeywords().split(","));
    }

    private String getTargetUrl() {
        return formatTargetUrl(form.getUrl());
    }

    private String formatTargetUrl(String url) {
        url = url.toLowerCase();
        if (!StringUtils.startsWith(url, "http")) {
            url = "http://" + url;
        }
        return url;
    }
}
