public class zielone_uklady extends obiekt{
    public zielone_uklady(double ile_potrzeba_na_sekunde){
        super(0.5,1,new String[]{"miedziany drut","3","zelazo","1"},ile_potrzeba_na_sekunde);
        obiekt md=new miedziane_druty(ile_potrzeba_na_sekunde*3);
        this.mapaWymagan(md.mapaWymagan());
    }
}
