package za.co.junghans.hszt.semin.ir;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import za.co.junghans.hszt.semin.ir.analyzer.AnalyzerResult;
import za.co.junghans.hszt.semin.ir.analyzer.KwAnalyzer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class AnalyzeActionListener implements ActionListener {

    private Logger log = Logger.getLogger(KeywordAnalyzer.class);
    private AnalyzeGui form;
    private KwAnalyzer analyzer;

    public AnalyzeActionListener(KwAnalyzer analyzer) {
        this.form = AnalyzeGui.getInstance();
        this.analyzer = analyzer;
    }

     @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String parsedHtml = "";
        AnalyzerResult result;
        List<String> keywords;

        try {
            parsedHtml= Util.loadAndParsePage();
            log.debug(parsedHtml);
        } catch (IOException e) {
            log.error(e);
            form.log("Error parsing page: " + e.getMessage());
        }

        keywords = Util.getKeywordList();
        if(keywords.isEmpty() || StringUtils.isEmpty(keywords.get(0))) {
            form.log("Keywords missing, please supply at least one keyword");
        }

        form.log("Starting...");
        result = analyzer.analyze(parsedHtml, keywords);
        form.log("Done!");
        form.setFoundKeywords(result.keywordsFound);
        form.setCoverage(result.coverage.toPlainString() + "%" + " or " + (result.keywordsSize - result.keywordsMissingSize) + "/" + result.keywordsSize);
        form.setMissingKeywords(result.keywordsMissing);
        form.setTotalKeywords(Integer.toString(result.totalKeywords));
        form.setUniqueKeywords(Integer.toString(result.uniqueKeywords));
    }

}
