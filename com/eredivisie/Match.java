package com.eredivisie;

public class Match {
    private String mHomeTeam;
    private String mAwayTeam;
    private int mHomeScore;
    private int mAwayScore;
    private long mDate;

    public Match(long date, String homeTeam, String awayTeam, int homeScore, int awayScore) {
        mDate = date;
        mHomeTeam = homeTeam;
        mAwayTeam = awayTeam;
        mHomeScore = homeScore;
        mAwayScore = awayScore;
    }

    public boolean hasWinner() {
        return mHomeScore != mAwayScore ? true : false;
    }

    public String getWinner() {
        return mHomeScore > mAwayScore ? mHomeTeam : mAwayTeam;
    }

    public String getHomeTeam() {
        return mHomeTeam;
    }

    public String getAwayTeam() {
        return mAwayTeam;
    }

    public long getDate() {
        return mDate;
    }

}