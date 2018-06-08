import com.eredivisie.Reader;
import com.eredivisie.Match;
import com.eredivisie.Analyze;

import java.util.Set;

public class Predict {
    // static String rawDate = "01/05/18";
    // static long date = rawDate.length() > 0 ? Reader.stringToDate(rawDate) : 1528415561000L;
    // // range 01/07/2017 - 01/06/2018
    
    static String homeClubName = "Den Haag";
    static String awayClubName = "AZ Alkmaar";

    public static void main(String[] args) {   
        Reader reader = new Reader();
        Set<Match> allMatches = reader.read();

        Analyze analyze = new Analyze();

        analyze.getComparativeRankingMatch(homeClubName, awayClubName, allMatches);
    }
}