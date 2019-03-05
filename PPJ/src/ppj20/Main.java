package ppj20;

public class Main {
    public static void main(String[] args){
        Drzewo dab=new Drzewo(true,10,"ladny");
        System.out.println(dab);

        DrzewoIglaste choinka=new DrzewoIglaste(false, 20, "nieladny", 2000,12.2);
        System.out.println(choinka);

        DrzewoLisciaste klon=new DrzewoLisciaste(true, 5, "ladne", 5);
        System.out.println(klon);

        DrzewoOwocowe jablon=new DrzewoOwocowe(false, 5, "ladne", 4,"Jablko");
        System.out.println(jablon);


        Prostokat jeden=new Prostokat(2,3);
        System.out.println(jeden);

        Prostopadloscian dwa=new Prostopadloscian(3,4,5);
        System.out.println(dwa);

        Trojkat trzy=new Trojkat(4);
        System.out.println(trzy);

        Ostroslup cztery=new Ostroslup(4,4);
        System.out.println(cztery);

        Granistoslup piec=new Granistoslup(4,5);
        System.out.println(piec);
    }
}
