using System;

namespace mp
{
    class Program
    {
        static void Main(string[] args)
        {
            Employee employee1 = new Employee("Jan", "Kowalski", new DateTime(2019, 10, 20));
            Storekeeper storekeeper1 = new Storekeeper("Robert", "Makłowicz", new DateTime(2018, 10, 20));
            Storekeeper storekeeper2 = new Storekeeper("Mandzio", "Stokrotka", new DateTime(2017, 10, 20), new License(License.Licenses.Forklift));
            //Client client1 = new Client("Stanisław", "Biedronka", "Na paluchu 01", 27);
            //Client client2 = new Client("Taras", "Koktajlowy", "Na paluchu 02");
            //Client.SaveClients();
            Client.RestoreClients();
            Client.Extension[1].addAddress("Centrum 05");

            //Console.WriteLine(employee1);
            //Console.WriteLine(storekeeper1);
            //Console.WriteLine(storekeeper2);
            //Console.WriteLine(Client.Extension);
            //Client.showClients();

            //zwykła
            Warehouse warehouse = new Warehouse();
            warehouse.addStorekeeper(storekeeper1);
            //kwalifikowana
            warehouse.addLane(1);
            warehouse.addLane(2);
            //kompozycja
            Item.createItem("Makaron", 2.5f, new decimal(5.50), warehouse, 2);
            //z atrybutem
            new LaneAssignment(storekeeper2, warehouse.GetLane(2), new DateTime(2021, 05, 01), new DateTime(2021, 05, 10));

            Console.WriteLine(warehouse);
            Console.WriteLine(storekeeper1);
            Console.WriteLine(warehouse.GetLane(2));
            Console.WriteLine(storekeeper2.GetLastLaneAssignment());
            Console.WriteLine(new LaneAssignment(storekeeper1, warehouse.GetLane(1), new DateTime(2021, 04, 25), new DateTime(2021, 04, 30)));
            Console.WriteLine(warehouse.GetLane(2).getItem("Makaron"));


        }
    }
}
