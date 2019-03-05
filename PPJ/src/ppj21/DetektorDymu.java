package ppj21;

public class DetektorDymu {
    private boolean czyDym;
    public DetektorDymu(boolean czyDym){
        this.czyDym=czyDym;
    }
    public void sprawdz() throws Alarm{
        if(this.czyDym){
            throw new Alarm("Pali sie");
        }

    }
}
