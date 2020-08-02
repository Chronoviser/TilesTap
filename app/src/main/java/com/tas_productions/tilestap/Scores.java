package com.tas_productions.tilestap;

public class Scores implements Comparable<Scores>{
    private String userName;
    private int score;

    public Scores() {
    }

    public Scores(String userName, int score) {
        this.userName = userName;
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Scores o) {
        if(this.getScore()>o.getScore()) {
            return -1;
        }
            else
                return 1;

    }
}
