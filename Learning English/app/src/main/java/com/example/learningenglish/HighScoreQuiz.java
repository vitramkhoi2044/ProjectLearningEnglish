package com.example.learningenglish;

public class HighScoreQuiz {
    protected String Name;
    protected int Score;

    public HighScoreQuiz(String name, int score) {
        this.Name = name;
        Score = score;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
