public class niebieskie_uklady extends obiekt{
    public niebieskie_uklady(double ile_potrzeba_na_sekunde){
        super(10,1,new String[]{"zielone uklady","20","czerwone uklady","2","kwas siarkowy","5"},ile_potrzeba_na_sekunde);
        obiekt zu=new zielone_uklady(ile_potrzeba_na_sekunde*20);
        obiekt cu=new czerwone_uklady(ile_potrzeba_na_sekunde*2);
        this.mapaWymagan(zu.mapaWymagan());
        this.mapaWymagan(cu.mapaWymagan());
    }
}
