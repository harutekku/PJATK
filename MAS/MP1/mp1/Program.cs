using System;

namespace mp1
{
    class Program
    {
        static void Main(string[] args)
        {
            Employee employee1 = new Employee("Jan","Kowalski",new DateTime(2019,10,20));
            Storekeeper storekeeper1 = new Storekeeper("Robert","Makłowicz", new DateTime(2018, 10, 20));
            Storekeeper storekeeper2 = new Storekeeper("Mandzio", "Stokrotka", new DateTime(2017, 10, 20),new License(License.Licenses.Forklift));
            //Client client1 = new Client("Stanisław", "Biedronka", "Na paluchu 01", 27);
            //Client client2 = new Client("Taras", "Koktajlowy", "Na paluchu 02");
            //Client.SaveClients();
            Client.RestoreClients();
            Client.Extension[1].addAddress("Centrum 05");

            Console.WriteLine(employee1);
            Console.WriteLine(storekeeper1);
            Console.WriteLine(storekeeper2);
            //Console.WriteLine(Client.Extension);
            Client.showClients();
        }
    }
}
