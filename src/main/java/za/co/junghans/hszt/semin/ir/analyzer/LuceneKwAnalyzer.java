package za.co.junghans.hszt.semin.ir.analyzer;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.de.GermanAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LuceneKwAnalyzer implements KwAnalyzer {

    //private Analyzer analyzer;
	private GermanAnalyzer analyzer;
    private String fieldName;
    
    

    private static final Logger log = Logger.getLogger(LuceneKwAnalyzer.class.getName());

    public LuceneKwAnalyzer() {
        analyzer = new GermanAnalyzer(Version.LUCENE_32);
        fieldName = "fieldName";
    }

    
    public AnalyzerResult analyze(String input, List<String> keywords) {
        //todo incorporate keyword in lucene search

        StringBuffer resultBuffer = new StringBuffer();
        Set<String> keywordSet = new HashSet<String>();
        List<String> missingKeywords = new ArrayList<String>();
        AnalyzerResult analyzerResult = new AnalyzerResult();

        Reader inputReader = new StringReader(input);
        TokenStream tokenStream = analyzer.tokenStream(fieldName, inputReader);
        CharTermAttribute terms = tokenStream.addAttribute(CharTermAttribute.class);
        OffsetAttribute offsets = tokenStream.addAttribute(OffsetAttribute.class);
        PositionIncrementAttribute positions = tokenStream.addAttribute(PositionIncrementAttribute.class);

        resultBuffer.append(String.format("\n%5s (%5s, %5s) %s\n",  "INCR","START","END","TERM"));
        /*x LuceneAnalysis.3 */
        int i = 0;
        try {
            while (tokenStream.incrementToken()) {
                int increment = positions.getPositionIncrement();
                int start = offsets.startOffset();
                int end = offsets.endOffset();
                String term = terms.toString();
                
                keywordSet.add(term.trim().toLowerCase());
                
                /*x*/
                resultBuffer.append(String.format("%5d (%5d, %5d) %s\n",increment,start,end,term));
                
                i += 1;
            }
                     
            	
        	for(String keyword : keywords) {
        	
        		if(!keywordSet.contains(keyword.toLowerCase())) {
        			missingKeywords.add(keyword);
        		}
        	}

        	// Coverage as percent rounded to two decimals
            BigDecimal coverage = new BigDecimal(((double)(keywords.size() - missingKeywords.size())/keywords.size() * 100));
            analyzerResult.coverage = coverage.setScale(2,BigDecimal.ROUND_HALF_UP);
            analyzerResult.keywordsMissing = missingKeywords.toString();
            analyzerResult.totalKeywords = i;
            analyzerResult.uniqueKeywords = keywordSet.size();
            analyzerResult.keywordsMissingSize = missingKeywords.size();
            analyzerResult.keywordsSize = keywords.size();
            
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        analyzerResult.keywordsFound = resultBuffer.toString();

        return analyzerResult;
       
    }
}
