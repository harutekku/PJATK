package GUI3;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*{
            Thread th1=new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<20;i++){
                        for(int j=0;j<10;j++){
                            System.out.print(j+" ");
                        }
                        System.out.println();
                    }
                }
            });

            Thread th2=new Thread(()->{
                for(int i=0;i<20;i++){
                    for(int j=0;j<10;j++){
                        System.err.print(j+" ");
                    }
                    System.err.println();
                }

            });
            th1.start();

            th2.start();
        }*/
        /*{
            long start=System.currentTimeMillis();
            for(int i=0;i<1000000;i++){
                System.out.print(i+" ");
                if(i%10000==0){
                    System.out.println();
                }
            }
            System.out.println();
            long stop=System.currentTimeMillis();
            System.out.println((stop-start)/1000.0);
        }*/
        /*{
            Thread th1=new Thread(new Runnable() {
                @Override
                public void run() {
                    long start=System.currentTimeMillis();
                    for(int i=0;i<1000000;i++){
                        if(i%2==1){
                            System.out.print(i+" ");
                        }
                    }
                    long stop=System.currentTimeMillis();
                    System.out.println((stop-start)/1000.0);
                }
            });

            Thread th2=new Thread(()->{
                long start=System.currentTimeMillis();
                for(int i=0;i<1000000;i++){
                    if(i%2==0){
                        System.err.print(i+" ");
                    }
                }
                long stop=System.currentTimeMillis();
                System.err.println((stop-start)/1000.0);
            });

            th2.start();
            th1.start();
        }*/
        /*
        {
            Counter c = new Counter();
            synchronized(c){
                AddingThread at0 = new AddingThread(c);
                at0.start();
                AddingThread at1 = new AddingThread(c);
                at1.start();
                AddingThread at2 = new AddingThread(c);
                at2.start();
            }
            System.out.println("Counter.x=" + c);
        }*/

        {

            Account konto1=new Account(10000);
            Account konto2=new Account(10000);

            new Thread(()->{konto1.transaction(konto2,"pierwszym");});
            new Thread(()->{konto1.transaction(konto2,"pierwszym");});
            new Thread(()->{konto1.transaction(konto2,"pierwszym");});
            new Thread(()->{konto1.transaction(konto2,"pierwszym");});
            new Thread(()->{konto1.transaction(konto2,"pierwszym");});
            new Thread(()->{konto2.transaction(konto1,"drugim");});
            new Thread(()->{konto2.transaction(konto1,"drugim");});
            new Thread(()->{konto2.transaction(konto1,"drugim");});
            new Thread(()->{konto2.transaction(konto1,"drugim");});
            new Thread(()->{konto2.transaction(konto1,"drugim");});
            System.out.println("Przed: "+(konto1.balance+konto2.balance));
            //while(th1.isAlive()||th2.isAlive());
            Thread.sleep(1000);
            System.out.println("Po: "+(konto1.balance+konto2.balance));


        }


    }
}
