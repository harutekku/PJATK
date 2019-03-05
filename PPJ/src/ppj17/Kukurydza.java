package ppj17;

public class Kukurydza {
    public int iloscZiaren;
    public Kukurydza(int iloscZiaren){
        this.iloscZiaren=iloscZiaren;
    }
    public Popcorn[] zrobPopcorn(){
        int a=(int)Math.random()*this.iloscZiaren;
        Popcorn[] tab=new Popcorn[a];
        for(int i=0;i<tab.length;i++){
            tab[i]=new Popcorn();
        }
        return tab;
    }
}
