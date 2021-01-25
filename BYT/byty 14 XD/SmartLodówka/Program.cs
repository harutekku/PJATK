using System;
using System.Threading;

namespace SmartLodówka
{
    class Program
    {
        public float Power { get; set; }
        public State State { get; set; }
        public bool light { get; set; }
        public Thread PowerThread { get; set; }
        public bool ThreadBool { get; set; }
        public float Temperature { get; set; }
        public Packer Takeaway, Fancy, Basic;
        public Contents Contents { get; set; }

        public Program()
        {
            Temperature = new Random().Next(20);
            State = new ClosedState(this);
            Takeaway = new Packer(new TakeawayDishBuilder());
            Fancy = new Packer(new FancyDishBuilder());
            Basic = new Packer(new BasicDishBuilder());
            Contents = new Contents();
            try
            {
                Dish kanapka = State.makeDish(Contents, Fancy, "Kanapka"); //wykonuje sie
                Console.WriteLine(kanapka);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            try
            {
                Dish kanapkaNaWynos = State.makeDish(Contents, Takeaway, "Kanapka");  //wykonuje sie
                Console.WriteLine(kanapkaNaWynos);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            State = new DefrostState(this);
            try
            {
                Dish kanapka3 = State.makeDish(Contents, Fancy, "Kanapka"); //nie wykonuje bo rozmrazanie
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            State = new OpenState(this);
            Thread.Sleep(5000);
            State = new ClosedState(this);
            try
            {
                Dish kanapka = State.makeDish(Contents, Fancy, "Kanapka"); //nie wykonuje bo nie ma skladnikow
                Console.WriteLine(kanapka);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.Message);
            }
            Thread.Sleep(5000);
            
            ThreadBool = false;
        }

        static void Main(string[] args)
        {
            Program program = new Program();
        }
    }
}
