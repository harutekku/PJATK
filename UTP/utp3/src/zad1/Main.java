/**
 *
 *  @author Pawłowicz Jakub S18688
 *
 */

package zad1;


import java.util.*;

public class Main {

    static List<String> getPricesInPLN(List<String> destinations,double xrate) {
    return ListCreator.collectFrom(destinations)
                       .when((dest)->((String)dest).substring(0,3).equals("WAW"))
                       .mapEvery((dest)->"To "+((String)dest).substring(4,7)+" - price in PLN:\t"+(int)(Integer.parseInt(((String)dest).substring(8))*xrate));
  }

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR 
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
    for (String r : result) System.out.println(r);
  }
}
