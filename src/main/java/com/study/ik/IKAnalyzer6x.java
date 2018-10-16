package com.study.ik;

import org.apache.lucene.analysis.Analyzer;

/**
 * @author alic
 * @ClassName: IKAnalyzer6x
 * @Description: TDD
 * @date 2018/8/9 0:09
 * @Version 1.0
 */
public class IKAnalyzer6x extends Analyzer {

    private boolean userSmart;

    public boolean userSmart() {
        return userSmart;
    }

    public void setUserSmart(boolean userSmart) {
        this.userSmart = userSmart;
    }

    @Override
    protected TokenStreamComponents createComponents(String s) {
        IKTokenizer6x tokenizer6x = new IKTokenizer6x(this.userSmart);
        return new TokenStreamComponents(tokenizer6x);
    }

    public IKAnalyzer6x(){
        this(false);
    }

    public IKAnalyzer6x(boolean userSmart){
        super();
        this.userSmart = userSmart;
    }


}
