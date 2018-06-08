package com.eredivisie;

import com.eredivisie.Match;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import java.util.HashMap;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

public class Reader {
    String csvFile;
    String line;
    String cvsSplitBy;

    public Reader() {
        csvFile = "/Users/daanschouten/N11.csv";
        line = "";
        cvsSplitBy = ",";
    }

    private Set<Match> readCSV() {

        Set<Match> allMatches = new HashSet<>();
        // date, matches
        
        int dateIndex = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] rawMatch = line.split(cvsSplitBy);
                Match match = new Match(
                    stringToDate(rawMatch[1]),
                    rawMatch[2],
                    rawMatch[3],
                    Integer.parseInt(rawMatch[4]),
                    Integer.parseInt(rawMatch[5])
                );
                allMatches.add(match);                
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return allMatches;
    }

    public static long stringToDate(String rawDate) {
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

    public Set<Match> read() {
        return readCSV();
    }
    
}