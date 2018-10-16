package com.study.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author alic
 * @ClassName: StdAnalyzer
 * @Description: TDD
 * @date 2018/8/8 22:51
 * @Version 1.0
 */
public class StdAnalyzer {

    private static String strCh = "中华人民共和国简称中国，是一个有13亿人口的国家";

    public static void main(String[] args) {
        try {
            stdAnalyzer(strCh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stdAnalyzer(String str) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        StringReader reader = new StringReader(str);
        TokenStream stream = analyzer.tokenStream(str, str);
        stream.reset();
        CharTermAttribute attribute = stream.getAttribute(CharTermAttribute.class);
        while(stream.incrementToken()){
            System.out.print(attribute.toString() + "|");
        }
        System.out.println("\n");
        analyzer.close();
    }
}
