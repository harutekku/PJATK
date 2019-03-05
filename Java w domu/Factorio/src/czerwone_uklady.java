public class czerwone_uklady extends obiekt{
    public czerwone_uklady(double ile_potrzeba_na_sekunde){
        super(6,1,new String[]{"miedziany drut","4","plastik","2","zielone uklady","2"},ile_potrzeba_na_sekunde);
        obiekt md=new miedziane_druty(ile_potrzeba_na_sekunde*4);
        obiekt zu=new zielone_uklady(ile_potrzeba_na_sekunde*2);
        this.mapaWymagan(md.mapaWymagan());
        this.mapaWymagan(zu.mapaWymagan());
    }
}
