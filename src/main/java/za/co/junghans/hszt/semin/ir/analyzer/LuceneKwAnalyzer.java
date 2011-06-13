package za.co.junghans.hszt.semin.ir.analyzer;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class LuceneKwAnalyzer implements KwAnalyzer {

    private Analyzer analyzer;
    private String fieldName;

    private static final Logger log = Logger.getLogger(LuceneKwAnalyzer.class.getName());

    public LuceneKwAnalyzer() {
        analyzer = new StandardAnalyzer(Version.LUCENE_32);
        fieldName = "fieldName";
    }

    @Override
    public String analyze(String input, List<String> keywords) {

        StringBuffer resultBuffer = new StringBuffer();

        Reader inputReader = new StringReader(input);
        TokenStream tokenStream = analyzer.tokenStream(fieldName, inputReader);
        CharTermAttribute terms = tokenStream.addAttribute(CharTermAttribute.class);
        OffsetAttribute offsets = tokenStream.addAttribute(OffsetAttribute.class);
        PositionIncrementAttribute positions = tokenStream.addAttribute(PositionIncrementAttribute.class);


        resultBuffer.append(String.format("\n%5s (%5s, %5s) %s\n",  "INCR","START","END","TERM"));
        /*x LuceneAnalysis.3 */
        try {
            while (tokenStream.incrementToken()) {
                int increment = positions.getPositionIncrement();
                int start = offsets.startOffset();
                int end = offsets.endOffset();
                String term = terms.toString();
            /*x*/
                resultBuffer.append(String.format("%5d (%5d, %5d) %s\n",increment,start,end,term));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }


        return resultBuffer.toString();
    }
}
