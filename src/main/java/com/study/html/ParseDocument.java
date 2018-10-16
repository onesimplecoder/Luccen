package com.study.html;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author alic
 * @ClassName: Html2Excel
 * @Description: TDD
 * @date 2018/8/9 22:11
 * @Version 1.0
 */
public class ParseDocument {

    public static String parseDoc(File file){
        String context = "";
        try {
            FileInputStream stream = new FileInputStream(file);
            BodyContentHandler handler = new BodyContentHandler();
            AutoDetectParser parser = new AutoDetectParser();
            ParseContext parseContext = new ParseContext();
            Metadata metadata = new Metadata();
            parser.parse(stream,handler,metadata,parseContext);
            context = handler.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return context;
    }
}
