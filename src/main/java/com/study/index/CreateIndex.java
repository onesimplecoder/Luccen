package com.study.index;

import com.study.ik.IKAnalyzer6x;
import com.study.po.News;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * @author alic
 * @ClassName: CreateIndex
 * @Description: TDD
 * @date 2018/10/20 23:23
 * @Version 1.0
 */
public class CreateIndex {


    public static void main(String[] args) {
        News news1 = new News();
        news1.setId(1);
        news1.setTitle("习近平会见美国总统奥巴马，学习国外经验");
        news1.setContent("国家主席习近平9月3日在杭州西湖国宾馆会见前来出席二十国集团领导人杭州峰会的美国总统奥巴马");
        news1.setReply(672);


        News news2 = new News();
        news2.setId(2);
        news2.setTitle("习近平对全国党委秘书长会议作出重要指示");
        news2.setContent("中共中央总书记、国家主席、中央军委主席习近平作出重要指示。他指出，党委办公厅（室）作为党委的综合部门，是党委履行领导职责的参谋助手");
        news2.setReply(995);


        News news3 = new News();
        news3.setId(3);
        news3.setTitle("印度火车撞人事故已致61人遇难");
        news3.setContent("据印度媒体报道，当地时间19日，印度旁遮普邦阿姆利则市附近发生一起火车撞人事件，已造成至少61人死亡，72人受伤");
        news3.setReply(1872);

        Analyzer analyzer = new IKAnalyzer6x();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        Directory directory = null;
        IndexWriter indexWriter = null;

        Path indexPath = Paths.get("indexdir");

        Date date = new Date();
        if(Files.isReadable(indexPath)){
            try {
                directory = FSDirectory.open(indexPath);
                indexWriter = new IndexWriter(directory,config);

                //设置新闻ID索引并存储
                FieldType idType = new FieldType();
                idType.setIndexOptions(IndexOptions.DOCS);
                idType.setStored(true);

                //设置新闻标题索引文档、词频频率、位移信息和偏移量，存储并词条化
                FieldType titleType = new FieldType();
                titleType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
                titleType.setStored(true);
                titleType.setTokenized(true);

                FieldType contentType = new FieldType();
                contentType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
                contentType.setStored(true);
                contentType.setTokenized(true);
                contentType.setStoreTermVectors(true);
                contentType.setStoreTermVectorPositions(true);
                contentType.setStoreTermVectorOffsets(true);
                contentType.setStoreTermVectorPayloads(true);

                Document doc1 = new Document();
                doc1.add(new Field("id",String.valueOf(news1.getId()),idType));
                doc1.add(new Field("title",news1.getTitle(),titleType));
                doc1.add(new Field("content",news1.getContent(),contentType));
                doc1.add(new IntPoint("reply",news1.getReply()));
                doc1.add(new StoredField("reply_display",news1.getReply()));


                Document doc2 = new Document();
                doc2.add(new Field("id",String.valueOf(news2.getId()),idType));
                doc2.add(new Field("title",news2.getTitle(),titleType));
                doc2.add(new Field("content",news2.getContent(),contentType));
                doc2.add(new IntPoint("reply",news2.getReply()));
                doc2.add(new StoredField("reply_display",news2.getReply()));

                Document doc3 = new Document();
                doc3.add(new Field("id",String.valueOf(news3.getId()),idType));
                doc3.add(new Field("title",news3.getTitle(),titleType));
                doc3.add(new Field("content",news3.getContent(),contentType));
                doc3.add(new IntPoint("reply",news3.getReply()));
                doc3.add(new StoredField("reply_display",news3.getReply()));

                indexWriter.addDocument(doc1);
                indexWriter.addDocument(doc2);
                indexWriter.addDocument(doc3);

                indexWriter.commit();
                indexWriter.close();
                directory.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Date end = new Date();
            System.out.println(end.getTime()-date.getTime());
        }

    }

}
