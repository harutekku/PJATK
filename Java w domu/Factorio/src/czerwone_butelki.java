public class czerwone_butelki extends obiekt{
    public czerwone_butelki(double ile_potrzeba_na_sekunde){
        super(5,1,new String[]{"miedz","1","zelazne kolo zebate","1"},ile_potrzeba_na_sekunde);
        obiekt zk=new zelazne_kolo_zebate(ile_potrzeba_na_sekunde*1);
        this.mapaWymagan(zk.mapaWymagan());
    }
}
