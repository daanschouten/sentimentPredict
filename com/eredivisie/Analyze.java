package com.eredivisie;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

public class Analyze {

    public Map<String, Integer> getPoints(long date, Set<Match> allMatches) {
        Map<String, Integer> pointCount = new TreeMap<>();

        for (Match match : allMatches) {
            if (match.getDate() < date) {
                if (match.hasWinner()) {
                    if (! pointCount.containsKey(match.getWinner())) {
                        pointCount.put(match.getWinner(), 3);
                    } else {
                        int count = pointCount.get(match.getWinner());
                        pointCount.put(match.getWinner(), count + 3);
                    }
                } else {
                    if (! pointCount.containsKey(match.getHomeTeam())) {
                        pointCount.put(match.getHomeTeam(), 1);
                    } else {
                        int count = pointCount.get(match.getHomeTeam());
                        pointCount.put(match.getHomeTeam(), count + 1);
                    }
                    
                    if (! pointCount.containsKey(match.getAwayTeam())) {
                        pointCount.put(match.getAwayTeam(), 1);
                    } else {
                        int count = pointCount.get(match.getAwayTeam());
                        pointCount.put(match.getAwayTeam(), count + 1);
                    }
                }
            }
        }
        return pointCount;
    }
    
    public void getPoints(Set<Match> allMatches) {
        long defaultDate = 1528415561000L;
        getPoints(defaultDate, allMatches);
    }

    public int getComparativeRanking(long date, String homeClubName, String awayClubName, Set<Match> allMatches) {
        // returns ranking of particular club at particular point in time.
        Map<String, Integer> pointCount = getPoints(date, allMatches);
        
        int homeClubPoints = pointCount.get(homeClubName);
        int homeClubRanking = 1;

        int awayClubPoints = pointCount.get(awayClubName);
        int awayClubRanking = 1;

        for (Map.Entry<String, Integer> entry : pointCount.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            if (key != homeClubName && homeClubPoints < value) {
                homeClubRanking ++;
            }
            if (key != awayClubName && awayClubPoints < value) {
                awayClubRanking ++;
            }
        }

        int comparativeRanking = homeClubRanking - awayClubRanking;
        System.out.printf("Comparative Ranking: %s (%dst) versus %s (%dst). %n", 
        homeClubName, 
        homeClubRanking,
        awayClubName,
        awayClubRanking);
        
        return comparativeRanking;
    }

    private long stringToDate(String rawDate) {
        long epoch = 1L;
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date date = df.parse(rawDate);
            epoch = date.getTime();
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return epoch;
    }

    private String dateToString(long epoch) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
        Date date = new Date(epoch);
        String dateString = simpleDateFormat.format(date); 
        return dateString;
    }

    public int getComparativeRankingMatch(String homeClubName, String awayClubName, Set<Match> allMatches) {
        
        for (Match match : allMatches) {
            if (match.getHomeTeam().equals(homeClubName) && match.getAwayTeam().equals(awayClubName)) {
                String result;
                dateToString(match.getDate());
                if (! match.hasWinner()) {
                    result = "drew against";
                } else {
                    result = match.getHomeTeam().equals(match.getWinner()) ? "beat" : "lost against";
                }
                int comparativeRanking = getComparativeRanking(match.getDate(), homeClubName, awayClubName, allMatches);

                System.out.printf("%s %s %s on %s %n",
                homeClubName,
                result,
                awayClubName,
                dateToString(match.getDate()));
              
            }
        }
        return 0;
    }

}
