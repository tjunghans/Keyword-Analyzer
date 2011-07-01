package za.co.junghans.hszt.semin.ir.analyzer;

import java.util.List;

public interface KwAnalyzer {
	AnalyzerResult analyze(String input, List<String> keywords);
}
