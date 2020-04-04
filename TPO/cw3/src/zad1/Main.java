/**
 *
 *  @author Pawłowicz Jakub S18688
 *
 */

package zad1;


public class Main {

  public static void main(String[] args) throws Exception {
    String fileName = System.getProperty("user.home") + "\\PassTimeOptions.yaml";
    Options opts = Tools.createOptionsFromYaml(fileName);
    System.out.println(opts);
    opts.getClientsMap().forEach( (id, dates) -> {
      System.out.println(id);
      dates.forEach( dpair -> {
        String[] d = dpair.split(" +");
        String info = Time.passed(d[0], d[1]);
        System.out.println(info);
      });
    });

    System.out.println();
    System.out.println(Time.passed("2000-01-01", "2020-04-01"));
    System.out.println(Time.passed("2018-01-01", "2020-02-02"));
    System.out.println(Time.passed("2019-01-01", "2020-04-03"));
    System.out.println(Time.passed("2020-04-01T10:00", "2020-04-01T13:00"));
    System.out.println(Time.passed("2020-03-27T10:00", "2020-03-28T10:00"));
    System.out.println(Time.passed("2020-03-28T10:00", "2020-03-29T10:00"));
    System.out.println(Time.passed("2020-03-28T10", "2020-03-29T10:00"));
    System.out.println(Time.passed("2019-02-29", "2020-04-03"));
  }

}
