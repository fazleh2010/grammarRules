/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elahi
 */
public class QAElement {
    private List<String[]> question = new ArrayList<String[]>();
    private String bindingSparql = null;
    private String questionSparql = null;
    private String complexSentence = null;
    
    public QAElement(List<String[]> question,String bindingSparql,String questionSparql){
       this.question = question;
       this.bindingSparql = bindingSparql;
       this.questionSparql=questionSparql;
    }
    public QAElement(List<String[]> question,String bindingSparql,String questionSparql,String  complexSentence){
       this.question = question;
       this.bindingSparql = bindingSparql;
       this.complexSentence = complexSentence;
    }

    public List<String[]> getQuestion() {
        return question;
    }

    public String getComplexSentence() {
        return complexSentence;
    }

    public String getBindingSparql() {
        return bindingSparql;
    }

    public String getQuestionSparql() {
        return questionSparql;
    }


    @Override
    public String toString() {
        return "QAElement{" + "question=" + question + ", sparql=" + bindingSparql + '}';
    }

   
    
}
