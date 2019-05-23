package GUI3;

class Account{
    long balance;
    public Account(long b){
        balance=b;
    }
    public void transaction(Account a,String b){
        int kwota=(int) (Math.random()*10000)-5000;
        a.balance-=kwota;
        this.balance+=kwota;
        System.out.println("Transakcja na "+b+" na kwote: "+kwota);
    }
}