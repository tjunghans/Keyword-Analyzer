package za.co.junghans.hszt.semin.ir;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * User: dkummer
 * Date: 30.06.11
 * Time: 18:49
 */
public class Util {

    public static String loadAndParsePage() throws IOException {
        String url = Util.getTargetUrl();
        Document website = Jsoup.connect(url).get();

        StringBuffer resultBuffer = new StringBuffer();
        resultBuffer.append(website.title());
        resultBuffer.append(website.body().text());

        return resultBuffer.toString();
    }

    public static List<String> getKeywordList() {
        return Arrays.asList(AnalyzeGui.getInstance().getKeywords().split(","));
    }

    public static String getTargetUrl() {
        return formatTargetUrl(AnalyzeGui.getInstance().getUrl());
    }

    public static String formatTargetUrl(String url) {
        url = url.toLowerCase();
        if (!StringUtils.startsWith(url, "http")) {
            url = "http://" + url;
        }
        return url;
       }

}
