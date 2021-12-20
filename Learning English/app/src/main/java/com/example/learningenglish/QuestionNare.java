package com.example.learningenglish;

public class QuestionNare {
    protected int ID;
    protected String Q;
    protected String AnswerA, AnswerB, AnswerC, AnswerD, Answer;
    protected String Image;

    public QuestionNare(int ID, String Q, String AnswerA,String AnswerB,String AnswerC,String AnswerD,String Answer, String Image ){
        this.ID = ID;
        this.Q = Q;
        this.AnswerA = AnswerA;
        this.AnswerB = AnswerB;
        this.AnswerC = AnswerC;
        this.AnswerD = AnswerD;
        this.Answer = Answer;
        this.Image = Image;

    }
}
