/**
 *
 *  @author Pawłowicz Jakub S18688
 *
 */

package zad2;


/*<-- niezbędne importy */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

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
    List<String> result = dest.stream()
            .filter((strim)->((strim).substring(0,3).equals("WAW")))
            .map((strim)->"To "+(strim).substring(4,7)+" - price in PLN:\t"+(int)(Integer.parseInt((strim).substring(8))*ratePLNvsEUR))
            .collect(Collectors.toList());

    for (String r : result) System.out.println(r);
  }
}
