package com.example.learningenglish;

public class QuestionNare {
    public int ID;
    public String Q;
    public String AnswerA, AnswerB, AnswerC, AnswerD, Answer;
    public String Image;

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
