package com.study.analyzer;

import com.study.ik.IKAnalyzer6x;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author alic
 * @ClassName: IkVSSmartch
 * @Description: TDD
 * @date 2018/8/9 0:23
 * @Version 1.0
 */
public class IkVSSmartch {

    private static String str1 = "公安局正在治理解放大道路面积水问题,厉害了我的哥skrr";

    private static String str2 = "IKAnalyzer是一个开源的，基于java语言开发的轻量级的中文分词工具包";


    public static void main(String[] args) {
        Analyzer analyzer = new IKAnalyzer6x(true);
        try {
            stdAnalyzer(analyzer,str1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stdAnalyzer(Analyzer analyzer, String str) throws IOException {
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
