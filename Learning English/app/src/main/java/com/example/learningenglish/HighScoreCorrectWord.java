package com.example.learningenglish;

public class HighScoreCorrectWord {
    protected int star;
    protected String name;

    public HighScoreCorrectWord(int star, String name) {
        this.star = star;
        this.name = name;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
