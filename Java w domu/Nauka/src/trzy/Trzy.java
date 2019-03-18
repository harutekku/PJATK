package trzy;

public class Trzy {
    String imie="BRAK";
    int wiek=0;

    public Trzy(String imi, int wie){
        imie=imi;
        wiek=wie;
    }

    public String toString(){
        return "imie: "+imie+"\nwiek: "+wiek;
    }


    public static void main(String[] args) {
        Trzy jeden=new Trzy("adam",15);
        System.out.println(jeden);
    }

}
