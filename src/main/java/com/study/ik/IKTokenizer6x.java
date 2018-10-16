package com.study.ik;

import jdk.nashorn.internal.parser.Token;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;

/**
 * @author alic
 * @ClassName: IKTokenizer6x
 * @Description: TDD
 * @date 2018/8/8 23:38
 * @Version 1.0
 */
public class IKTokenizer6x extends Tokenizer {


    private IKSegmenter _ikSegmenter;

    private final CharTermAttribute termAtt;

    private final OffsetAttribute offsetAtt;

    private final TypeAttribute typeAtt;

    private int endPosition;

    public IKTokenizer6x(boolean userSmart){
        super();
        offsetAtt = addAttribute(OffsetAttribute.class);
        termAtt = addAttribute(CharTermAttribute.class);
        typeAtt = addAttribute(TypeAttribute.class);
        _ikSegmenter = new IKSegmenter(input,userSmart);
    }


    @Override
    public boolean incrementToken() throws IOException {
        clearAttributes();
        Lexeme next = _ikSegmenter.next();
        if(next != null){
            termAtt.append(next.getLexemeText());
            termAtt.setLength(next.getLength());
            offsetAtt.setOffset(next.getBeginPosition(),next.getEndPosition());
            endPosition = next.getEndPosition();
            typeAtt.setType(next.getLexemeText());
            return true;
        }
        return false;
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        _ikSegmenter.reset(input);
    }

    @Override
    public final void end(){
        int offset = correctOffset(this.endPosition);
        offsetAtt.setOffset(offset,offset);
    }

}
